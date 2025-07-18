package com.kuki.domain.entry.contact

import com.kuki.domain.entry.Entry

data class ContactEntry(
    val id: String,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val avatarUrl: String
): Entry