package com.example.iconmobilesample

import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.example.iconmobilesample.feature.FeatureManager

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class IconMobileApplication : SplitCompatApplication(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@IconMobileApplication))
        import(appModule)
        importAll(FeatureManager.kodeinModules)
    }
}