package com.kuki.data.datasource.local

import com.kuki.data.datasource.dto.contact.ContactDto

interface ContactsLocalDataSource {
    suspend fun fetchContacts(): List<ContactDto>
}