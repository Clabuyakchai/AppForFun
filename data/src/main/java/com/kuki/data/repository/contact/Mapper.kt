package com.kuki.data.repository.contact

import com.kuki.data.datasource.dto.contact.ContactDto
import com.kuki.domain.entry.contact.ContactEntry

fun ContactDto.toEntry() = ContactEntry(
    id = this.id,
    name = this.name,
    surname = this.surname,
    phoneNumber = this.phoneNumber,
    avatarUrl = this.avatarUrl
)