package com.iconmobile.sample.feature.products.data.mapper

import com.iconmobile.sample.feature.products.data.model.ProductDto
import com.iconmobile.sample.feature.products.data.model.ProductListDto
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.domain.model.ProductList
import com.iconmobile.sample.library.base.data.viewmodelmapper.DataToDomainMapper

internal class ProductDtoToDomainMapper : DataToDomainMapper<ProductListDto, ProductList> {
    override fun transform(domain: ProductListDto) = ProductList(
        products = transform(domain.products)
    )

    private fun transform(products: List<ProductDto>) = products.map {
        Product(
            id = it.id,
            name = it.name,
            brand = it.brand,
            imageURL = it.imageURL ?: "",
            price = it.price,
            currency = it.currency,
            description = it.description
        )
    }
}