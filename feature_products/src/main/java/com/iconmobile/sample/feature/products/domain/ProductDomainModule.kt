package com.iconmobile.sample.feature.products.domain

import com.iconmobile.sample.feature.products.FEATURE_NAME
import com.iconmobile.sample.feature.products.domain.usecase.GetProductUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${FEATURE_NAME}DomainModule") {

    bind() from singleton {
        GetProductUseCase(
            instance()
        )
    }
}