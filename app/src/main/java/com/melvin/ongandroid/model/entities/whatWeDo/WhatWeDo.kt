package com.melvin.ongandroid.model.entities.whatWeDo

import android.text.Html

data class WhatWeDo(
    val id: Int,
    val name: String,
    val slug: String,
    val description: String,
    val image: String,
){
    val rawDescription: String
        get() = Html.fromHtml(this.description, Html.FROM_HTML_MODE_COMPACT).toString()
}
