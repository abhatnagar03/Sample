package com.iconmobile.sample.feature.products.domain.usecase

import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.domain.repository.SaveProductRepository
import com.iconmobile.sample.feature.products.domain.repository.UpdateProductRepository

internal class UpdateProductUseCase(
    private val updateProductRepository: UpdateProductRepository
) {
    suspend fun execute(product: Product) = updateProductRepository.updateProduct(product)
}