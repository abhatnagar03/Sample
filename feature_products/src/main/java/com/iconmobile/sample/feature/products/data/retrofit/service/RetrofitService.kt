package com.iconmobile.sample.feature.products.data.retrofit.service

import com.iconmobile.sample.feature.products.data.model.ProductDto
import com.iconmobile.sample.feature.products.data.model.ProductRequestBodyDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface RetrofitService {

    @GET("./productResults")
    suspend fun getProducts():  List<ProductDto>

    @POST("./productResults")
    suspend fun saveProduct(@Body product:ProductRequestBodyDto):  ProductDto
}