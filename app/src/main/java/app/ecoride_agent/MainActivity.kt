package app.ecoride_agent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.findFragment
import app.ecoride_agent.databinding.ActivityMainBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

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
}