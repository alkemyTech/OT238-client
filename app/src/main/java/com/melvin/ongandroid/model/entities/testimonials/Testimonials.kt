package com.melvin.ongandroid.model.entities.testimonials

import com.google.gson.annotations.SerializedName

data class Testimonials (
    val id : Int,
    val name : String,
    val image : String,
    val description : String,
    @SerializedName("created_at")
    val createdAt : String,
    @SerializedName("updated_at")
    val updatedAt : String,
    @SerializedName("deleted_at")
    val deletedAt : String?
    )
