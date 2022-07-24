package com.melvin.ongandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.entities.news.News
import com.melvin.ongandroid.view.viewHolders.NewsViewHolder
import javax.inject.Inject

class NewsAdapter @Inject constructor(
    private val news: List <News>,
    private val isMain: Boolean
) : RecyclerView.Adapter<NewsViewHolder>() {

    private val MAXITEMS = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (isMain) {
            NewsViewHolder(layoutInflater.inflate(R.layout.news_view_pager_item_home, parent, false))
        } else {
            NewsViewHolder(layoutInflater.inflate(R.layout.fragment_news_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = news[position]
        if (isMain && position <= MAXITEMS) {
            holder.drawNewsViewPager(item)
        } else if(isMain && position == MAXITEMS + 1) {
            holder.drawArrow()
        } else if (!isMain) {
            holder.drawNewsRecyclerView(item)
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }
}