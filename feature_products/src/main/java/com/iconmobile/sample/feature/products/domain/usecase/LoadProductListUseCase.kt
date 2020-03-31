package com.iconmobile.sample.feature.products.domain.usecase

import com.iconmobile.sample.feature.products.domain.repository.LoadProductListRepository

internal class LoadProductListUseCase(
    private val loadProductListRepository: LoadProductListRepository
) {
    suspend fun execute() = loadProductListRepository.getProducts()
}