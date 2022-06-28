package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentSignUpBinding
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import com.melvin.ongandroid.viewmodel.ApiStatus
import com.melvin.ongandroid.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.validateFields(binding.etUserName.text.toString(),binding.etUserPassword.text.toString(),
            binding.etUserEmail.text.toString(),binding.etUserPasswordConfirm.text.toString())

        binding.btnSignUp.setOnClickListener {
            val newUser = UserRegistrationRequest(
                binding.tiUsername.toString(),
                binding.tiUserEmail.toString(),
                binding.tiUserPassword.toString()
            )
            viewModel.registerUser(newUser)
            drawStatusDialog()
        }

        val binding = FragmentSignUpBinding.bind(view)

        binding.etUserPasswordConfirm.doAfterTextChanged {
            val controlFields = viewModel.validateFields(binding.etUserName.text.toString(),binding.etUserPassword.text.toString(),
                binding.etUserEmail.text.toString(),binding.etUserPasswordConfirm.text.toString())
            if (controlFields){
                binding.btnSignUp.isEnabled = true
            }
        }

        viewModel.signUpUserCharging.observe( viewLifecycleOwner) { charging ->
            if (charging) {
                binding.pbSignUp.visibility=View.VISIBLE
            } else {
                binding.pbSignUp.visibility=View.GONE
            }
        }

    }

    private fun drawStatusDialog() {
        viewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                ApiStatus.SUCCESS -> { showSuccessDialog() }
                ApiStatus.FAILURE -> { showFailureDialog() }
            }
        }
    }

    private fun showSuccessDialog() {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.success_dialog_title))
                .setMessage(resources.getString(R.string.success_supporting_text))
                .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                }
                .show()
        }
    }

    private fun showFailureDialog() {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.failure_dialog_title))
                .setMessage(resources.getString(R.string.failure_supporting_text))
                .setPositiveButton(resources.getString(R.string.close)) { _, _ ->
                    Log.d("SignUpFragment", "close")
                }
                .show()
        }
    }
}


