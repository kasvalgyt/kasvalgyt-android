package lt.blackbrackets.kasvalgyt.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by simonas on 25/01/2017.
 */

object ApiClient {

    var retrofit : Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.kasvalgyt.lt/")
            .build()


    val format = SimpleDateFormat("yyyy-MM-dd")

    /**
     * date format 2017-01-25
     */
    fun getPlacesObservable(nowCal : Calendar)
//        = retrofit.create(KasValgytApi::class.java).getPlaces(format.format(nowCal.time))
            = retrofit.create(KasValgytApi::class.java).getPlaces("2017-01-25")
}