package com.kuki.contacts.internal.domain.usecase

import com.kuki.domain.entry.contact.ContactEntry
import com.kuki.domain.repository.ContactsRepository
import com.kuki.domain.usecase.AsyncUseCase
import javax.inject.Inject

internal class FetchContactsUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository
) : AsyncUseCase<Unit, List<ContactEntry>> {

    override suspend fun invoke(param: Unit): List<ContactEntry> {
        return contactsRepository.fetchContacts()
    }
}