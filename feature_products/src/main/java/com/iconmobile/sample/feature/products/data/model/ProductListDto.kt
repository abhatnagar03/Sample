@file:JvmName("ProductDataModelKt")

package com.iconmobile.sample.feature.products.data.model

import com.squareup.moshi.Json

data class ProductDto(
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "brand") val brand: String?,
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "url") val imageURL: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "price") val price: Double?,
    @field:Json(name = "currency") val currency: String?
)

data class ProductRequestBodyDto(
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "brand") val brand: String?,
    @field:Json(name = "url") val imageURL: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "price") val price: Double?,
    @field:Json(name = "currency") val currency: String?
)