package lt.blackbrackets.kasvalgyt.utils

import android.graphics.Color

/**
 * Created by simonas on 27/01/2017.
 */

fun Int.addAlphaToColor(alpha: Int): Int {
    return Color.argb(alpha, Color.red(this), Color.green(this), Color.blue(this))
}