package com.kuki.data.repository.contact

import com.kuki.data.datasource.dto.contact.ContactsDto
import com.kuki.domain.entry.contact.ContactEntry

fun ContactsDto.toEntry() = ContactEntry(
    name = this.name,
    surname = this.surname,
    phoneNumber = this.phoneNumber,
    avatarUrl = this.avatarUrl
)