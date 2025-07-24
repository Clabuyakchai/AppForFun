package com.kuki.testapp

import android.app.Application
import com.kuki.testapp.di.components.AppComponent
import com.kuki.testapp.di.components.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }
}