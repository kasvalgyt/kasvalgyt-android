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
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.view.View
import com.google.android.gms.location.LocationRequest
import com.mcxiaoke.koi.log.logd
import com.mcxiaoke.koi.log.loge
import com.mcxiaoke.koi.utils.currentVersion
import com.patloew.rxlocation.RxLocation
import kotlinx.android.synthetic.main.activity_main.*
import lt.blackbrackets.kasvalgyt.api.ApiClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import com.tbruyelle.rxpermissions2.RxPermissions
import lt.blackbrackets.kasvalgyt.utils.*

class MainActivity : AppCompatActivity() {
    var onCreateAt : Long = System.currentTimeMillis()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rxPermissions = RxPermissions(this)

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


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager

        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe { item ->
                    if (item) {
                        getLocation(applicationContext) { location ->
                            getPlaces(location)
                        }
                    }
                }

        onCreateAt = System.currentTimeMillis()

        loaderGif.setImageResource(R.drawable.pin_eye_256)
    }

    fun getPlaces(location: Location) {
        var cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, -1)
        ApiClient.getPlacesObservable(cal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    items -> logd(items.toString())
                    setItems(items, location)
                    showContentView()
                }, {
                    error -> loge("Wat"+error.toString())
                })
    }

    fun showContentView() {
        var base = 1000L
        var delay : Long = base + (1000 * Math.random()).toLong()
        var diff = System.currentTimeMillis() - onCreateAt
        if (diff < delay) {
            delay -= diff
        }

        contentHolder.alpha = 0f
        contentHolder.visibility = View.VISIBLE
        contentHolder.animate().alpha(1f).setStartDelay(delay).start()
        loadingHolder.animate().alpha(0f).setStartDelay(delay).start()

    }

    fun setItems(items : List<EatingPlace>, location: Location) {
        var adapter = EatingPlaceAdapter(this, location)
        adapter.placeList = items
        recyclerView.adapter = adapter
    }
}

fun getLocation(context: Context, f:(location: Location) -> Unit) {
    val rxLocation = RxLocation(context)

    val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000)

    locationRequest.numUpdates = 1

    rxLocation.location().updates(locationRequest)
            .subscribe { address ->
                f.invoke(address)
            }
}