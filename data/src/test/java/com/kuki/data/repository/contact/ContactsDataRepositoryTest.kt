package com.kuki.data.repository.contact

import com.kuki.data.datasource.dto.contact.ContactDto
import com.kuki.data.datasource.local.ContactsLocalDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
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

    private lateinit var contactsLocalDataSource: ContactsLocalDataSource
    private lateinit var repository: ContactsDataRepository

    @Before
    fun createRepository() {
        contactsLocalDataSource = mockk()
        repository = ContactsDataRepository(contactsLocalDataSource)
    }

    /**
     * Tests that [ContactsDataRepository.fetchContacts] requests all contacts from the local data source.
     */
    @Test
    fun fetchContacts_requestAllContactsFromLocalDataSource() = runTest {
        coEvery { contactsLocalDataSource.fetchContacts() } returns contacts

        val localContacts = repository.fetchContacts()
        val contactsEntry = contacts.map { it.toEntry() }

        assertEquals(contactsEntry.size, localContacts.size)
        assertEquals(contactsEntry, localContacts)

        coVerify { contactsLocalDataSource.fetchContacts() }
    }

    @Test
    fun fetchContacts_requestAllContactsFromLocalDataSource_returnEmptyList() = runTest {
        coEvery { contactsLocalDataSource.fetchContacts() } returns emptyList()

        val localContacts = repository.fetchContacts()
        val emptyContactsEntry = emptyList<ContactDto>().map { it.toEntry() }

        assertEquals(emptyContactsEntry.size, localContacts.size)
        assertEquals(emptyContactsEntry, localContacts)

        coVerify { contactsLocalDataSource.fetchContacts() }
    }

    @Test
    fun fetchContacts_requestAllContactsFromLocalDataSource_getSecondItem() = runTest {
        coEvery { contactsLocalDataSource.fetchContacts() } returns contacts

        val secondLocalContact = repository.fetchContacts()[1]
        val secondContactEntry = contacts.map { it.toEntry() }[1]

        assertEquals(secondLocalContact, secondContactEntry)

        coVerify { contactsLocalDataSource.fetchContacts() }
    }

    @Test
    fun fetchContacts_whenLocalDataSourceThrowsException_propagatesException() = runTest {
        val expectedExceptionMessage = "Local data connection failed"
        val expectedException = RuntimeException(expectedExceptionMessage)

        coEvery { contactsLocalDataSource.fetchContacts() } throws expectedException

        val actualException = assertThrows(RuntimeException::class.java) {
            runBlocking {
                repository.fetchContacts()
            }
        }

        assertEquals(expectedException.message, actualException.message)
        coEvery { contactsLocalDataSource.fetchContacts() }
    }
}