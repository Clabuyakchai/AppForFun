package com.kuki.contacts.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kuki.common.findDependencies
import com.kuki.contacts.internal.di.ContactsComponentHolder
import com.kuki.contacts.internal.di.DaggerContactsComponent
import com.kuki.contacts.internal.presentation.ContactsScreenPrivate

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    onClick: (contactId: String) -> Unit
) {
    val component = DaggerContactsComponent.builder().dependencies(findDependencies()).build()
    ContactsComponentHolder._contactsComponent = component

    ContactsScreenPrivate(
        modifier = modifier,
        onClick = onClick
    )
}