package com.kuki.data.di.component

import com.kuki.data.di.module.RepositoryModule
import com.kuki.domain.repository.ContactsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {

    fun contactsRepository(): ContactsRepository

}