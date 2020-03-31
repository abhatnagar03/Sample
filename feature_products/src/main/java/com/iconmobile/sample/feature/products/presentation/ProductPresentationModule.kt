package com.iconmobile.sample.feature.products.presentation

import androidx.fragment.app.Fragment
import com.iconmobile.sample.feature.products.FEATURE_NAME
import com.iconmobile.sample.feature.products.presentation.products.AddProductViewModel
import com.iconmobile.sample.feature.products.presentation.products.ProductViewModel
import com.iconmobile.sample.feature.products.presentation.products.recyclerview.ProductAdapter
import com.iconmobile.sample.library.base.presentation.di.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${FEATURE_NAME}PresentationModule") {

    bind<ProductViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { ProductViewModel(instance(), instance()) }
    }

    bind<AddProductViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { AddProductViewModel(instance()) }
    }

    bind<ProductAdapter>() with scoped<Fragment>(AndroidLifecycleScope).singleton { ProductAdapter() }
}
