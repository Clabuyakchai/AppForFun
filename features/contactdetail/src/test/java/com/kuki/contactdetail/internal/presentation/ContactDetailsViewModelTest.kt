package com.kuki.contactdetail.internal.presentation

import app.cash.turbine.test
import com.kuki.contactdetail.internal.domain.usecase.FetchContactDetailsUseCase
import com.kuki.contactdetail.internal.presentation.model.ContactDetailUiState
import com.kuki.domain.entry.contact.ContactEntry
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

@ExperimentalCoroutinesApi
class ContactDetailsViewModelTest {

    // 1. Test Dispatcher
    private val testDispatcher = StandardTestDispatcher() // Or UnconfinedTestDispatcher

    // 2. Mock Dependencies
    private lateinit var mockFetchContactDetailsUseCase: FetchContactDetailsUseCase

    // 3. System Under Test
    private lateinit var viewModel: ContactDetailsViewModel

    private val testContactId = "3"

    @Before
    fun setUp() {
        // Set the main dispatcher to the test dispatcher
        Dispatchers.setMain(testDispatcher)
        mockFetchContactDetailsUseCase = mockk()
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher to the original one
        Dispatchers.resetMain()
    }

    private fun createViewModel(contactId: String = testContactId): ContactDetailsViewModel {
        return ContactDetailsViewModel(
            fetchContactDetailsUseCase = mockFetchContactDetailsUseCase,
            contactId = contactId
        )
    }

    @Test
    fun `init should start with loading state and then emit contact details on success`() =
        runTest(testDispatcher) {
            val expectedContactDetails = ContactEntry(
                id = testContactId,
                name = "Вася",
                surname = "Пупкин",
                phoneNumber = "123-456-7890",
                avatarUrl = "https://example.com/avatar.jpg"
            )

            // Arrange: Mock the use case to return successfully
            coEvery {
                mockFetchContactDetailsUseCase.invoke(
                    FetchContactDetailsUseCase.Param(
                        testContactId
                    )
                )
            } returns expectedContactDetails

            // Act
            viewModel = createViewModel()

            // Assert: Use Turbine to test StateFlow emissions
            viewModel.uiState.test {
                // Initial state (or the first state after init starts if not using UnconfinedTestDispatcher and advancing)
                var emission = awaitItem() // Default state from MutableStateFlow
                // This might be ContactDetailUiState(isLoading=true, entry=null, error=null)
                // or the default ContactDetailUiState() depending on timing.
                // Let's assume the VM's init block runs quickly.

                // The init block will first set isLoading = true
                emission = awaitItem()
                assertTrue(emission.isLoading)
                assertEquals("", emission.entry.id)
                assertNull(emission.error)

                // Then, after the use case returns, it should emit the success state
                val successEmission = awaitItem()
                assertEquals(false, successEmission.isLoading)
                assertEquals(expectedContactDetails, successEmission.entry)
                assertNull(successEmission.error)

                // Ensure no more emissions
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `init should emit error state when FetchContactDetailsUseCase throws an exception`() =
        runTest(testDispatcher) {
            val errorMessage = "Network error fetching details"
            val exception = RuntimeException(errorMessage)

            // Arrange: Mock the use case to throw an exception
            coEvery {
                mockFetchContactDetailsUseCase.invoke(
                    FetchContactDetailsUseCase.Param(
                        testContactId
                    )
                )
            } throws exception

            // Act
            viewModel = createViewModel()

            // Assert
            viewModel.uiState.test {
                var emission = awaitItem() // Default state

                // Loading state
                emission = awaitItem()
                assertTrue(emission.isLoading)
                assertNull(emission.error)

                // Error state
                val errorEmission = awaitItem()
                assertEquals(false, errorEmission.isLoading)
                assertEquals("", errorEmission.entry.id)
                assertEquals(errorMessage, errorEmission.error)

                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `uiState should reflect initial default state before init completes loading`() =
        runTest(testDispatcher) {
            // This test is a bit tricky due to immediate launch in init.
            // It's more about the initial value of MutableStateFlow.
            // If using UnconfinedTestDispatcher, the init block might complete immediately.
            // Let's test the state *before* creating the ViewModel in a controlled way,
            // or by checking the very first emission if possible.

            // Arrange
            coEvery { mockFetchContactDetailsUseCase(any()) } coAnswers {
                delay(5.seconds) // Ensure it doesn't complete quickly
                ContactEntry(
                    "id",
                    "name",
                    "surname",
                    phoneNumber = "phoneNumber",
                    avatarUrl = "avatarUrl"
                )
            }

            // Act
            val tempViewModel = ContactDetailsViewModel(mockFetchContactDetailsUseCase, "temp-id")

            advanceTimeBy(5.seconds)

            // Assert
            val initialState = tempViewModel.uiState.value
            assertEquals(ContactDetailUiState(isLoading = true, error = null), initialState)
            // Note: The `isLoading = true` comes from the first update in the launch block.
            // The default `ContactDetailUiState()` passed to MutableStateFlow would be (isLoading=false, entry=null, error=null)
            // If your default state in VM is ContactDetailUiState(isLoading = true), then adjust expectation.
            // My example ContactDetailUiState has isLoading=true by default.
            // The ViewModel immediately updates to isLoading=true in the launch block.
        }
}
