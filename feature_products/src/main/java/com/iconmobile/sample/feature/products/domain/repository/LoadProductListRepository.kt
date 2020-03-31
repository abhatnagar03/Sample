package com.iconmobile.sample.feature.products.domain.repository

import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.library.base.domain.DomainResultWrapper

internal interface LoadProductListRepository {
    suspend fun getProducts(): DomainResultWrapper<List<Product>>
}