package com.iconmobile.sample.feature.products.data.data

import com.iconmobile.sample.feature.products.data.domain.DomainFixtures
import com.iconmobile.sample.feature.products.data.model.ProductDto

object DataFixtures {

    const val PRODUCT_ID = "dummy_id"

    internal fun getProductDto() = ProductDto(
        id = DomainFixtures.PRODUCT_ID,
        name = "name",
        brand = "brand",
        description = "description",
        imageURL = "",
        price = 12.90,
        currency = "EUR"
    )
}