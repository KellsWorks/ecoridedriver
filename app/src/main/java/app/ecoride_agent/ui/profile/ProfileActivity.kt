package app.ecoride_agent.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.ecoride_agent.R
import app.ecoride_agent.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileBinding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
    }

    override fun onStart() {
        super.onStart()

        profileBinding.profileBack.setOnClickListener {
            onBackPress()
            }
    }

    private fun onBackPress(){
        this.onBackPressed()
    }
}