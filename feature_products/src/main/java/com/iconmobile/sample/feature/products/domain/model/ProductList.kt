package com.iconmobile.sample.feature.products.domain.model

internal data class ProductList(
    val products : List<Product>
)

data class Product (
    val name : String,
    val brand : String,
    val id : Int,
    val imageURL : String,
    val description : String,
    val price : Double,
    val currency : String
)