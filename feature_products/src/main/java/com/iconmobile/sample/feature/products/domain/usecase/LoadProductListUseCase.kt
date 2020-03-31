package com.iconmobile.sample.feature.products.domain.usecase

import com.iconmobile.sample.feature.products.domain.repository.ProductRepository

internal class LoadProductListUseCase(
    private val productRepository: ProductRepository
) {
    suspend fun execute() = productRepository.getProducts()
}