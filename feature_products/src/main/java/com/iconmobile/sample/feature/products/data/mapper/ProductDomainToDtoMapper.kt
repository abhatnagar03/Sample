package com.iconmobile.sample.feature.products.data.mapper

import com.iconmobile.sample.feature.products.data.model.ProductRequestBodyDto
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.library.base.data.viewmodelmapper.DomainToDataMapper

internal class ProductDomainToDtoMapper : DomainToDataMapper<Product, ProductRequestBodyDto> {

    override fun transform(domain: Product) =
        ProductRequestBodyDto(
            name = domain.name,
            brand = domain.brand,
            imageURL = domain.imageURL,
            price = domain.price,
            currency = domain.currency,
            description = domain.description
        )
}