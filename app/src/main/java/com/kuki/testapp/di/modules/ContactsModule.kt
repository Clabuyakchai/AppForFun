package com.kuki.testapp.di.modules

import com.kuki.contacts.di.ContactsComponent
import com.kuki.contacts.di.ContactsComponentHolder
import com.kuki.contacts.di.DaggerContactsComponent
import com.kuki.domain.repository.ContactsRepository
import dagger.Module
import dagger.Provides

@Module
class ContactsModule {

    @Provides
    fun provideContactsComponent(repository: ContactsRepository): ContactsComponent {
        return DaggerContactsComponent.builder().contactsRepository(repository).build()
            .also {
                ContactsComponentHolder._contactsComponent = it
            }
    }
}