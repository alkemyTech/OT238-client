package com.melvin.ongandroid.model.entities

import com.google.gson.annotations.SerializedName

data class UserApiResponse(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("email_verified_at")
    var emailVerifiedAt : String? = null,
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("role_id")
    var roleId: Int?    = null,
    @SerializedName("remember_token")
    var rememberToken: String? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @SerializedName("deleted_at")
    var deletedAt: String? = null,
    @SerializedName("group_id")
    var groupId: String? = null,
    @SerializedName("latitude")
    var latitude: String? = null,
    @SerializedName("longitude")
    var longitude: String? = null,
    @SerializedName("address")
    var address: String? = null,
    @SerializedName("profile_image")
    var profileImage: String? = null
)
