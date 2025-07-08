package com.kuki.contactdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuki.contactdetail.domain.usecase.FetchContactDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class ContactDetailsViewModel @AssistedInject constructor(
    private val fetchContactDetailsUseCase: FetchContactDetailsUseCase,
    @Assisted contactId: String
) : ViewModel() {

    init {
        viewModelScope.launch {
            fetchContactDetailsUseCase.fetchContactDetails(contactId)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(contactId: String): ContactDetailsViewModel
    }
}