package com.melvin.ongandroid.view.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.databinding.NewsViewPagerItemHomeBinding
import com.melvin.ongandroid.model.entities.News
import com.squareup.picasso.Picasso
import javax.inject.Inject

class NewsViewHolder @Inject constructor(view: View): RecyclerView.ViewHolder(view) {

    val binding = NewsViewPagerItemHomeBinding.bind(view)

    fun drawNewsViewPager(news: News) {
        Picasso.get().load(news.image).into(binding.ivNews)
        binding.tvNewsTitle.text = news.name
        binding.tvNewsDescription.text = news.rawContent
    }
}