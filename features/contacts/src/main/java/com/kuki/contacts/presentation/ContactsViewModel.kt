package com.kuki.contacts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuki.contacts.domain.usecase.FetchContactsUseCase
import com.kuki.contacts.presentation.model.ContactsUiState
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactsViewModel @AssistedInject constructor(
    private val fetchContactsUseCase: FetchContactsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactsUiState.createInitialState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable -> }) {
            _uiState.update {
                it.copy(items = fetchContactsUseCase.fetchContacts())
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(): ContactsViewModel
    }
}