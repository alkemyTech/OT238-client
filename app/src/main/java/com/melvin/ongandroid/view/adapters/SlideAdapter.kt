package com.melvin.ongandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.entities.slides.Slide
import com.melvin.ongandroid.view.viewHolders.SlidesViewHolder

class SlideAdapter (private val slidesList:List<Slide>): RecyclerView.Adapter<SlidesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlidesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SlidesViewHolder(layoutInflater.inflate(R.layout.slides_activities_list, parent, false))
    }

    override fun onBindViewHolder(holder: SlidesViewHolder, position: Int) {
        val item = slidesList[position]
        holder.render(item)

    }

    override fun getItemCount() = slidesList.size

}


