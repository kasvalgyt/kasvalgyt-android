package lt.blackbrackets.kasvalgyt.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by simonas on 25/01/2017.
 */

class PageLocation {
    @SerializedName("city")
    @Expose
    private val city = ThreadLocal<String>()
    @SerializedName("country")
    @Expose
    private val country: String? = null
    @SerializedName("latitude")
    @Expose
    private val latitude: Double? = null
    @SerializedName("longitude")
    @Expose
    private val longitude: Double? = null
    @SerializedName("street")
    @Expose
    private val street: String? = null
    @SerializedName("zip")
    @Expose
    private val zip: String? = null
}
