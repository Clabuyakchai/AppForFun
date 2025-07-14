package com.kuki.contactdetail.di

import com.kuki.contactdetail.presentation.ContactDetailsViewModel
import com.kuki.domain.repository.ContactsRepository
import dagger.Component

@Component(dependencies = [ContactsRepository::class])
interface ContactDetailsComponent {

    @Component.Builder
    interface Builder {

        fun repository(repository: ContactsRepository): Builder

        fun build(): ContactDetailsComponent
    }

    fun contactsRepository(): ContactsRepository

    fun viewModelFactory(): ContactDetailsViewModel.Factory
}