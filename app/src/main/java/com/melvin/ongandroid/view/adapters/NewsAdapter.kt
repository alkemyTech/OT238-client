package com.melvin.ongandroid.view.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.entities.News
import com.melvin.ongandroid.view.viewHolders.NewsViewHolder
import javax.inject.Inject

class NewsAdapter @Inject constructor(
    private val news: List <News>
) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(
            layoutInflater.inflate(R.layout.news_view_pager_item_home, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = news[position]
        holder.drawNewsViewPager(item)
    }

    override fun getItemCount(): Int {
        return news.size
    }
}