package com.kuki.di

import dagger.Binds
import dagger.Module

@Module
interface DiModule {

    @Binds
    fun bindDispatchersProvider(impl: DispatchersProviderImpl): DispatchersProvider
}