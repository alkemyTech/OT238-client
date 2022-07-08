package com.melvin.ongandroid.view.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.databinding.FragmentNewsItemBinding
import com.melvin.ongandroid.databinding.NewsViewPagerItemHomeBinding
import com.melvin.ongandroid.model.entities.news.News
import com.squareup.picasso.Picasso
import javax.inject.Inject

class NewsViewHolder @Inject constructor(view: View): RecyclerView.ViewHolder(view) {

    private lateinit var binding : NewsViewPagerItemHomeBinding
    private lateinit var newsBinding : FragmentNewsItemBinding

    fun drawNewsViewPager(news: News) {
        binding = NewsViewPagerItemHomeBinding.bind(itemView)
        Picasso.get().load(news.image).into(binding.ivNews)
        binding.tvNewsTitle.text = news.name
        binding.tvNewsDescription.text = news.rawContent
    }

    fun drawNewsRecyclerView(news: News) {
        newsBinding = FragmentNewsItemBinding.bind(itemView)
        Picasso.get().load(news.image).into(newsBinding.ivNewsItem)
        newsBinding.tvNewsItemTitle.text = news.name
        newsBinding.tvNewsItemDescription.text = news.rawContent
    }
}