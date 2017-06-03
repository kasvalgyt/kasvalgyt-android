package lt.blackbrackets.kasvalgyt.utils

import android.content.Context
import android.graphics.Color
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import com.mcxiaoke.koi.ext.dpToPx
import lt.blackbrackets.kasvalgyt.R
import lt.blackbrackets.kasvalgyt.api.models.PageLocation
import android.view.ViewConfiguration



/**
 * Created by simonas on 27/01/2017.
 */

fun Int.addAlphaToColor(alpha: Int): Int {
    return Color.argb(alpha, Color.red(this), Color.green(this), Color.blue(this))
}

fun Context.getActionBarHeight(): Int {
    val typedValue = TypedValue()

    var attributeResourceId = android.R.attr.actionBarSize
    if (this is AppCompatActivity) {
        attributeResourceId = R.attr.actionBarSize
    }

    if (theme.resolveAttribute(attributeResourceId, typedValue, true)) {
        return TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)
    } else {
        // fallback
        return 48.dpToPx()
    }
}

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}


fun Context.getNavigationBarHeight(): Int {
    val hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey()
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    if (resourceId > 0 && !hasMenuKey) {
        return resources.getDimensionPixelSize(resourceId)
    }
    return 0
}
