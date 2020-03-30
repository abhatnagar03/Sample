package com.iconmobile.sample.feature.products.data.retrofit.service

import com.iconmobile.sample.feature.products.data.model.ProductDataModel
import retrofit2.http.GET

internal interface RetrofitService {

    @GET("./productResults")
    suspend fun getProducts(): ProductDataModel
}