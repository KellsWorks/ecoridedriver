package app.ecoride_agent.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.ecoride_agent.Ecoride
import app.ecoride_agent.R
import app.ecoride_agent.customs.CustomMapMarker
import app.ecoride_agent.databinding.FragmentHomeBinding
import app.ecoride_agent.utils.GPSTracker
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import timber.log.Timber
import java.io.IOException


open class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mGoogleMap: GoogleMap

    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeBinding.lifecycleOwner = this

        return homeBinding.root
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeBinding.map.apply {
            onCreate(null)
            onResume()
            getMapAsync(this@HomeFragment)
        }

        homeBinding.btnGoOffline.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_offlineFragment)
        }

    }

    override fun onMapReady(map: GoogleMap?) {

        if (checkPermissions()){
            MapsInitializer.initialize(requireActivity())
            if (map != null) {
                mGoogleMap = map
            }
            mGoogleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            mGoogleMap.isTrafficEnabled = true
            mGoogleMap.isBuildingsEnabled = true
            mGoogleMap.isIndoorEnabled = true
            mGoogleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(),
                    app.ecoride_agent.R.raw.style_json
                )
            )

            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            getLastLocation()

            val sharedPreference: SharedPreferences? = context?.getSharedPreferences(
                "MapsMainLocation",
                Context.MODE_PRIVATE
            )

            val lat = sharedPreference?.getFloat("Latitude", 0F)
            val long = sharedPreference?.getFloat("Longitude", 0F)

            val sharedPreferences = context?.getSharedPreferences("routes", Context.MODE_PRIVATE)?.edit()
            sharedPreferences?.putString("start", "$lat,$long")
            sharedPreferences?.apply()

            val markerIcon = getMarkerIcon(
                root = (requireView().parent as ViewGroup),
                text = "You are currently here",
                image = app.ecoride_agent.R.drawable.rectangle_side,
                isSelected = true
            )

            mGoogleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(lat!!.toDouble(), long!!.toDouble()))
                    .icon(markerIcon)
            )

            val camera = CameraPosition.builder().target(LatLng(lat.toDouble(), long.toDouble())).zoom(
                16F
            ).bearing(0F).tilt(45F).build()
            mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera))


        }else{
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Timber.e("location checked")
            }else{
                requestPermissions()
            }

        }

        val serviceIntent = Intent(requireActivity(), GPSTracker::class.java)
        ContextCompat.startForegroundService(requireActivity(), serviceIntent)
    }

    open fun getLocationFromAddress(context: Context?, strAddress: String?): LatLng? {
        val coder = Geocoder(context)
        val address: List<Address>
        var p1: LatLng? = null
        try {
            address = coder.getFromLocationName(strAddress, 5)
            if (address == null) {
                return null
            }
            val location: Address = address[0]
            p1 = LatLng(location.latitude, location.longitude)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return p1
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            Ecoride.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
        )
    }


    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    return
                }
                mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        val latitude = location.latitude
                        val longitude = location.longitude

                        val preference: SharedPreferences? = context?.getSharedPreferences(
                            "MapsMainLocation",
                            Context.MODE_PRIVATE
                        )

                        val editor = preference?.edit()

                        editor?.putFloat("Latitude", latitude.toFloat())
                        editor?.putFloat("Longitude", longitude.toFloat())

                        editor?.apply()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            Timber.e(mLastLocation.latitude.toString())
            Timber.e(mLastLocation.longitude.toString())
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

}