package app.ecoride_agent.network

import app.ecoride_agent.network.responses.GeneralResponse
import app.ecoride_agent.network.responses.location.LocationUpdate
import app.ecoride_agent.network.responses.login.Login
import app.ecoride_agent.network.responses.register.Register
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    /* Authentication API routes */

    @POST("register")
    @FormUrlEncoded
    fun register(
            @Field("name") name : String,
            @Field("email") email : String,
            @Field("phone") phone : String,
            @Field("role") role : String,
            @Field("emergency") emergency : String,
            @Field("password") password : String,
            @Field("c_password") c_password : String
    ) : Call<Register>

    @POST("login")
    @FormUrlEncoded
    fun login(
            @Field("phone") phone : String,
            @Field("password") password: String
    ) : Call<Login>

    @POST("change-password")
    @FormUrlEncoded
    fun changePassword(
            @Field("id") id : Int,
            @Field("password") password: String
    ) : Call<GeneralResponse>


    @POST("update-profile-picture")
    @FormUrlEncoded
    fun updateProfilePicture(
            @Field("id") id : Int,
            @Field("photo") photo: String
    ) : Call<GeneralResponse>

    /* Location API routes */

    @POST("update-location")
    @FormUrlEncoded
    fun updateLocation(
        @Field("id") id : Int,
        @Field("lat") lat : String,
        @Field("long") long : String
    ) : Call<LocationUpdate>

}