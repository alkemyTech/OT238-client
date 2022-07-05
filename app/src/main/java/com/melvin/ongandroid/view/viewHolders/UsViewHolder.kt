package com.melvin.ongandroid.view.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.databinding.FragmentUsItemBinding
import com.melvin.ongandroid.model.entities.us.Member
import com.squareup.picasso.Picasso
import javax.inject.Inject

class UsViewHolder
@Inject constructor(view: View): RecyclerView.ViewHolder(view) {

    val binding = FragmentUsItemBinding.bind(view)

    fun drawUs(us: Member) {
        Picasso.get().load(us.image).into(binding.ivUsImageUser)
        binding.tvUsNameTitle.text = us.name
        binding.tvUsUserDescription.text = us.rawDescription
    }
}