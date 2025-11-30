package com.kuki.testapp.di.component

import com.kuki.testapp.di.contract.provide.AppProvideContract
import com.kuki.testapp.di.module.TestAppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class])
interface TestAppComponent: AppProvideContract {

    @Component.Factory
    interface Factory {
        fun create(): TestAppComponent
    }
}