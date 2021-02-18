package app.ecoride_agent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import app.ecoride_agent.databinding.ActivityMainBinding
import app.ecoride_agent.ui.profile.ProfileActivity
import app.ecoride_agent.utils.ConnectivityReceiver
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment


class MainActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraIdleListener, ConnectivityReceiver.ConnectivityReceiverListener {

    private lateinit var mainBinding: ActivityMainBinding


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

    private fun closeNavigationDrawer(){
        mainBinding.mainDrawer.closeDrawer(GravityCompat.START)
    }

    override fun onMapReady(p0: GoogleMap?) {

    }

    override fun onCameraMove() {

    }

    override fun onCameraIdle() {

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
}