package app.ecoride_agent.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.ecoride_agent.R
import app.ecoride_agent.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var settingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

    }

    override fun onStart() {
        super.onStart()

        settingsBinding.settingsBack.setOnClickListener {
            this@SettingsActivity.onBackPressed()
        }
    }
}