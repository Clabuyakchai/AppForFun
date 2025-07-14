package com.kuki.contactdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuki.contactdetail.domain.usecase.FetchContactDetailsUseCase
import com.kuki.contactdetail.presentation.model.ContactDetailUiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactDetailsViewModel @AssistedInject constructor(
    private val fetchContactDetailsUseCase: FetchContactDetailsUseCase,
    @Assisted contactId: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactDetailUiState.createInitialState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable -> }) {
            _uiState.update {
                it.copy(entry = fetchContactDetailsUseCase.fetchContactDetails(contactId))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    @AssistedFactory
    interface Factory {
        fun create(contactId: String): ContactDetailsViewModel
    }
}