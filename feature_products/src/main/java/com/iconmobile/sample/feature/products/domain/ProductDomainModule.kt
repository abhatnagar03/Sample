package com.iconmobile.sample.feature.products.domain

import com.iconmobile.sample.feature.products.FEATURE_NAME
import com.iconmobile.sample.feature.products.domain.usecase.*
import com.iconmobile.sample.feature.products.domain.usecase.DeleteProductUseCase
import com.iconmobile.sample.feature.products.domain.usecase.LoadProductListUseCase
import com.iconmobile.sample.feature.products.domain.usecase.LoadProductUseCase
import com.iconmobile.sample.feature.products.domain.usecase.SaveProductUseCase
import com.iconmobile.sample.feature.products.domain.usecase.UpdateProductUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${FEATURE_NAME}DomainModule") {

    bind() from singleton {
        LoadProductListUseCase(
            instance()
        )
    }

    bind() from singleton {
        SaveProductUseCase(
            instance()
        )
    }

    bind() from singleton {
        DeleteProductUseCase(
            instance()
        )
    }

    bind() from singleton {
        LoadProductUseCase(
            instance()
        )
    }

    bind() from singleton {
        UpdateProductUseCase(
            instance()
        )
    }
}