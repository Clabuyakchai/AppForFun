package com.kuki.di

import dagger.Component

@Component(modules = [DiModule::class])
interface DiComponent {

    fun dispatchersProvider(): DispatchersProvider
}

