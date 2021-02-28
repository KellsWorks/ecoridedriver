package app.ecoride_agent.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.ecoride_agent.MainActivity
import app.ecoride_agent.ui.auth.AuthenticationActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(
            Intent(
                this@SplashActivity, AuthenticationActivity::class.java
            )
        )
    }
}