package app.ecoride_agent.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.ecoride_agent.MainActivity
import app.ecoride_agent.helpers.SharedHelper
import app.ecoride_agent.ui.auth.AuthenticationActivity
import app.ecoride_agent.ui.onboard.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (SharedHelper.getKey(this, "isFirstTime") == ""){
            startActivity(
                Intent(
                    this@SplashActivity, OnBoardingActivity::class.java
                )
            )
        }else{

            if (!SharedHelper.getKey(this, "token").isNullOrEmpty()){
                startActivity(Intent(
                    this, MainActivity::class.java
                ))
            }
            else{
                startActivity(
                    Intent(
                        this@SplashActivity, AuthenticationActivity::class.java
                    )
                )
            }

        }
    }

}