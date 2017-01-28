package lt.blackbrackets.kasvalgyt.api.models

import android.location.Location
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by simonas on 25/01/2017.
 */

class PageLocation (
    @SerializedName("city")
    @Expose
    val city : String? = null,
    @SerializedName("country")
    @Expose
    val country: String? = null,
    @SerializedName("latitude")
    @Expose
    val latitude: Double? = null,
    @SerializedName("longitude")
    @Expose
    val longitude: Double? = null,
    @SerializedName("street")
    @Expose
    val street: String? = null,
    @SerializedName("zip")
    @Expose
    val zip: String? = null
) {
    fun getLocation(): Location {
        var loc = Location("")
        loc.latitude = latitude!!
        loc.longitude = longitude!!
        return loc
    }
}
