package lt.blackbrackets.kasvalgyt.api.models

import android.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.location.Location


/**
 * Created by simonas on 25/01/2017.
 */

class EatingPlace : BaseObservable() {
    @SerializedName("page_picture")
    @Expose
    var pagePicture: String? = null

    @SerializedName("page_name")
    @Expose
    var pageName: String? = null

    @SerializedName("page_location")
    @Expose
    var pageLocation: PageLocation? = null

    @SerializedName("page_wifi")
    @Expose
    var pageWifi: String? = null

    @SerializedName("page_link")
    @Expose
    var pageLink: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("image")
    @Expose
    var mealImage: String? = null

    fun getDistanceInM(loc: Location)
            = pageLocation!!.getLocation().distanceTo(loc)

    fun getDistanceInKm(loc: Location)
            = getDistanceInM(loc) / 1000

    fun getDistanceString(loc: Location)
            = getDistanceInKm(loc).toInt().toString() + " km"
}