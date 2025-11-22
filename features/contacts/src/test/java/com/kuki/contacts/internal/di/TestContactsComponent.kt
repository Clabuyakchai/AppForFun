package com.kuki.contacts.internal.di

import com.kuki.contacts.internal.presentation.ContactsViewModel
import com.kuki.domain.repository.ContactsRepository
import dagger.BindsInstance
import dagger.Component

@ContactsScope
@Component
internal interface TestContactsComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance repository: ContactsRepository): TestContactsComponent
    }

    fun testContactsRepository(): ContactsRepository

    fun viewModel(): ContactsViewModel.Factory
}