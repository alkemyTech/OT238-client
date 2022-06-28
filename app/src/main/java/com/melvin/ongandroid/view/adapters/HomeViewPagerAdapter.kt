package com.melvin.ongandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.entities.slides.Slide
import com.melvin.ongandroid.view.viewHolders.HomeViewHolder
import javax.inject.Inject

class HomeViewPagerAdapter @Inject constructor(
    private val slide: List<Slide>
): RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(
            layoutInflater.inflate(R.layout.view_pager_item_home, parent, false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val slide = slide[position]
        holder.drawHomeViewPager(slide)
    }

    override fun getItemCount(): Int {
        return slide.size
    }

}