package com.melvin.ongandroid.view.fragment
import android.view.View
import androidx.fragment.app.Fragment
import com.melvin.ongandroid.databinding.FragmentHomeBinding


fun Fragment.showProgressBar(view: View){
    var binding: FragmentHomeBinding?=null
    binding = FragmentHomeBinding.bind(view)
    binding.pbGeneralCharging.visibility=View.VISIBLE
}

fun Fragment.hideProgressBar(view: View){
    var binding: FragmentHomeBinding?=null
    binding = FragmentHomeBinding.bind(view)
    binding.pbGeneralCharging.visibility=View.GONE
}