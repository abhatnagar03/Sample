package com.iconmobile.sample.feature.products

import com.example.iconmobilesample.feature.KodeinModuleProvider
import com.iconmobile.sample.feature.products.data.dataModule
import com.iconmobile.sample.feature.products.domain.domainModule
import com.iconmobile.sample.feature.products.presentation.presentationModule
import org.kodein.di.Kodein

internal const val FEATURE_NAME = "Products"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${FEATURE_NAME}Module") {
        import(dataModule)
        import(domainModule)
        import(presentationModule)
    }
}
