package com.kuki.contacts.domain.usecase

import com.kuki.domain.entry.contact.ContactEntry
import com.kuki.domain.repository.ContactsRepository
import javax.inject.Inject

class FetchContactsUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository
) {

    suspend fun fetchContacts(): List<ContactEntry> {
        return contactsRepository.fetchContacts()
    }
}