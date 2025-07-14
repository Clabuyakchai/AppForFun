package com.kuki.data.datasource.dto.contact

import com.kuki.data.datasource.dto.Dto

class ContactsDto(
    val id: String,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val avatarUrl: String
) : Dto