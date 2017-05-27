package lt.blackbrackets.kasvalgyt

import android.support.multidex.MultiDexApplication
import com.rollbar.android.Rollbar
import com.squareup.picasso.LruCache
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso

/**
 * Created by simonas on 27/01/2017.
 */

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        val builder = Picasso.Builder(this)
        builder.downloader(OkHttpDownloader(this, Integer.MAX_VALUE.toLong()))
        builder.memoryCache(LruCache(102400))
        val built = builder.build()
        Picasso.setSingletonInstance(built)

        Rollbar.init(this, "6021b3f0353843bfaec8ee3dd8af0214", "production");
    }
}