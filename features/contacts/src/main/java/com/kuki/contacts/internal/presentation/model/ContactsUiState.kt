package com.kuki.contacts.internal.presentation.model

import com.kuki.domain.entry.contact.ContactEntry

internal sealed interface ContactsUiState {

    object Error: ContactsUiState

    object Loading: ContactsUiState

    data class Success(val items: List<ContactEntry>): ContactsUiState
}
