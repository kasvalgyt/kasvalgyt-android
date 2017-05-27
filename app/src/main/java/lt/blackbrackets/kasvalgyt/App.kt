package lt.blackbrackets.kasvalgyt

import android.support.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig
import com.rollbar.android.Rollbar

/**
 * Created by simonas on 27/01/2017.
 */

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Rollbar.init(this, "6021b3f0353843bfaec8ee3dd8af0214", "production")

        val config = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(SimpleProgressiveJpegConfig())
                .setResizeAndRotateEnabledForNetwork(true)
                .setDownsampleEnabled(true)
                .build();

        Fresco.initialize(this, config);
    }
}