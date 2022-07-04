package com.melvin.ongandroid.view.fragment
import android.view.View
import androidx.fragment.app.Fragment
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.databinding.FragmentUsBinding


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

fun Fragment.showProgressBar2(view: View){
    var binding: FragmentUsBinding?=null
    binding = FragmentUsBinding.bind(view)
    binding.pbChargingUs.visibility=View.VISIBLE
}

fun Fragment.hideProgressBar2(view: View){
    var binding: FragmentUsBinding?=null
    binding = FragmentUsBinding.bind(view)
    binding.pbChargingUs.visibility=View.GONE
}