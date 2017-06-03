package lt.blackbrackets.kasvalgyt.api

import lt.blackbrackets.kasvalgyt.api.models.EatingPlace
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by simonas on 25/01/2017.
 */
interface KasValgytApi {

    @GET("api")
    fun getPlaces(@Query("date") date : String): Observable<List<EatingPlace>>

}