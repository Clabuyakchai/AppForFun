package com.kuki.testing.repository

import com.kuki.domain.entry.contact.ContactEntry
import com.kuki.domain.repository.ContactsRepository
import javax.inject.Inject

private val contact1 = ContactEntry(
    id = "0",
    name = "Василий",
    surname = "Пупкин",
    phoneNumber = "+375 (29) 123-45-67",
    avatarUrl = "https://..."
)

private val contact2 = ContactEntry(
    id = "1",
    name = "Сергей",
    surname = "Кузнецов",
    phoneNumber = "+375 (29) 133-45-11",
    avatarUrl = "https://..."
)

private val contact3 = ContactEntry(
    id = "2",
    name = "Миша",
    surname = "Гаврилов",
    phoneNumber = "+375 (29) 563-45-99",
    avatarUrl = "https://..."
)

internal class TestContactsRepository @Inject constructor(): ContactsRepository {

    override suspend fun fetchContacts(): List<ContactEntry> {
        return listOf(contact1, contact2, contact3)
    }
}