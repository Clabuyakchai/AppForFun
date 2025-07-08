package com.kuki.contactdetail.di

import com.kuki.domain.repository.ContactsRepository
import dagger.Component

@Component
interface ContactDetailsComponent {

    @Component.Builder
    interface Builder {

        fun repository(repository: ContactsRepository): Builder

        fun build(): ContactDetailsComponent
    }

    fun contactsRepository(): ContactsRepository
}