package com.iconmobile.sample.feature.products.data.mapper

import com.iconmobile.sample.feature.products.data.model.ProductDto
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.library.base.data.viewmodelmapper.DataToDomainMapper

internal class ProductDtoToDomainMapper : DataToDomainMapper<ProductDto, Product> {

    override fun transform(data: ProductDto) =
        Product(
            id = data.id!!,
            name = data.name,
            brand = data.brand,
            imageURL = data.imageURL ?: "",
            price = data.price,
            currency = data.currency,
            description = data.description
        )
}