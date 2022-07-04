package com.melvin.ongandroid.model.entities.we

data class MembersResponse(
    val success: Boolean,
    val data: List<Member>,
    val message: String
)
