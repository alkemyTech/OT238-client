package com.melvin.ongandroid.model.entities.slides

import com.google.gson.annotations.SerializedName

data class Slide(
    val id:Int,
    val name: String,
    val description: String,
    @SerializedName("image") val imageUrl:String
)
