package com.kuki.contactdetail.presentation.model

import com.kuki.domain.entry.contact.ContactEntry

data class ContactDetailUiState(
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
