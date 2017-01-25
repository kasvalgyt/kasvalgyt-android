package lt.blackbrackets.kasvalgyt.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by simonas on 25/01/2017.
 */

class EatingPlace {
    @Expose
    private val pagePicture: String? = null
    @SerializedName("page_name")
    @Expose
    private val pageName: String? = null
    @SerializedName("page_location")
    @Expose
    private val pageLocation: PageLocation? = null
    @SerializedName("page_wifi")
    @Expose
    private val pageWifi: Any? = null
    @SerializedName("page_link")
    @Expose
    private val pageLink: String? = null
    @SerializedName("message")
    @Expose
    private val message: String? = null
    @SerializedName("image")
    @Expose
    private val image: String? = null
}