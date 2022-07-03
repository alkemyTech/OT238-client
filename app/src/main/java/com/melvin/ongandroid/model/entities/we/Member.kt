package com.melvin.ongandroid.model.entities.we

import com.google.gson.annotations.SerializedName

data class Member(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val facebookURL: String,
    val linkedinURL: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("deleted_at")
    val deletedAt: String
)