package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.melvin.ongandroid.viewmodel.SignUpViewModel

class SignUpFragment: Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel : SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSignUp.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.validateFields(
            binding.tiUsername,
            binding.tiUserEmail,
            binding.tiUserPassword,
            binding.tiUserPasswordConfirm
        )
        viewModel.validateEmail(binding.tiUserEmail)
        viewModel.validatePassword()
    }
}