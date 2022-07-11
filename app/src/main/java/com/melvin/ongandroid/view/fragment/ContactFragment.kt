package com.melvin.ongandroid.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentContactBinding
import com.melvin.ongandroid.databinding.FragmentWhatwedoBinding
import com.melvin.ongandroid.viewmodel.ContactViewModel

class ContactFragment : Fragment() {

    private  var viewModel=ContactViewModel ()
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
        }
    }

    private fun checkPlaces(){
        binding.apply {
            btnSend.isEnabled = viewModel.validateName(etName.text.toString()) &&
                    viewModel.validateEmail(etEmail.text.toString()) &&
                    viewModel.validateNumber(etPhoneNumber.text.toString()) &&
                    viewModel.validateMessage(etMessage.text.toString())
        }
    }




}