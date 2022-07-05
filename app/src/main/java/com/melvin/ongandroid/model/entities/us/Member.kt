package com.melvin.ongandroid.model.entities.us

import android.text.Html
import com.google.gson.annotations.SerializedName

data class Member(
    val id: Int?,
    val name: String?,
    val image: String?,
    val description: String?,
    @SerializedName("facebookUrl")
    val facebookURL: String?,
    @SerializedName("linkedinUrl")
    val linkedInURL: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("deleted_at")
    val deletedAt: String?,
) {
    val rawDescription: String
        get() = Html.fromHtml(this.description, Html.FROM_HTML_MODE_COMPACT).toString()
}
