package com.melvin.ongandroid.view.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.databinding.NewsViewPagerItemHomeBinding
import com.melvin.ongandroid.model.entities.News
import com.squareup.picasso.Picasso

class NewsAdapter : ListAdapter<News, RecyclerView.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = NewsViewPagerItemHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val news = getItem(position)
        holder.bind(news)
    }

    class ViewHolder(private var binding: NewsViewPagerItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            val img = binding.ivNews
            Picasso.get()
                .load(news.image).into(img)
            binding.tvNewsTitle.text = news.name
            binding.tvNewsDescription.text = news.content
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.image == newItem.image
        }
    }

}