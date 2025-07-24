package com.kuki.contactdetail.api

import com.kuki.common.Dependencies
import com.kuki.domain.repository.ContactsRepository

interface ContactDetailsDependencies : Dependencies {
    val contactsRepository: ContactsRepository
}