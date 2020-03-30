package com.iconmobile.sample.feature.products.data

import com.iconmobile.sample.feature.products.FEATURE_NAME
import com.iconmobile.sample.feature.products.data.retrofit.service.RetrofitService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${FEATURE_NAME}DataModule") {

    bind() from singleton { instance<Retrofit>().create(RetrofitService::class.java) }
}
