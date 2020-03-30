package com.iconmobile.sample.feature.products.data

import androidx.fragment.app.Fragment
import com.iconmobile.sample.feature.products.FEATURE_NAME
import com.iconmobile.sample.feature.products.data.mapper.ProductDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.repository.ProductRepositoryImpl
import com.iconmobile.sample.feature.products.data.retrofit.service.RetrofitService
import com.iconmobile.sample.feature.products.domain.repository.ProductRepository
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${FEATURE_NAME}DataModule") {

    bind() from singleton { instance<Retrofit>().create(RetrofitService::class.java) }

    bind<ProductDtoToDomainMapper>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        ProductDtoToDomainMapper()
    }

    bind<ProductRepository>() with singleton { ProductRepositoryImpl(instance(), instance()) }
}
