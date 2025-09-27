package com.kuki.data.di.component

import com.kuki.data.di.module.RepositoryModule
import com.kuki.data.di.module.RepositoryModuleBinds
import com.kuki.di.DispatchersProvider
import com.kuki.domain.repository.ContactsRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, RepositoryModuleBinds::class])
interface RepositoryComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance dispatchersProvider: DispatchersProvider
        ): RepositoryComponent
    }

    fun contactsRepository(): ContactsRepository
}