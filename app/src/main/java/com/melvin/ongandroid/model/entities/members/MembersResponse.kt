package com.melvin.ongandroid.model.entities.members

data class MembersResponse(
    val success: Boolean,
    val data: Member,
    val message: String
)
