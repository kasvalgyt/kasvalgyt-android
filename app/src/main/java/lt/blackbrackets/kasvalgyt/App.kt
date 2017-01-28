package lt.blackbrackets.kasvalgyt

import android.app.Application
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso



/**
 * Created by simonas on 27/01/2017.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val builder = Picasso.Builder(this)
        builder.downloader(OkHttpDownloader(this, Integer.MAX_VALUE.toLong()))
        val built = builder.build()
        Picasso.setSingletonInstance(built)
    }
}