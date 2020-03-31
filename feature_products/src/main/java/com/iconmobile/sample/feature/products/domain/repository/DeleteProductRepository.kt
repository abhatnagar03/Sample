package com.iconmobile.sample.feature.products.domain.repository

import com.iconmobile.sample.library.base.domain.DomainResultWrapper

internal interface DeleteProductRepository {
    suspend fun deleteProduct(id: String): DomainResultWrapper<Unit>
}