package com.melvin.ongandroid.model.activity

import com.google.gson.annotations.SerializedName

data class Activity(
    var id: Int,
    var name: String,
    @SerializedName("slug")
    var slug: String,
    var description: String,
    var image: String,
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("CategoryId")
    var categoryId: Int,
    @SerializedName("CategoryId")
    var createdAt: Int,
    var updatedAt: String,
    val deletedAt: String,
    val groupId: Int
)
