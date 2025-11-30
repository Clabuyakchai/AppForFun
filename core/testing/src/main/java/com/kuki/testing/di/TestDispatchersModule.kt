package com.kuki.testing.di

import com.kuki.di.DispatchersProvider
import com.kuki.testing.dispatcher.TestDispatchersProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class TestDispatchersModule {

    @Singleton
    @Binds
    abstract fun bindDispatchersProvider(impl: TestDispatchersProviderImpl): DispatchersProvider
}
