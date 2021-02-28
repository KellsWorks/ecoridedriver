package app.ecoride_agent.ui.auth.register

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.ecoride_agent.R
import app.ecoride_agent.databinding.FragmentOTPVerificationBinding


class OTPVerificationFragment : Fragment() {

    private lateinit var otpVerificationBinding: FragmentOTPVerificationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        otpVerificationBinding = FragmentOTPVerificationBinding.inflate(inflater, container, false)
        otpVerificationBinding.lifecycleOwner = this

        return otpVerificationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timer = object: CountDownTimer(20000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val timeResult = "${(millisUntilFinished / 1000 / 60).toString().padStart(2, '0')}:" + "${(millisUntilFinished / 1000 % 60).toString().padStart(2, '0')} "
                "Resend code in: $timeResult".also { otpVerificationBinding.resendTimer.text = it }
            }

            override fun onFinish() {
                otpVerificationBinding.resendTimer.text = getString(R.string.resend)
            }
        }
        timer.start()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().navigateUp()
        }
        onBackPressed()

    }

    private fun onBackPressed(){

        otpVerificationBinding.otpClose.setOnClickListener {
            findNavController().navigateUp()
        }

    }

}