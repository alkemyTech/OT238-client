package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentLogInBinding
import com.melvin.ongandroid.model.entities.LoginRequest
import com.melvin.ongandroid.viewmodel.ApiStatus
import com.melvin.ongandroid.viewmodel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var _binding : FragmentLogInBinding
    private val loginBinding get() = _binding
    private val loginViewModel : LogInViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater,container,false)
        return loginBinding.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBinding.itPasswordDesign.isHelperTextEnabled = false
        loginBinding.itEmailDesign.isHelperTextEnabled = false

        loginBinding.itEmail.addTextChangedListener{
            loginBinding.bLogin.isEnabled = loginViewModel.validateEmail(loginBinding.itEmail.text.toString()) &&
                    loginViewModel.validatePassword(loginBinding.itPassword.text.toString())
            loginBinding.itEmailDesign.isErrorEnabled = false
            loginBinding.itPasswordDesign.isErrorEnabled = false
        }

        loginBinding.itPassword.addTextChangedListener{
            loginBinding.bLogin.isEnabled = loginViewModel.validateEmail(loginBinding.itEmail.text.toString()) &&
                    loginViewModel.validatePassword(loginBinding.itPassword.text.toString())
            loginBinding.itEmailDesign.isErrorEnabled = false
            loginBinding.itPasswordDesign.isErrorEnabled = false
        }

        loginBinding.bSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        
        loginBinding.bLogin.setOnClickListener{
            val logIn = LoginRequest(
                loginBinding.itEmail.text.toString(),
                loginBinding.itPassword.text.toString())
            loginViewModel.logInUser(logIn)
            drawStatusDialog()
            }

        loginViewModel.logInUserCharging.observe(viewLifecycleOwner) { charging ->
            if (charging) {
                loginBinding.pbCharging.visibility = View.VISIBLE
            } else {
                loginBinding.pbCharging.visibility = View.GONE
            }
        }

        loginBinding.bGoogleLogin.setOnClickListener{
            val googleConfig = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(requireActivity(), googleConfig)
            
        }

    }

        private fun drawStatusDialog() {
            loginViewModel.status.observe(viewLifecycleOwner) {
                when (it!!) {
                    ApiStatus.SUCCESS -> { showSuccessDialog() }
                    ApiStatus.FAILURE -> { showFailureDialog() }
                }
            }
        }

        private fun showSuccessDialog() {
            Toast.makeText(context, resources.getString(R.string.success_login), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
        }

        private fun showFailureDialog() {
            context?.let {
                MaterialAlertDialogBuilder(it)
                    .setTitle(resources.getString(R.string.failure_dialog_title))
                    .setMessage(resources.getString(R.string.failure_supporting_text))
                    .setPositiveButton(resources.getString(R.string.close)) { _, _ ->
                        Log.d("LogInFragment", "close")
                    }
                    .show()
                loginBinding.itPasswordDesign.error = getString(R.string.set_error)
                loginBinding.itEmailDesign.error = getString(R.string.set_error)
            }
        }

}