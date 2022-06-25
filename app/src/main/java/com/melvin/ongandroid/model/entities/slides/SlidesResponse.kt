package com.melvin.ongandroid.model.entities.slides

import com.google.gson.annotations.SerializedName

data class SlidesResponse(
    val success: Boolean,
    @SerializedName("data")
    val slideList: List<Slide>
)
