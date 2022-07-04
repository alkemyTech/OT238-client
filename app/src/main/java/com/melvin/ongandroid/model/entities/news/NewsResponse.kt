package com.melvin.ongandroid.model.entities.news

import com.melvin.ongandroid.model.entities.news.News

data class NewsResponse (
    val success: Boolean,
    val data: List<News>,
    val message: String? = ""
)