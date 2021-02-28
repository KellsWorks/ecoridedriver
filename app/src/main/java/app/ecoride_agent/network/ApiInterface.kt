package app.ecoride_agent.network

import app.ecoride_agent.network.responses.GeneralResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    /* Location API */

    @POST("update-location")
    @FormUrlEncoded
    fun updateLocation(
        @Field("id") id : Int,
        @Field("lat") lat : String,
        @Field("long") long : String
    ) : Call<GeneralResponse>

}