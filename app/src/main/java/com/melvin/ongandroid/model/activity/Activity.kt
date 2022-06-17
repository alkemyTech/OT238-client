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
    var user_id: Int,

    @SerializedName("CategoryId")
    var category_id: Int,

    @SerializedName("CategoryId")
    var created_at: Int,

    var updated_at: String,

    val deleted_at: String,

    val group_id: Int


)
