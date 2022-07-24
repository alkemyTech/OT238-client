package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentContactBinding
import com.melvin.ongandroid.model.entities.contact.Contact
import com.melvin.ongandroid.viewmodel.ApiStatus
import com.melvin.ongandroid.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : Fragment() {
    private val contactViewModel: ContactViewModel by viewModels()
    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.menu_contact_us)
        binding.apply {
            etName.addTextChangedListener{
                checkPlaces()
            }
            etEmail.addTextChangedListener {
                checkPlaces()
            }
            etMessage.addTextChangedListener {
                checkPlaces()
            }
            etPhoneNumber.addTextChangedListener {
                checkPlaces()
            }
            btnSend.setOnClickListener{
                val newContact= Contact(
                        etName.text.toString(),
                        etEmail.text.toString(),
                        etPhoneNumber.text.toString(),
                        etMessage.text.toString())
                contactViewModel.createContact(newContact)
            }

            contactViewModel.status.observe(viewLifecycleOwner) { currentStatus ->
                when (currentStatus) {
                    ApiStatus.SUCCESS -> {
                        binding.pbChargingContact.hideProgressBar()
                        showSuccessDialog()
                    }
                    ApiStatus.FAILURE -> {
                        binding.pbChargingContact.hideProgressBar()
                        showFailure()
                    }
                    ApiStatus.LOADING -> binding.pbChargingContact.showProgressBar()
                }
            }
        }
    }


    private fun checkPlaces(){
        binding.apply {
            btnSend.isEnabled = contactViewModel.validateName(etName.text.toString()) &&
                    contactViewModel.validateEmail(etEmail.text.toString()) &&
                    contactViewModel.validateNumber(etPhoneNumber.text.toString()) &&
                    contactViewModel.validateMessage(etMessage.text.toString())
            tiNameAndSurname.isErrorEnabled=false
            tiEmail.isErrorEnabled=false
            tiMessage.isErrorEnabled=false
            tiPhone.isErrorEnabled=false
        }
    }


    private fun showSuccessDialog() {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.successful_operation))
                .setMessage(resources.getString(R.string.success_contact_creation))
                .setPositiveButton(resources.getString(R.string.close)) { _, _ ->
                }
                .show()
        }
        binding.apply {
            etName.setText("")
            etEmail.setText("")
            etMessage.setText("")
            etPhoneNumber.setText("")
        }
    }

    private fun showFailure() {
        binding.apply {
            tiNameAndSurname.error = getString(R.string.set_error)
            tiEmail.error = getString(R.string.set_error)
            tiMessage.error = getString(R.string.set_error)
            tiPhone.error= getString(R.string.set_error)
        }
    }


}