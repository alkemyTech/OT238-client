package com.melvin.ongandroid.view.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.databinding.SlidesActivitiesListBinding
import com.melvin.ongandroid.model.entities.slides.Slide


class SlidesViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val binding = SlidesActivitiesListBinding.bind(view)

    fun render(slide: Slide){
        binding.tvActivitiesTitle.text = slide.name
        binding.tvActivitiesDesc.text = slide.description
        //Insert here slide images

    }
}