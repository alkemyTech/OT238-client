package com.melvin.ongandroid.model.entities

data class NewsResponse (
    val success: Boolean,
    val data: List<News>,
    val message: String? = ""
)