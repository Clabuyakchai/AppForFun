package com.kuki.testapp

import android.app.Application
import com.kuki.testapp.di.component.AppComponent
import com.kuki.testapp.di.component.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }
}