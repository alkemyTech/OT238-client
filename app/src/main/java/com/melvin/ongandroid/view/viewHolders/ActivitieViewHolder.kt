package com.melvin.ongandroid.view.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.databinding.FragmentActivitiesItemBinding
import com.melvin.ongandroid.model.entities.activities.Activitie
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ActivitieViewHolder @Inject constructor(view: View): RecyclerView.ViewHolder(view) {

    val binding = FragmentActivitiesItemBinding.bind(view)

    fun drawActivitie(activitie: Activitie) {
        binding.tvActivitieTitle.text = activitie.name
        binding.tvActivitieDescription.text = activitie.rawDescription
        Picasso.get().load(activitie.image).into(binding.ivActivitieImage)
    }
}