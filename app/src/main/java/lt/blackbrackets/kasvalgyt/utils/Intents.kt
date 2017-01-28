package lt.blackbrackets.kasvalgyt.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mcxiaoke.koi.ext.toast
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat.startActivity






/**
 * Created by simonas on 27/01/2017.
 */

object Intents {
    fun openNav(c: Context, lat: Float, lgn: Float) {
        val gmmIntentUri = Uri.parse("google.navigation:q=$lat,$lgn")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.`package` = "com.google.android.apps.maps"
        if (mapIntent.resolveActivity(c.packageManager) != null) {
            c.startActivity(mapIntent)
        } else {
            c.toast("Google Maps unavailable")
        }
    }

    //method to get the right URL to use in the intent
    fun openFbPage(context: Context, pageId: String) {
        val packageManager = context.packageManager
        var facebookUrl = ""
        try {
            facebookUrl = "fb://page/" + pageId
        } catch (e: PackageManager.NameNotFoundException) {
            facebookUrl = "href=https://www.facebook.com/"+pageId //normal web url
        }

        val facebookIntent = Intent(Intent.ACTION_VIEW)
        facebookIntent.data = Uri.parse(facebookUrl)
        context.startActivity(facebookIntent)
    }
}
