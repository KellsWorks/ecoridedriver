package app.ecoride_agent.broadcasts

import app.ecoride_agent.network.ApiClient
import app.ecoride_agent.network.ApiInterface
import app.ecoride_agent.network.responses.GeneralResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class BroadcastLocation  {

    fun broadcastLocation(id: Int, lat: Double, long: Double){

        GlobalScope.launch (Dispatchers.IO){
            val apiClient = ApiClient.client!!.create(ApiInterface::class.java)
            val locationUpdate : Call<GeneralResponse> = apiClient.updateLocation(id, lat.toString(), long.toString())

            locationUpdate.enqueue(object : Callback<GeneralResponse>{
                override fun onResponse(
                    call: Call<GeneralResponse>,
                    response: Response<GeneralResponse>
                ) {
                    when(response.code()){
                        200 -> {
                            Timber.e("Geo data sent to ecoridemalawi.com server")
                        }else ->{
                            Timber.e("Unknown response")
                        }
                    }
                }

                override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                    Timber.e(t)
                }

            })
        }

    }

}