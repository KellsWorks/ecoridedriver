package app.ecoride_agent.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.ecoride_agent.R
import app.ecoride_agent.databinding.FragmentOTPVerificationBinding


class OTPVerificationFragment : Fragment() {

    private lateinit var otpVerificationBinding: FragmentOTPVerificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        otpVerificationBinding = FragmentOTPVerificationBinding.inflate(inflater, container, false)
        otpVerificationBinding.lifecycleOwner = this

        return otpVerificationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        
    }

}