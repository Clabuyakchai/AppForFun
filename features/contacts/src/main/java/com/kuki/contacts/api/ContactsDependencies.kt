package com.kuki.contacts.api

import com.kuki.common.Dependencies
import com.kuki.domain.repository.ContactsRepository

interface ContactsDependencies : Dependencies {
    val contactsRepository: ContactsRepository
}