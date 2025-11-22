package com.kuki.testing.di

import com.kuki.domain.repository.ContactsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestRepositoryModule::class])
interface TestRepositoryComponent {

    @Component.Factory
    interface Factory {
        fun create(): TestRepositoryComponent
    }

    fun testContactsRepository(): ContactsRepository
}
