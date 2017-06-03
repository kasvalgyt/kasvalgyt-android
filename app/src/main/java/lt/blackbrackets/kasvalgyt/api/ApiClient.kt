package lt.blackbrackets.kasvalgyt.api

import android.annotation.SuppressLint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by simonas on 25/01/2017.
 */

object ApiClient {
    const val API_URL = "https://www.kasvalgyt.lt/"

    var retrofit : Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL)
            .build()

    /**
     * @suppress Api requires this format.
     */
    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("yyyy-MM-dd")

    fun getPlacesObservable(nowCal : Calendar)
            = retrofit.create(KasValgytApi::class.java).getPlaces(format.format(nowCal.time))
}