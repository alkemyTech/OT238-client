package com.melvin.ongandroid.model.entities.testimonials

import com.google.gson.annotations.SerializedName

data class TestimonialsResponse(
    val success: Boolean,
    @SerializedName("data")
    val testimonialsList: List<Testimonials>
)
