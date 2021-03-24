package app.ecoride_agent.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.ecoride_agent.R
import app.ecoride_agent.databinding.ActivityProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

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

        profileBinding.changeProfile.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = dialog.layoutInflater.inflate(R.layout.dialog_profile, null)
            dialog.setContentView(view)
            dialog.dismissWithAnimation = true
            dialog.show()
        }
    }

    private fun onBackPress(){
        this.onBackPressed()
    }
}