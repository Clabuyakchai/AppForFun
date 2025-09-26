package com.kuki.contactdetail.api

import com.kuki.di.Dependencies
import com.kuki.di.DispatchersProvider
import com.kuki.domain.repository.ContactsRepository

interface ContactDetailsDependencies : Dependencies {
    val contactsRepository: ContactsRepository

    val dispatchersProvider: DispatchersProvider
}