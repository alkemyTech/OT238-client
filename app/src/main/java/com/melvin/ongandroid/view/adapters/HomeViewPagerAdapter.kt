package com.melvin.ongandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melvin.ongandroid.databinding.ViewPagerItemHomeBinding
import com.melvin.ongandroid.model.entities.slides.Slide

class HomeViewPagerAdapter(private val imageList: List<Slide>): RecyclerView.Adapter<HomeViewPagerAdapter.ViewHolder>() {

    private lateinit var binding: ViewPagerItemHomeBinding

    class ViewHolder(binding: ViewPagerItemHomeBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (imageList.isNotEmpty()) {
            with (imageList.get(position % imageList.size)) {
                Glide.with(holder.itemView.context).load(imageUrl).into(binding.ivPager)
                binding.tvPagerTitle.text = name
                binding.tvPagerDescription.text = description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ViewPagerItemHomeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


}