package com.kuki.contactdetail.internal.domain.usecase

import com.kuki.domain.entry.contact.ContactEntry
import com.kuki.domain.repository.ContactsRepository
import com.kuki.domain.usecase.AsyncUseCase
import javax.inject.Inject

internal class FetchContactDetailsUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository
) : AsyncUseCase<FetchContactDetailsUseCase.Param, ContactEntry> {

    override suspend fun invoke(param: Param): ContactEntry {
        return contactsRepository.fetchContacts()[param.contactId.toInt()]
    }

    data class Param(val contactId: String)
}