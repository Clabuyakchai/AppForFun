package com.kuki.testing.di

import com.kuki.di.DispatchersProvider
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Singleton
@Component(modules = [TestDispatchersModule::class])
interface TestDispatchersComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance testDispatcher: CoroutineDispatcher): TestDispatchersComponent
    }

    fun dispatchersProvider(): DispatchersProvider

}