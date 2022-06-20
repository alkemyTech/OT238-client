package com.melvin.ongandroid.businesslogic.use_cases

data class ValidationResult(
    val successful:Boolean,
    val errorMessage:String? =null
)
