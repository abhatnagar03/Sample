package com.iconmobile.sample.feature.products.data.domain

import com.iconmobile.sample.feature.products.domain.model.Product

object DomainFixtures {

    const val PRODUCT_ID = "dummy_id"

    internal fun getProduct(): Product = Product(
        id = PRODUCT_ID,
        name = "name",
        brand = "brand",
        description = "description",
        imageURL = "",
        price = 12.90,
        currency = "EUR"
    )
}
