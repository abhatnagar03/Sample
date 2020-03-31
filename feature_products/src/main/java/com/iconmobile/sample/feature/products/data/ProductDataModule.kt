package com.iconmobile.sample.feature.products.data

import androidx.fragment.app.Fragment
import com.iconmobile.sample.feature.products.FEATURE_NAME
import com.iconmobile.sample.feature.products.data.mapper.ProductDomainToDtoMapper
import com.iconmobile.sample.feature.products.data.mapper.ProductDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.mapper.ProductListDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.repository.ProductRepositoryImpl
import com.iconmobile.sample.feature.products.data.repository.SaveProductRepositoryImpl
import com.iconmobile.sample.feature.products.data.retrofit.service.RetrofitService
import com.iconmobile.sample.feature.products.domain.repository.ProductRepository
import com.iconmobile.sample.feature.products.domain.repository.SaveProductRepository
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${FEATURE_NAME}DataModule") {

    bind() from singleton { instance<Retrofit>().create(RetrofitService::class.java) }

    bind<ProductListDtoToDomainMapper>() with singleton {
        ProductListDtoToDomainMapper()
    }

    bind<ProductDtoToDomainMapper>() with singleton {
        ProductDtoToDomainMapper()
    }

    bind<ProductDomainToDtoMapper>() with singleton {
        ProductDomainToDtoMapper()
    }

    bind<ProductRepository>() with singleton { ProductRepositoryImpl(instance(), instance()) }

    bind<SaveProductRepository>() with singleton {
        SaveProductRepositoryImpl(
            retrofitService = instance(),
            dtoToDomainMapper = instance(),
            domainToDataMapper = instance()
        )
    }
}
