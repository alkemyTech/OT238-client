package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentLogInBinding

class LoginFragment : Fragment() {

    private lateinit var loginBinding : FragmentLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        loginBinding.bSignUp.setOnClickListener{

            //call fragment sign up

        }


    }


}