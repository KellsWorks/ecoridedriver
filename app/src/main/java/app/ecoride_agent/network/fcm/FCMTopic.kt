package app.ecoride_agent.network.fcm

import android.content.Context
import app.ecoride_agent.helpers.SharedHelper
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

class FCMTopic(userId: Int) {

    private val apiClient = ApiClient.client?.create(ApiInterface::class.java)
    private val getFMCTopic : Call<FCMResponse>? = apiClient?.getFCMTopic(userId)

    fun getTopic(context: Context, userId: Int){
        GlobalScope.launch(Dispatchers.IO) {
            getFMCTopic?.enqueue(object: Callback<FCMResponse>{
                override fun onResponse(call: Call<FCMResponse>, response: Response<FCMResponse>) {
                    when(response.code()){
                        200->{
                            SharedHelper.putKey(context, "f_c_m_topic", response.body()?.topic)
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