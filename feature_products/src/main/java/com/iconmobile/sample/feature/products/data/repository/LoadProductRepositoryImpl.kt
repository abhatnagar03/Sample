package com.iconmobile.sample.feature.products.data.repository

import com.example.iconmobilesample.retrofit.safeApiCall
import com.example.iconmobilesample.retrofit.toDomainResultWrapper
import com.iconmobile.sample.feature.products.data.mapper.ProductDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.mapper.ProductListDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.retrofit.service.RetrofitService
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.domain.repository.LoadProductListRepository
import com.iconmobile.sample.feature.products.domain.repository.LoadProductRepository
import com.iconmobile.sample.library.base.domain.DomainResultWrapper

internal class LoadProductRepositoryImpl(
    private val retrofitService: RetrofitService,
    private val productDtoToDomainMapper: ProductDtoToDomainMapper
) : LoadProductRepository {

    override suspend fun getProduct(id: String): DomainResultWrapper<Product> =
        safeApiCall {
            productDtoToDomainMapper.transform(
                retrofitService
                    .loadProduct(id)
            )
        }.toDomainResultWrapper()
}