package com.melvin.ongandroid.view.fragment
import android.view.View
import androidx.fragment.app.Fragment
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.databinding.FragmentUsBinding


fun Fragment.showProgressBarHome(view: View){
    var binding: FragmentHomeBinding? = null
    binding = FragmentHomeBinding.bind(view)
    binding.pbGeneralCharging.visibility=View.VISIBLE
}

fun Fragment.hideProgressBarHome(view: View){
    var binding: FragmentHomeBinding? = null
    binding = FragmentHomeBinding.bind(view)
    binding.pbGeneralCharging.visibility=View.GONE
}

fun Fragment.showProgressBarUs(view: View){
    var binding: FragmentUsBinding? = null
    binding = FragmentUsBinding.bind(view)
    binding.pbChargingUs.visibility=View.VISIBLE
}

fun Fragment.hideProgressBarUs(view: View){
    var binding: FragmentUsBinding? = null
    binding = FragmentUsBinding.bind(view)
    binding.pbChargingUs.visibility=View.GONE
}