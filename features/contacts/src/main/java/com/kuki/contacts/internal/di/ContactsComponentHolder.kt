package com.kuki.contacts.internal.di

import com.kuki.contacts.api.ContactsDependencies
import com.kuki.utils.compose.ComponentHolder

internal class ContactsComponentHolder(dependencies: ContactsDependencies) : ComponentHolder {

    val contactsComponent = DaggerContactsComponent.builder().dependencies(dependencies).build()
}