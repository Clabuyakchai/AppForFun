package com.kuki.contacts.internal.presentation

import app.cash.turbine.test
import com.kuki.contacts.internal.di.DaggerTestContactsComponent
import com.kuki.contacts.internal.di.TestContactsComponent
import com.kuki.contacts.internal.presentation.model.ContactsUiState
import com.kuki.testing.di.DaggerTestRepositoryComponent
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

@OptIn(ExperimentalCoroutinesApi::class)
internal class ContactsViewModelSociableTests { // this test example of sociable unit test by Martin Fowler

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var component: TestContactsComponent
    private lateinit var viewModelFactory: ContactsViewModel.Factory

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        val testRepositoryComponent = DaggerTestRepositoryComponent.factory().create()
        component = DaggerTestContactsComponent.factory()
            .create(testRepositoryComponent.testContactsRepository())
        viewModelFactory = component.viewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel() = viewModelFactory.create()

    @Test
    fun initialStateIsLoading_fetchContactsFromRepository_thanStateIsSuccess() = runTest {
        val viewModel = createViewModel()
        val list = component.testContactsRepository().fetchContacts()

        viewModel.uiState.test {

            var emission = awaitItem()
            assertEquals(ContactsUiState.Loading, emission)

            emission = awaitItem()
            assertEquals(ContactsUiState.Success(list), emission)

            cancelAndConsumeRemainingEvents()
        }
    }
}