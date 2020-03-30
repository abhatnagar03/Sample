@file:JvmName("ProductDataModelKt")

package com.iconmobile.sample.feature.products.data.model

import com.squareup.moshi.Json

data class ProductDataModel(
    @field:Json(name = "products") val products: List<Product>
)

data class Product(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "brand") val brand: String,
    @field:Json(name = "uuid") val id: Int,
    @field:Json(name = "url") val imageURL: String?,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "price") val price: Double,
    @field:Json(name = "currency") val currency: String
)