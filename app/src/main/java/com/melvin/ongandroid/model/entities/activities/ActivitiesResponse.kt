package com.melvin.ongandroid.model.entities.activities

import com.google.gson.annotations.SerializedName

data class ActivitiesResponse (
    val success: Boolean,
    @SerializedName("data")
    val activitieList: List<Activitie>
)
