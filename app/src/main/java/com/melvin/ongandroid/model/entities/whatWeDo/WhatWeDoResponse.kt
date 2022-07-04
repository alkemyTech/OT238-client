package com.melvin.ongandroid.model.entities.whatWeDo

import com.google.gson.annotations.SerializedName


data class WhatWeDoResponse (
    val success: Boolean,
    @SerializedName("data")
    val whatWeDoList: List<WhatWeDo>
)
