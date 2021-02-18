package app.ecoride_agent

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import app.ecoride_agent.Ecoride.*
import app.ecoride_agent.databinding.ActivityMainBinding
import app.ecoride_agent.ui.profile.ProfileActivity
import app.ecoride_agent.utils.ConnectivityReceiver
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.tasks.Task
import timber.log.Timber


class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener, ConnectivityReceiver.ConnectivityReceiverListener {

    private lateinit var mainBinding: ActivityMainBinding

    private val mDefaultLocation = LatLng(13.077032, 80.236646)

    private var mLocationPermissionGranted = false

    private val mFusedLocationProviderClient: FusedLocationProviderClient? = null

    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()

        mainBinding.drawerController.setOnClickListener {
            openCloseNavigationDrawer()
        }
        mainBinding.navView.bringToFront()


        val map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        map.apply {
            getMapAsync(this@MainActivity)
            onCreate(null)
            onResume()
        }

        mainBinding.navView.findViewById<RelativeLayout>(R.id.avatar)
            .setOnClickListener {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

    }

    private fun openCloseNavigationDrawer() {
        if (mainBinding.mainDrawer.isDrawerOpen(GravityCompat.START)){
            mainBinding.mainDrawer.closeDrawer(GravityCompat.START)
        }else{
            mainBinding.mainDrawer.openDrawer(GravityCompat.START)
        }
    }


    override fun onMapReady(p0: GoogleMap?) {

        if (p0 != null) {
            googleMap = p0
        }

        try {
            googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.style_json
                )
            )
        } catch (e: Resources.NotFoundException) {
            Timber.d("Can't find style. Error: ")
        }

        getLocationPermission()
        updateLocationUI()
        getDeviceLocation()

    }

    override fun onCameraMove() {

    }

    override fun onCameraIdle() {

    }


    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    private fun updateLocationUI() {
        try {
            if (mLocationPermissionGranted) {
                googleMap.isMyLocationEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = false
                googleMap.setOnCameraMoveListener(this)
                googleMap.setOnCameraIdleListener(this)
            } else {
                googleMap.isMyLocationEnabled = false
                googleMap.uiSettings.isMyLocationButtonEnabled = false
                mLastKnownLocation = null
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Timber.e("Exception: %s", e.message)
        }
    }

    private fun getDeviceLocation() {
        try {
            if (mLocationPermissionGranted) {
                mFusedLocationProviderClient?.lastLocation?.addOnCompleteListener(
                    this
                ) { task: Task<Location?> ->
                    if (task.isSuccessful && task.result != null) {
                        mLastKnownLocation = task.result
                        googleMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(
                                    mLastKnownLocation.latitude,
                                    mLastKnownLocation.longitude
                                ), 10F
                            )
                        )

                    } else {
                        Timber.d("Current location is null. Using defaults.")
                        Timber.e(task.exception)
                        googleMap.animateCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(mDefaultLocation, 10F)
                        )
                        googleMap.uiSettings.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Timber.e(e)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        mLocationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true
                    updateLocationUI()
                    getDeviceLocation()
                }
            }
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected)
    }

    private fun showSnack(isConnected: Boolean) {
        if (isConnected) {
            mainBinding.contentMain.findViewById<LinearLayout>(R.id.lnrNoInternet).visibility = View.GONE
        } else {
            mainBinding.contentMain.findViewById<LinearLayout>(R.id.lnrNoInternet).visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        this.moveTaskToBack(true)

    }
}