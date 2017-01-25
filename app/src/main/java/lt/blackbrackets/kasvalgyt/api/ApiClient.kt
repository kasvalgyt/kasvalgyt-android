package lt.blackbrackets.kasvalgyt.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory



/**
 * Created by simonas on 25/01/2017.
 */

object ApiClient {
    var retrofit : Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.kasvalgyt.lt/")
            .build()

    /**
     * date format 2017-01-25
     */
    fun getPlacesObservable(date : String)
            = retrofit.create(KasValgytApi::class.java).getPlaces(date)
}