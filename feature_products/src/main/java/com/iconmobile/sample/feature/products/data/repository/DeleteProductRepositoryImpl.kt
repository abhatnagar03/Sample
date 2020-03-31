package com.iconmobile.sample.feature.products.data.repository

import com.example.iconmobilesample.retrofit.safeApiCall
import com.example.iconmobilesample.retrofit.toDomainResultWrapper
import com.iconmobile.sample.feature.products.data.retrofit.service.RetrofitService
import com.iconmobile.sample.feature.products.domain.repository.DeleteProductRepository
import com.iconmobile.sample.library.base.domain.DomainResultWrapper

internal class DeleteProductRepositoryImpl(
    private val retrofitService: RetrofitService
) : DeleteProductRepository {

    override suspend fun deleteProduct(id: String): DomainResultWrapper<Unit> {
        return safeApiCall {
                retrofitService
                    .deleteProduct(id)
        }.toDomainResultWrapper()
    }
}