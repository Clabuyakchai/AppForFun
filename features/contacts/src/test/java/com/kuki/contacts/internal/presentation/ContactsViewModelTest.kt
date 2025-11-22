package com.kuki.contacts.internal.presentation

import app.cash.turbine.test
import com.kuki.contacts.internal.domain.usecase.FetchContactsUseCase
import com.kuki.contacts.internal.presentation.model.ContactsUiState
import com.kuki.domain.usecase.call
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
internal class ContactsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var mockFetchContactsUseCase: FetchContactsUseCase

    @Before
    fun setUp() {
        mockFetchContactsUseCase = mockk()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel() = ContactsViewModel(mockFetchContactsUseCase)

    @Test
    fun firstState_IsLoading_And_Then_Success() = runTest(testDispatcher) {

        coEvery { mockFetchContactsUseCase.call() } returns emptyList()

        val viewModel = createViewModel()

        viewModel.uiState.test {

            var emission = awaitItem()
            assertEquals(ContactsUiState.Loading, emission)

            emission = awaitItem()
            assertEquals(ContactsUiState.Success(emptyList()), emission)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `GIVEN use case throws exception WHEN view model is created THEN emits error state`() = runTest(testDispatcher) {
        val errorMessage = "Network error fetching contacts"
        val exception = RuntimeException(errorMessage)

        coEvery { mockFetchContactsUseCase.call() } throws exception

        val viewModel = createViewModel()

        viewModel.uiState.test {
            var emission = awaitItem()
            assertEquals(ContactsUiState.Loading, emission)

            emission = awaitItem()
            assertEquals(ContactsUiState.Error, emission)

            cancelAndConsumeRemainingEvents()
        }
    }
}