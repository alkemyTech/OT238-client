package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentLogInBinding
import com.melvin.ongandroid.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var loginBinding : FragmentLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private val loginViewModel= LoginViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBinding= FragmentLogInBinding.bind(view)

        loginBinding.itEmail.addTextChangedListener{
            loginBinding.bLogin.isEnabled = loginViewModel.validateEmail(loginBinding.itEmail.text.toString()) &&
                    loginViewModel.validatePassword(loginBinding.itPassword.text.toString())
        }

        loginBinding.itPassword.addTextChangedListener{
            loginBinding.bLogin.isEnabled = loginViewModel.validateEmail(loginBinding.itEmail.text.toString()) &&
                    loginViewModel.validatePassword(loginBinding.itPassword.text.toString())
        }

        loginBinding.bSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        
        loginBinding.bLogin.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

    }
}