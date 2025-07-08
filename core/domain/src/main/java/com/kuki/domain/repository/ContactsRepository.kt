package com.kuki.domain.repository

import com.kuki.domain.entry.contact.ContactEntry

interface ContactsRepository {

    suspend fun fetchContacts(): List<ContactEntry>
}