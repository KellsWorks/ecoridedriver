package app.ecoride_agent

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import app.ecoride_agent.databinding.ActivityMainBinding
import app.ecoride_agent.ui.documents.DocumentsActivity
import app.ecoride_agent.ui.earnings.EarningsActivity
import app.ecoride_agent.ui.profile.ProfileActivity
import app.ecoride_agent.ui.settings.SettingsActivity
import app.ecoride_agent.ui.summary.SummaryActivity
import app.ecoride_agent.ui.trips.TripsActivity


class MainActivity : AppCompatActivity(){

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

        mainBinding.navView.findViewById<RelativeLayout>(R.id.avatar)
            .setOnClickListener {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

        val navigator = mainBinding.navView

        val home = navigator.findViewById<LinearLayout>(R.id.toHome)
        val earnings = navigator.findViewById<LinearLayout>(R.id.toWallet)
        val trips = navigator.findViewById<LinearLayout>(R.id.toTrips)
        val summary = navigator.findViewById<LinearLayout>(R.id.toSummary)
        val documents = navigator.findViewById<LinearLayout>(R.id.toDocuments)
        val settings = navigator.findViewById<LinearLayout>(R.id.toSettings)

        home.setOnClickListener {
            navigator.bringToFront()
            openCloseNavigationDrawer()
        }

        earnings.setOnClickListener {

            navigator.bringToFront()

            startActivity(
                Intent(
                    this@MainActivity, EarningsActivity::class.java
                )
            )
            overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
            openCloseNavigationDrawer()
        }

        trips.setOnClickListener {
            navigator.bringToFront()

            startActivity(
                Intent(
                    this@MainActivity, TripsActivity::class.java
                )
            )
            overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
            openCloseNavigationDrawer()
        }

        summary.setOnClickListener {
            navigator.bringToFront()

            startActivity(
                Intent(
                    this@MainActivity, SummaryActivity::class.java
                )
            )
            overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
            openCloseNavigationDrawer()
        }

        documents.setOnClickListener {
            navigator.bringToFront()

            startActivity(
                Intent(
                    this@MainActivity, DocumentsActivity::class.java
                )
            )
            overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
            openCloseNavigationDrawer()
        }

        settings.setOnClickListener {
            navigator.bringToFront()

            startActivity(
                Intent(
                    this@MainActivity, SettingsActivity::class.java
                )
            )
            overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
            openCloseNavigationDrawer()
        }

    }

    private fun openCloseNavigationDrawer() {
        if (mainBinding.mainDrawer.isDrawerOpen(GravityCompat.START)){
            mainBinding.mainDrawer.closeDrawer(GravityCompat.START)
        }else{
            mainBinding.mainDrawer.openDrawer(GravityCompat.START)
        }
    }




    override fun onBackPressed() {
        super.onBackPressed()

        this.moveTaskToBack(true)

    }
}