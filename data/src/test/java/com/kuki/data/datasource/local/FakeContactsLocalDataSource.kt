package com.kuki.data.datasource.local

import com.kuki.data.datasource.dto.contact.ContactDto

class FakeContactsLocalDataSource(
    private val contacts: List<ContactDto>
): ContactsLocalDataSource {

    override suspend fun fetchContacts(): List<ContactDto> {
        return contacts
    }
}