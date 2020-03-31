package com.iconmobile.sample.feature.products.domain.usecase

import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.domain.repository.SaveProductRepository

internal class SaveProductUseCase(
    private val addProductRepository: SaveProductRepository
) {
    suspend fun execute(product: Product) = addProductRepository.saveProduct(product)
}