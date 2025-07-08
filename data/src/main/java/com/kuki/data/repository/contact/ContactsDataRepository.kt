package com.kuki.data.repository.contact

import com.kuki.data.datasource.local.ContactsLocalDataSource
import com.kuki.domain.entry.contact.ContactEntry
import com.kuki.domain.repository.ContactsRepository
import javax.inject.Inject

class ContactsDataRepository @Inject constructor(
    private val contactsLocalDataSource: ContactsLocalDataSource
) : ContactsRepository {

    override suspend fun fetchContacts(): List<ContactEntry> {
        return contactsLocalDataSource.fetchContacts().map { it.toEntry() }
    }
}