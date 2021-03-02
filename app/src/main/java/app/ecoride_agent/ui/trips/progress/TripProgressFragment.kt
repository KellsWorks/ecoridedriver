package app.ecoride_agent.ui.trips.progress

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import app.ecoride_agent.Ecoride
import app.ecoride_agent.R
import app.ecoride_agent.customs.CustomMapMarker
import app.ecoride_agent.databinding.FragmentTripProgressBinding
import app.ecoride_agent.utils.GoogleMapDTO
import app.ecoride_agent.utils.JavaUtils
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class TripProgressFragment : Fragment() , OnMapReadyCallback{

    private lateinit var tripsProgressBinding: FragmentTripProgressBinding

    lateinit var mGoogleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        tripsProgressBinding = FragmentTripProgressBinding.inflate(inflater, container, false)
        tripsProgressBinding.lifecycleOwner = this

        return tripsProgressBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tripsProgressBinding.progressMap.onCreate(this.arguments)
        tripsProgressBinding.progressMap.getMapAsync(this)
        tripsProgressBinding.progressMap.onResume()

    }

    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            mGoogleMap = p0
        }

        mGoogleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mGoogleMap.isTrafficEnabled = true
        mGoogleMap.isBuildingsEnabled = true
        mGoogleMap.isIndoorEnabled = true
        mGoogleMap.setMinZoomPreference(Ecoride.DEFAULT_ZOOM)
        mGoogleMap.setMaxZoomPreference(Ecoride.DEFAULT_ZOOM)
        mGoogleMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireActivity(), R.raw.style_json))

        val icon = BitmapDescriptorFactory.fromResource(R.drawable.user_marker)

        val markerOptionsFour = MarkerOptions().position(LatLng(-15.809818349256762, 35.059702333613906))
            .icon(icon)

        mGoogleMap.addMarker(markerOptionsFour)

        val cameraUpdate: CameraUpdate =
            CameraUpdateFactory.newLatLngZoom(LatLng(-15.809818349256762, 35.059702333613906), Ecoride.DEFAULT_ZOOM)
        mGoogleMap.animateCamera(cameraUpdate)
    }

    private fun getMarkerIcon(root: ViewGroup, text: String?, image: Int, isSelected: Boolean): BitmapDescriptor? {
        val markerView = CustomMapMarker(root, text, image, isSelected)
        markerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        markerView.layout(0, 0, markerView.measuredWidth, markerView.measuredHeight)
        markerView.isDrawingCacheEnabled = true
        markerView.invalidate()
        markerView.buildDrawingCache(false)
        return BitmapDescriptorFactory.fromBitmap(markerView.drawingCache)
    }

    fun getDirectionURL(origin:LatLng, dest:LatLng) : String{
        val apiKey =  getString(R.string.maps_api_key)
        return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}&destination=${dest.latitude},${dest.longitude}&sensor=false&mode=driving&key=$apiKey"
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }



    @SuppressLint("StaticFieldLeak")
    inner class GetDirection(val url : String) : AsyncTask<Void, Void, List<List<LatLng>>>(){
        override fun doInBackground(vararg params: Void?): List<List<LatLng>> {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val result =  ArrayList<List<LatLng>>()


            val response = client.newCall(request).execute()
            try{
                val data = response.body!!.string()

                val respObj = Gson().fromJson(data, GoogleMapDTO::class.java)

                val path =  ArrayList<LatLng>()

                for (i in 0 until respObj.routes[0].legs[0].steps.size){
                    path.addAll(decodePolyline(respObj.routes[0].legs[0].steps[i].polyline.points))
                }
                result.add(path)
            }catch (e:Exception){
                e.printStackTrace()
            }


            return result
        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onPostExecute(result: List<List<LatLng>>) {
            val lineoption = PolylineOptions()

            for (i in result.indices){
                lineoption.addAll(result[i])
                lineoption.width(10f)
                lineoption.pattern(JavaUtils().dotted)
                lineoption.color(context!!.getColor(R.color.blue))
                lineoption.geodesic(true)
            }
            mGoogleMap.addPolyline(lineoption)
        }
    }

    fun decodePolyline(encoded: String): List<LatLng> {

        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val latLng = LatLng((lat.toDouble() / 1E5),(lng.toDouble() / 1E5))
            poly.add(latLng)
        }

        return poly
    }

}