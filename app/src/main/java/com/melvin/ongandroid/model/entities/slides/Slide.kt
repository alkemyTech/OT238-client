package com.melvin.ongandroid.model.entities.slides

import android.text.Html
import com.google.gson.annotations.SerializedName

data class Slide(
    val id: Int,
    val name: String,
    val description: String,
    @SerializedName("image") val imageUrl: String
) {
    val rawDescription: String
        get() = Html.fromHtml(this.description, Html.FROM_HTML_MODE_COMPACT).toString()
}