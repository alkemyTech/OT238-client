package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.melvin.ongandroid.databinding.FragmentSignUpBinding
import com.melvin.ongandroid.viewmodel.SignUpViewModel


class SignUpFragment: Fragment() {
    private lateinit var binding: FragmentSignUpBinding
     private val signUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


/*        viewModel.validateFields(
            binding.tiUsername,
            binding.tiUserEmail,
            binding.tiUserPassword,
            binding.tiUserPasswordConfirm
        )
        viewModel.validateEmail(binding.tiUserEmail)
        viewModel.validatePassword()
    }*/
    }
}

