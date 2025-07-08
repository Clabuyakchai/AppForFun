package com.kuki.testapp

import android.app.Application
import com.kuki.feature1.domain.repository.FeatureRepositoryStore
import com.kuki.testapp.di.components.AppComponent
import com.kuki.testapp.di.components.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }

    override fun onCreate() {
        super.onCreate()

//        FeatureRepositoryStore.featureRepository = appComponent.featureRepository()
    }
}