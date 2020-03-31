package com.iconmobile.sample.feature.products.domain.usecase

import com.iconmobile.sample.feature.products.domain.repository.LoadProductRepository

internal class LoadProductUseCase(
    private val loadProductRepository: LoadProductRepository
) {
    suspend fun execute(id: String) = loadProductRepository.getProduct(id)
}