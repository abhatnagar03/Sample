package com.iconmobile.sample.feature.products.domain.usecase

import com.iconmobile.sample.feature.products.domain.repository.DeleteProductRepository

internal class DeleteProductUseCase(
    private val deleteProductRepository: DeleteProductRepository
) {
    suspend fun execute(id: String) = deleteProductRepository.deleteProduct(id)
}