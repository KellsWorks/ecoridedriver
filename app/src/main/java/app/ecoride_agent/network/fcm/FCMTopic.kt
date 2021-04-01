package app.ecoride_agent.network.fcm

import app.ecoride_agent.network.ApiClient
import app.ecoride_agent.network.ApiInterface
import app.ecoride_agent.network.responses.fcm.FCMResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class FCMTopic(userId: Int, token: String) {

    private val apiClient = ApiClient.client?.create(ApiInterface::class.java)
    private val getFMCTopic : Call<FCMResponse>? = apiClient?.sendTokenToServer(userId, token)

    fun sendToken() {
        GlobalScope.launch(Dispatchers.IO) {
            getFMCTopic?.enqueue(object: Callback<FCMResponse>{
                override fun onResponse(call: Call<FCMResponse>, response: Response<FCMResponse>) {
                    when(response.code()){
                        200->{
                            Timber.e("Token sent to server")
                        }else->{
                            Timber.e(response.errorBody()?.string())
                        }
                    }
                }

                override fun onFailure(call: Call<FCMResponse>, t: Throwable) {
                    Timber.e(t)
                }

            })
        }
    }
}