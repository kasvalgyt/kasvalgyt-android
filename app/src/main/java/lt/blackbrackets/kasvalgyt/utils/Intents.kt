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
    val FACEBOOK_PACKAGE_NAME = "com.facebook.katana"

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
    fun openFbPage(context: Context, facebookURL: String) {
        var pageId = facebookURL.removePrefix("http://facebook.com/")
        if (Intents.isPackageExists(context, Intents.FACEBOOK_PACKAGE_NAME)) {
            var facebookUrl = "fb://page/" + pageId
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            facebookIntent.data = Uri.parse(facebookUrl)
            context.startActivity(facebookIntent)
        } else {
            var facebookUri = Uri.parse("https://www.facebook.com/" + pageId)
            var intent = Intent(Intent.ACTION_VIEW, facebookUri)
            context.startActivity(intent)
        }
    }

    fun openLink(context: Context, linkURL: String) {
        if (linkURL.startsWith("https://www.facebook.com/")) {
            openFbPage(context, linkURL)
        } else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkURL))
            context.startActivity(intent)
        }
    }

    fun isPackageExists(context: Context, targetPackage: String): Boolean {
        val pm = context.packageManager
        val packages = pm.getInstalledApplications(0)
        packages.forEach { if (it.packageName == targetPackage) return true }
        return false
    }
}
