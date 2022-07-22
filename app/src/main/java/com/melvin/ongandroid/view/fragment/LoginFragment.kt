package com.melvin.ongandroid.view.fragment

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.melvin.ongandroid.R
import com.melvin.ongandroid.data.AppData
import com.melvin.ongandroid.databinding.FragmentLogInBinding
import com.melvin.ongandroid.model.entities.LoginRequest
import com.melvin.ongandroid.viewmodel.ApiStatus
import com.melvin.ongandroid.viewmodel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {
    @Inject lateinit var appData : AppData
    private lateinit var _binding : FragmentLogInBinding
    private val loginBinding get() = _binding
    private val loginViewModel : LogInViewModel by viewModels()

    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient
    private lateinit var callbackManager : CallbackManager

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

        // Firebase Auth
        auth = FirebaseAuth.getInstance()
        //Google Auth
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)
        // Facebook Auth
        callbackManager = CallbackManager.Factory.create()

        //Bindings
        loginBinding.itPasswordDesign.isHelperTextEnabled = false
        loginBinding.itEmailDesign.isHelperTextEnabled = false

        loginBinding.itEmail.addTextChangedListener {
            loginBinding.bLogin.isEnabled =
                loginViewModel.validateEmail(loginBinding.itEmail.text.toString()) &&
                        loginViewModel.validatePassword(loginBinding.itPassword.text.toString())
            loginBinding.itEmailDesign.isErrorEnabled = false
            loginBinding.itPasswordDesign.isErrorEnabled = false
        }

        loginBinding.itPassword.addTextChangedListener {
            loginBinding.bLogin.isEnabled =
                loginViewModel.validateEmail(loginBinding.itEmail.text.toString()) &&
                        loginViewModel.validatePassword(loginBinding.itPassword.text.toString())
            loginBinding.itEmailDesign.isErrorEnabled = false
            loginBinding.itPasswordDesign.isErrorEnabled = false
        }

        loginBinding.bSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        loginBinding.bLogin.setOnClickListener {
            val logIn = LoginRequest(
                loginBinding.itEmail.text.toString(),
                loginBinding.itPassword.text.toString()
            )
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
        //Google Sign In
        loginBinding.bGoogleLogin.setOnClickListener {
            loginViewModel.loginWithSocialMedia("LOGIN_ACTION")
            signInGoogle()
        }
        //Facebook Sign In
        loginBinding.bFacebookLogin.setOnClickListener{
            loginViewModel.loginWithSocialMedia("LOGIN_ACTION")
            LoginManager.getInstance().logInWithReadPermissions(
                this,
                callbackManager,
                listOf("email", "public_profile"))
            signInFacebook()
        }

    }
    //Google Sign In Method
    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(StartActivityForResult()){
            result ->
                if(result.resultCode == Activity.RESULT_OK){
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    googleHandlerResult(task)
                }
    }

    private fun googleHandlerResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                loginWithGoogle(account)
            }
        } else {
            loginViewModel.loginWithSocialMedia("FAILED_LOGIN")
            Toast.makeText(requireContext(), R.string.social_media_login_error, Toast.LENGTH_LONG).show()
         }
    }

    private fun loginWithGoogle(account: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credentials).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                loginViewModel.loginWithSocialMedia("LOGGED_IN")
                //Save data from login
                account.displayName?.let { appData.savePrefs("username", it) }
                account.email?.let { appData.savePrefs("email", it) }
                appData.savePrefs("key", "loggedWithGoogle")
                //Successful login
                showSuccessDialog()
            }else{
                showFailureDialog()
            }
        }
    }

    //Facebook Sign In Method
    private fun signInFacebook() {
        LoginManager.getInstance().registerCallback(callbackManager,
        object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult) {
                handleFacebookAccessToken(result)
            }

            override fun onCancel() {
                loginViewModel.loginWithSocialMedia("FAILED_LOGIN")
                Toast.makeText(requireContext(), R.string.social_media_login_error, Toast.LENGTH_LONG).show()
            }

            override fun onError(error: FacebookException) {
                loginViewModel.loginWithSocialMedia("FAILED_LOGIN")
                Toast.makeText(requireContext(), R.string.social_media_login_error, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun handleFacebookAccessToken(result: LoginResult) {
        val accessToken = result.accessToken
        val credentials = FacebookAuthProvider.getCredential(accessToken.token)
        auth.signInWithCredential(credentials).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                loginViewModel.loginWithSocialMedia("LOGGED_IN")
                val user = auth.currentUser
                user?.let { firebaseUser ->
                    firebaseUser.displayName?.let { appData.savePrefs("username", it) }
                    firebaseUser.email?.let { appData.savePrefs("email", it) }
                }
                appData.savePrefs("key", "loggedWithFacebook")
                showSuccessDialog()
            }else{
                showFailureDialog()
            }
        }
    }

    //Dialog Methods
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
