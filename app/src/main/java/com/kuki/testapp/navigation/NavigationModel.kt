package com.kuki.testapp.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object ContactsScreenNavModel : NavKey

@Serializable
data class ContactDetailsScreenNavModel(val contactId: String) : NavKey