package com.kuki.data.repository.contact

import com.kuki.data.datasource.dto.contact.ContactDto
import com.kuki.data.datasource.local.FakeContactsLocalDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ContactsDataRepositoryTest {

    private val contact1 = ContactDto(
        id = "0",
        name = "Василий",
        surname = "Пупкин",
        phoneNumber = "+375 (29) 123-45-67",
        avatarUrl = "https://..."
    )

    private val contact2 = ContactDto(
        id = "1",
        name = "Сергей",
        surname = "Кузнецов",
        phoneNumber = "+375 (29) 133-45-11",
        avatarUrl = "https://..."
    )

    private val contact3 = ContactDto(
        id = "2",
        name = "Миша",
        surname = "Гаврилов",
        phoneNumber = "+375 (29) 563-45-99",
        avatarUrl = "https://..."
    )

    private val contacts = listOf(contact1, contact2, contact3)

    private lateinit var repository: ContactsDataRepository

    @Before
    fun createRepository() {
        val fakeContactsDataSource = FakeContactsLocalDataSource(contacts)
        repository = ContactsDataRepository(fakeContactsDataSource)
    }

    /**
     * Tests that [ContactsDataRepository.fetchContacts] requests all contacts from the local data source.
     */
    @Test
    fun fetchContacts_requestAllContactsFromLocalDataSource() = runTest {
        val localContacts = repository.fetchContacts()
        val contactsEntry = contacts.map { it.toEntry() }
        assertEquals(contactsEntry.size, localContacts.size)
        assertEquals(contactsEntry, localContacts)
    }
}