package com.kuki.contacts.internal.presentation.model

import com.kuki.domain.entry.contact.ContactEntry

internal data class ContactsUiState(
    val items: List<ContactEntry> = emptyList()
)