package com.melvin.ongandroid.model.entities

import android.text.Html


data class News(

    val id: Int?,
    val name: String?,
    val slug: String?,
    val content: String?,
    val image: String?,
    val user_id: Int?,
    val category_id: Int?,
    val created_at: String,
    val updated_at: String,
    val deleted_at: String = ""
) {
    val rawContent: String = Html.fromHtml(this.content, Html.FROM_HTML_MODE_COMPACT).toString()
}

