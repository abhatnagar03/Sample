package com.iconmobile.sample.feature.products.domain.model

data class Product (
    val name : String?,
    val brand : String?,
    val id : String,
    val imageURL : String?,
    val description : String?,
    val price : Double?,
    val currency : String?
)