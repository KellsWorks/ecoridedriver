package app.ecoride_agent.network.fcm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class IFCMInterface {

    @Headers({
            "Content-Type:application/json",
            "Authorization:key=<SERVER_KEY>"
    })
    @POST("fcm/send")
    Call<String> sendMessage(@Body String body) {
        return null;
    }
}

