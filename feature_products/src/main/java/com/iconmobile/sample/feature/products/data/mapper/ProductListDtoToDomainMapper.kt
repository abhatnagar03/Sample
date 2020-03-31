package com.iconmobile.sample.feature.products.data.mapper

import com.iconmobile.sample.feature.products.data.model.ProductDto
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.library.base.data.viewmodelmapper.DataToDomainMapper

internal class ProductListDtoToDomainMapper : DataToDomainMapper<List<ProductDto>, List<Product>> {

    override fun transform(data: List<ProductDto>) = data.filter {
        it.id != null
    }.map {
        Product(
            id = it.id!!,
            name = it.name,
            brand = it.brand,
            imageURL = it.imageURL ?: "",
            price = it.price,
            currency = it.currency,
            description = it.description
        )
    }
}