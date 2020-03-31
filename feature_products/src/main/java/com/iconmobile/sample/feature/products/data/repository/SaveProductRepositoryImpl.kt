package com.iconmobile.sample.feature.products.data.repository

import com.example.iconmobilesample.retrofit.safeApiCall
import com.example.iconmobilesample.retrofit.toDomainResultWrapper
import com.iconmobile.sample.feature.products.data.mapper.ProductDomainToDtoMapper
import com.iconmobile.sample.feature.products.data.mapper.ProductDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.mapper.ProductListDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.retrofit.service.RetrofitService
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.domain.repository.SaveProductRepository
import com.iconmobile.sample.library.base.domain.DomainResultWrapper

internal class SaveProductRepositoryImpl(
    private val retrofitService: RetrofitService,
    private val dtoToDomainMapper: ProductDtoToDomainMapper,
    private val domainToDataMapper: ProductDomainToDtoMapper
) : SaveProductRepository {

    override suspend fun saveProduct(product: Product): DomainResultWrapper<Product> {
        return safeApiCall {
            dtoToDomainMapper.transform(
                retrofitService
                    .saveProduct(product = domainToDataMapper.transform(product))
            )
        }.toDomainResultWrapper()
    }
}