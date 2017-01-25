package lt.blackbrackets.kasvalgyt.api

import lt.blackbrackets.kasvalgyt.api.models.EatingPlace
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by simonas on 25/01/2017.
 */
interface KasValgytApi {
    /**
     * E.g. https://www.kasvalgyt.lt/api?date=2017-01-25
     */
    @GET("api")
    fun getPlaces(@Query("date") date : String) : Call<List<EatingPlace>>
}