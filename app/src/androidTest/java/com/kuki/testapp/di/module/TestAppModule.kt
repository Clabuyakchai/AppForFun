package com.kuki.testapp.di.module

import com.kuki.testing.di.DaggerTestDispatchersComponent
import com.kuki.testing.di.DaggerTestRepositoryComponent
import com.kuki.testing.di.TestDispatchersComponent
import com.kuki.testing.di.TestRepositoryComponent
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@Module
internal class TestAppModule {

    @Singleton
    @Provides
    fun provideTestRepositoryComponent(): TestRepositoryComponent {
        return DaggerTestRepositoryComponent.factory().create()
    }

    @Singleton
    @Provides
    fun provideTestContactsRepository(testRepositoryComponent: TestRepositoryComponent) =
        testRepositoryComponent.testContactsRepository()

    @Singleton
    @Provides
    fun provideTestDispatchersComponent(): TestDispatchersComponent {
        return DaggerTestDispatchersComponent.factory().create(UnconfinedTestDispatcher())
    }

    @Singleton
    @Provides
    fun provideTestDispatchers(testDispatchersComponent: TestDispatchersComponent) =
        testDispatchersComponent.dispatchersProvider()
}