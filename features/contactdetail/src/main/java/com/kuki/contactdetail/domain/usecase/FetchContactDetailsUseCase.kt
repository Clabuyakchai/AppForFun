package com.kuki.contactdetail.domain.usecase

import com.kuki.domain.entry.contact.ContactEntry
import com.kuki.domain.repository.ContactsRepository
import javax.inject.Inject

class FetchContactDetailsUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository
) {

    suspend fun fetchContactDetails(contactId: String) : ContactEntry {
        return contactsRepository.fetchContacts().get(contactId.toInt())
    }
}