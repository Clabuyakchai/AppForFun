package com.kuki.testapp

import android.app.Application
import com.kuki.testapp.di.component.AppComponentProvider
import com.kuki.testapp.di.component.DaggerTestAppComponent
import com.kuki.testapp.di.component.TestAppComponent
import com.kuki.testapp.di.contract.provide.AppProvideContract

class TestApplication : Application(), AppComponentProvider {

    val testAppComponent: TestAppComponent by lazy {
        DaggerTestAppComponent.factory().create()
    }

    override fun provideAppComponentContract(): AppProvideContract {
        return testAppComponent
    }
}