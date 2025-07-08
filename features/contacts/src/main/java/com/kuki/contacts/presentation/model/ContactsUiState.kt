package com.kuki.contacts.presentation.model

import com.kuki.domain.entry.contact.ContactEntry

data class ContactsUiState(
    val items: List<ContactEntry>
) {
    companion object {
        fun createInitialState() = ContactsUiState(items = emptyList())
    }
}