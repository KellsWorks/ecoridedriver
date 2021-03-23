package app.ecoride_agent.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.ecoride_agent.MainActivity
import app.ecoride_agent.R
import app.ecoride_agent.customs.CustomProgressDialog
import app.ecoride_agent.databinding.FragmentLoginBinding
import app.ecoride_agent.helpers.SharedHelper
import app.ecoride_agent.network.ApiClient
import app.ecoride_agent.network.ApiInterface
import app.ecoride_agent.network.responses.login.Login
import es.dmoral.toasty.Toasty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        loginBinding.lifecycleOwner = this

        return loginBinding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loginBinding.createAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        loginBinding.login.setOnClickListener {
            if (validate()){
                login(
                        loginBinding.loginPhone.text.toString(),
                        loginBinding.loginPassword.text.toString()
                )
            }
        }

    }

    private fun validate() : Boolean{

        if (loginBinding.loginPhone.text.toString().length != 10){
            Toasty.warning(requireContext(), "Enter a 10 digit number").show()
            return false
        }

        if (loginBinding.loginPassword.text.toString().isEmpty()){
            Toasty.warning(requireContext(), "Enter your password").show()
            return false
        }

        return true
    }

    private fun login(phone : String, password: String){

        val api = ApiClient.client?.create(ApiInterface::class.java)
        val login : Call<Login> = api!!.login(phone, password)

        val progressDialog = CustomProgressDialog()
        progressDialog.show(requireContext(), "Please wait...")

        login.enqueue(object : Callback<Login>{
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                progressDialog.dialog.dismiss()
                when(response.code()){
                    200->{
                        SharedHelper.putKey(requireContext(), "user_id", response.body()?.data?.profile?.get(0)?.user_id)
                        SharedHelper.putKey(requireContext(), "profile_id", response.body()?.data?.profile?.get(0)?.id)
                        SharedHelper.putKey(requireContext(), "firebase_id", response.body()?.data?.firebase?.get(0)?.id)
                        SharedHelper.putKey(requireContext(), "username", response.body()?.data?.name)
                        SharedHelper.putKey(requireContext(), "phone", response.body()?.data?.phone)
                        SharedHelper.putKey(requireContext(), "email", response.body()?.data?.email)
                        SharedHelper.putKey(requireContext(), "remember_token", response.body()?.data?.token)
                        SharedHelper.putKey(requireContext(), "firebase_topic", response.body()?.data?.firebase?.get(0)?.topic)
                        SharedHelper.putKey(requireContext(), "user_photo", response.body()?.data?.profile?.get(0)?.photo)
                        SharedHelper.putKey(requireContext(), "role", response.body()?.data?.profile?.get(0)?.role)
                        SharedHelper.putKey(requireContext(), "emergency", response.body()?.data?.profile?.get(0)?.emergency)

                        requireActivity()
                                .startActivity(
                                        Intent(
                                                requireActivity(),
                                                MainActivity::class.java
                                        )
                                )
                        requireActivity()
                                .overridePendingTransition(
                                        android.R.anim.fade_in,
                                        android.R.anim.fade_out
                                )
                    }else->{
                    Timber.e(response.errorBody()?.string())
                        Toasty.error(requireContext(), "A general error occurred").show()
                    }
                }
            }

            override fun onFailure(call: Call<Login>, t: Throwable) {
                progressDialog.dialog.dismiss()
                Timber.e(t)
                Toasty.error(requireContext(), "An error occurred").show()
            }

        })
    }

}