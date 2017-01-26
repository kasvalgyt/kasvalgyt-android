package lt.blackbrackets.kasvalgyt.api.models

import android.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso
import android.databinding.BindingAdapter
import android.widget.ImageView
import android.content.Intent
import android.net.Uri


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
}

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView?, url: String?) {
    Picasso.with(view!!.context).load(url).into(view)
}