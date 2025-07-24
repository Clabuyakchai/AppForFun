package com.kuki.contactdetail.internal.presentation.model

import com.kuki.domain.entry.contact.ContactEntry

internal data class ContactDetailUiState(
    val entry: ContactEntry
) {
    companion object {
        fun createInitialState() = ContactDetailUiState(
            ContactEntry(
                id = "",
                name = "",
                surname = "",
                phoneNumber = "",
                avatarUrl = ""
            )
        )
    }
}
