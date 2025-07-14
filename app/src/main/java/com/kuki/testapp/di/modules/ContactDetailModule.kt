package com.kuki.testapp.di.modules

import com.kuki.contactdetail.di.ContactDetailComponentHolder
import com.kuki.contactdetail.di.ContactDetailsComponent
import com.kuki.contactdetail.di.DaggerContactDetailsComponent
import com.kuki.domain.repository.ContactsRepository
import dagger.Module
import dagger.Provides

@Module
class ContactDetailModule {

    @Provides
    fun provideContactDetailComponent(repository: ContactsRepository): ContactDetailsComponent {
        return DaggerContactDetailsComponent.builder().repository(repository).build()
            .also {
                ContactDetailComponentHolder._contactsDetailHolder = it
            }
    }
}