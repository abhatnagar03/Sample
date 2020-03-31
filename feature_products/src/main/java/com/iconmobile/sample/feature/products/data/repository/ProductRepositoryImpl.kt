package com.iconmobile.sample.feature.products.data.repository

import com.example.iconmobilesample.retrofit.safeApiCall
import com.example.iconmobilesample.retrofit.toDomainResultWrapper
import com.iconmobile.sample.feature.products.data.mapper.ProductDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.retrofit.service.RetrofitService
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.domain.repository.ProductRepository
import com.iconmobile.sample.library.base.domain.DomainResultWrapper

internal class ProductRepositoryImpl(
    private val retrofitService: RetrofitService,
    private val dtoToDomainMapper: ProductDtoToDomainMapper
) : ProductRepository {

    override suspend fun getProducts(): DomainResultWrapper<List<Product>> =
        safeApiCall {
            dtoToDomainMapper.transform(
                retrofitService
                    .getProducts()
            )
        }.toDomainResultWrapper()
}