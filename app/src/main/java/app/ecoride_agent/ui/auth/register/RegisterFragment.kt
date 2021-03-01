package app.ecoride_agent.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.ecoride_agent.R
import app.ecoride_agent.databinding.FragmentRegisterBinding
import app.ecoride_agent.helpers.SharedHelper
import es.dmoral.toasty.Toasty

class RegisterFragment : Fragment() {

    private lateinit var registerBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        registerBinding.lifecycleOwner = this

        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerBinding.toVerifyOTP.setOnClickListener {
            if (validate()){
                SharedHelper.putKey(requireContext(), "register_phone", registerBinding.registerPhoneGet.text.toString())
                findNavController().navigate(R.id.action_registerFragment_to_registerDetailsFragment)
            }
        }
        registerBinding.registerClose.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun validate() : Boolean{
        return if (registerBinding.registerPhoneGet.text?.length != 9){
            Toasty.warning(requireContext(), "Enter a 9 digit number").show()

            false
        }else
            true
    }
}