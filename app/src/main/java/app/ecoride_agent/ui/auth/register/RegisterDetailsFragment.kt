package app.ecoride_agent.ui.auth.register

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import app.ecoride_agent.R
import app.ecoride_agent.databinding.FragmentRegisterDetailsBinding


class RegisterDetailsFragment : Fragment() {

    private lateinit var registerDetailsBinding: FragmentRegisterDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        registerDetailsBinding = FragmentRegisterDetailsBinding.inflate(inflater, container, false)
        registerDetailsBinding.lifecycleOwner = this

        return registerDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerDetailsBinding.contiueRegister.setOnClickListener {
            registerDetailsBinding.registerGo.visibility = View.GONE
            registerDetailsBinding.cpPbar.visibility = View.VISIBLE
            Handler().postDelayed({
                findNavController().navigate(R.id.action_registerDetailsFragment_to_OTPVerificationFragment)
            }, 8000)
        }

        registerDetailsBinding.registerDetailsBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}