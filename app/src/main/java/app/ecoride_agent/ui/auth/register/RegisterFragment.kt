package app.ecoride_agent.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.ecoride_agent.customs.CustomProgressDialog
import app.ecoride_agent.databinding.FragmentRegisterBinding
import app.ecoride_agent.helpers.SharedHelper
import app.ecoride_agent.network.ApiClient
import app.ecoride_agent.network.ApiInterface
import app.ecoride_agent.network.responses.register.Register
import es.dmoral.toasty.Toasty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

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

        registerBinding.continueRegister.setOnClickListener{
            if(validate()){
                registerUser(
                    registerBinding.registerUsername.text.toString(),
                    registerBinding.registerPhone.text.toString(),
                    registerBinding.registerEmail.text.toString(),
                    registerBinding.registerPassword.text.toString(),
                    registerBinding.registerConfirmPassword.text.toString(),
                    registerBinding.emergencyPhone.text.toString()
                )
            }
        }

        registerBinding.registerBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun registerUser(
        name: String,
        phone: String,
        email: String,
        password : String,
        c_password: String,
        emergency: String
    ) {

        val progressDialog = CustomProgressDialog()
        progressDialog.show(requireContext(), "Registering...")

        val api = ApiClient.client!!.create(ApiInterface::class.java)
        val register: Call<Register> = api.register(name, email, phone, "ecoride_agent", emergency, password, c_password)

        register.enqueue(object : Callback<Register?> {

            override fun onFailure(call: Call<Register?>, t: Throwable) {
                Timber.e(t)
                Toasty.error(requireContext(), "A network error occurred").show()
                progressDialog.dialog.dismiss()
            }

            override fun onResponse(call: Call<Register?>, response: Response<Register?>) {

                progressDialog.dialog.dismiss()

                when(response.code()){
                    200 ->{
                        SharedHelper.putKey(requireContext(), "remember_token", response.body()?.data?.token)
                        Toasty.success(requireContext(), response.body()!!.message).show()
                        findNavController().navigateUp()

                    }else->{
                    Timber.e(response.errorBody()!!.string())
                }
                }

            }

        })
    }

    private fun validate(): Boolean{

        if (registerBinding.registerUsername.text.toString().isEmpty()){

            registerBinding.registerUsernameLayout.isErrorEnabled = true
            registerBinding.registerUsernameLayout.error = "This field is required"

            return false
        }

        if (registerBinding.registerPhone.text!!.length != 10){

            Toasty.warning(requireContext(), "Phone number is required").show()

            return false
        }

        if (registerBinding.emergencyPhone.text!!.length != 10){

            registerBinding.emergencyPhoneLayout.isErrorEnabled = true
            registerBinding.emergencyPhoneLayout.error = "Enter a 10 digit number"

            return false
        }

        if (!registerBinding.registerEmail.text!!.contains("@")){

            registerBinding.registerEmailLayout.isErrorEnabled = true
            registerBinding.registerEmailLayout.error = "Enter a valid email addrerss"

            return false
        }
        if (registerBinding.registerPassword.text.toString() != registerBinding.registerConfirmPassword.text.toString() ){

            registerBinding.registerConfirmPasswordLayout.isErrorEnabled = true
            registerBinding.registerConfirmPasswordLayout.error = "Passwords do not match"

            return false
        }
        return true
    }

}