package com.kuki.contacts.api

import com.kuki.di.Dependencies
import com.kuki.di.DispatchersProvider
import com.kuki.domain.repository.ContactsRepository

interface ContactsDependencies : Dependencies {
    val contactsRepository: ContactsRepository

    val dispatchersProvider: DispatchersProvider
}