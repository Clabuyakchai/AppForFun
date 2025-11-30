package com.kuki.testapp.di.component

import com.kuki.testapp.di.contract.provide.AppProvideContract

interface AppComponentProvider {

    fun provideAppComponentContract(): AppProvideContract
}