package com.iconmobile.sample.feature.products.data

import com.iconmobile.sample.feature.products.FEATURE_NAME
import com.iconmobile.sample.feature.products.data.mapper.ProductDomainToDtoMapper
import com.iconmobile.sample.feature.products.data.mapper.ProductDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.mapper.ProductListDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.repository.*
import com.iconmobile.sample.feature.products.data.repository.DeleteProductRepositoryImpl
import com.iconmobile.sample.feature.products.data.repository.LoadProductListRepositoryImpl
import com.iconmobile.sample.feature.products.data.repository.LoadProductRepositoryImpl
import com.iconmobile.sample.feature.products.data.repository.SaveProductRepositoryImpl
import com.iconmobile.sample.feature.products.data.repository.UpdateProductRepositoryImpl
import com.iconmobile.sample.feature.products.data.retrofit.service.RetrofitService
import com.iconmobile.sample.feature.products.domain.repository.*
import com.iconmobile.sample.feature.products.domain.repository.DeleteProductRepository
import com.iconmobile.sample.feature.products.domain.repository.LoadProductListRepository
import com.iconmobile.sample.feature.products.domain.repository.LoadProductRepository
import com.iconmobile.sample.feature.products.domain.repository.SaveProductRepository
import com.iconmobile.sample.feature.products.domain.repository.UpdateProductRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
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

    bind<LoadProductListRepository>() with singleton {
        LoadProductListRepositoryImpl(
            instance(),
            instance()
        )
    }

    bind<SaveProductRepository>() with singleton {
        SaveProductRepositoryImpl(
            retrofitService = instance(),
            dtoToDomainMapper = instance(),
            domainToDataMapper = instance()
        )
    }

    bind<DeleteProductRepository>() with singleton { DeleteProductRepositoryImpl(instance()) }

    bind<LoadProductRepository>() with singleton {
        LoadProductRepositoryImpl(
            instance(),
            instance()
        )
    }

    bind<UpdateProductRepository>() with singleton {
        UpdateProductRepositoryImpl(
            instance(),
            instance(),
            instance()
        )
    }
}
