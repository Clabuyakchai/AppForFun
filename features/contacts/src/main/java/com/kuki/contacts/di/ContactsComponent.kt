package com.kuki.contacts.di

import com.kuki.contacts.presentation.ContactsViewModel
import com.kuki.domain.repository.ContactsRepository
import dagger.Component

@ContactsScope
@Component(dependencies = [ContactsRepository::class])
interface ContactsComponent {

    @Component.Builder
    interface Builder {

        fun contactsRepository(repository: ContactsRepository): Builder

        fun build(): ContactsComponent
    }

    fun repository(): ContactsRepository

    fun viewModelFactory(): ContactsViewModel.Factory
}