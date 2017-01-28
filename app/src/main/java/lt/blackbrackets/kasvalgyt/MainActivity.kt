package lt.blackbrackets.kasvalgyt

import android.Manifest
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.mcxiaoke.koi.ext.dpToPx
import lt.blackbrackets.kasvalgyt.api.models.EatingPlace
import java.util.*
import com.readystatesoftware.systembartint.SystemBarTintManager
import android.util.TypedValue
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.location.Location
import com.apt7.rxpermissions.Permission
import com.apt7.rxpermissions.PermissionObservable
import com.google.android.gms.location.LocationRequest
import com.mcxiaoke.koi.log.logd
import com.mcxiaoke.koi.log.loge
import com.mcxiaoke.koi.utils.currentVersion
import com.patloew.rxlocation.RxLocation
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_main.*
import lt.blackbrackets.kasvalgyt.api.ApiClient
import lt.blackbrackets.kasvalgyt.utils.addAlphaToColor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    lateinit var mAdapter: EatingPlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        PermissionObservable.getInstance().checkThePermissionStatus(this,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(object : DisposableObserver<Permission>() {
                    override fun onError(e: Throwable?) {

                    }

                    override fun onComplete() {
                        getLocation(applicationContext) {
                            location -> logd { location.toString() }
                        }
                    }

                    override fun onNext(value: Permission?) {

                    }

                })

        supportActionBar!!.setIcon(R.mipmap.kas_valgyt_icon)
        supportActionBar!!.setDisplayUseLogoEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "   " + resources.getString(R.string.app_name)

        @SuppressLint("NewApi")
        if(currentVersion() >= 19) {
            val tintManager = SystemBarTintManager(this)
            tintManager.setNavigationBarTintEnabled(true)
            tintManager.isStatusBarTintEnabled = true
            tintManager.setNavigationBarTintColor(resources.getColor(R.color.nav_bar))
            tintManager.setStatusBarTintColor(resources.getColor(R.color.nav_bar))

            var drawable = ColorDrawable()
            drawable.color = resources.getColor(R.color.colorPrimary).addAlphaToColor(230)
            supportActionBar!!.setBackgroundDrawable(drawable)

            var height = getActionBarHeight(this) + getStatusBarHeight(this)

            recyclerView.setPaddingRelative(0, height, 0, 48.dpToPx())
            recyclerView.setPadding(0, height, 0, 48.dpToPx())
        }

        mAdapter = EatingPlaceAdapter(this)

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager

        var cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, -1)
        ApiClient.getPlacesObservable(cal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    items -> logd(items.toString());
                    setItems(items)
                }, {
                    error -> loge("Wat"+error.toString())
                })
    }

    fun setItems(items : List<EatingPlace>) {
        mAdapter.placeList.clear()
        mAdapter.placeList.addAll(items)
        recyclerView.adapter = mAdapter
    }
}

fun getLocation(context: Context, f:(location: Location) -> Unit) {
    val rxLocation = RxLocation(context)

    val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000)

    locationRequest.numUpdates = 3

    rxLocation.location().updates(locationRequest)
            .subscribe { address ->
                f.invoke(address)
            }
}

fun getActionBarHeight(context: Context): Int {
    val typedValue = TypedValue()

    var attributeResourceId = android.R.attr.actionBarSize
    if (context is AppCompatActivity) {
        attributeResourceId = R.attr.actionBarSize
    }

    if (context.theme.resolveAttribute(attributeResourceId, typedValue, true)) {
        return TypedValue.complexToDimensionPixelSize(typedValue.data, context.resources.displayMetrics)
    }

    return 48.dpToPx()
}

fun getStatusBarHeight(c : Context): Int {
    var result = 0
    val resourceId = c.getResources().getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = c.getResources().getDimensionPixelSize(resourceId)
    }
    return result
}
