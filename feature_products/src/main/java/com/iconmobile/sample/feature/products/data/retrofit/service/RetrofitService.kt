package com.iconmobile.sample.feature.products.data.retrofit.service

import com.iconmobile.sample.feature.products.data.model.ProductDto
import com.iconmobile.sample.feature.products.data.model.ProductRequestBodyDto
import retrofit2.http.*

internal interface RetrofitService {

    @GET("./productResults")
    suspend fun getProducts(): List<ProductDto>

    @GET("/productResults/{id}")
    suspend fun loadProduct(@Path("id") id: String): ProductDto

    @PUT("/productResults/{id}")
    suspend fun updateProduct(@Path("id") id: String, @Body product: ProductRequestBodyDto): ProductDto

    @POST("./productResults")
    suspend fun saveProduct(@Body product: ProductRequestBodyDto): ProductDto

    @DELETE("/productResults/{id}")
    suspend fun deleteProduct(@Path("id") id: String)
}