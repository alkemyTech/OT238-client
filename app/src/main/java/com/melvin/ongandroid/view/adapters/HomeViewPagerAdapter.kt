package com.melvin.ongandroid.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melvin.ongandroid.databinding.ViewPagerItemHomeBinding
import com.melvin.ongandroid.model.entities.slides.Slide

class HomeViewPagerAdapter: ListAdapter<Slide, HomeViewPagerAdapter.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewPagerItemHomeBinding.inflate(LayoutInflater
            .from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val slide = getItem(position)
        holder.bind(slide)
    }

    class ViewHolder(private var binding: ViewPagerItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(slide: Slide) {
            Glide.with(binding.ivPager).load(slide.imageUrl).into(binding.ivPager)
            Log.d("JOSE", slide.imageUrl)
            binding.tvPagerTitle.text = slide.name
            binding.tvPagerDescription.text = slide.description
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Slide>() {
        override fun areItemsTheSame(oldItem: Slide, newItem: Slide): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Slide, newItem: Slide): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }
    }
}