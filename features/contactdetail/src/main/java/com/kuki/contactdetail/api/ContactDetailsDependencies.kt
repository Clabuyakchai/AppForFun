package com.kuki.contactdetail.api

import com.kuki.di.Dependencies
import com.kuki.domain.repository.ContactsRepository

interface ContactDetailsDependencies : Dependencies {
    val contactsRepository: ContactsRepository
}