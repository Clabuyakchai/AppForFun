package com.kuki.testapp.di.components

import android.content.Context
import com.kuki.testapp.di.contract.provide.AppProvideContract
import com.kuki.testapp.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent : AppProvideContract {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}