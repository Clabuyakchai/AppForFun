package com.kuki.testapp

import android.app.Application
import com.kuki.testapp.di.component.AppComponent
import com.kuki.testapp.di.component.AppComponentProvider
import com.kuki.testapp.di.component.DaggerAppComponent
import com.kuki.testapp.di.contract.provide.AppProvideContract

class App : Application(), AppComponentProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }

    override fun provideAppComponentContract(): AppProvideContract {
        return appComponent
    }
}