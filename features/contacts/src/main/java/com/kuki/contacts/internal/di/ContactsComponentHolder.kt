package com.kuki.contacts.internal.di

internal object ContactsComponentHolder {

    var _contactsComponent: ContactsComponent? = null
    val contactsComponent
        get() = requireNotNull(_contactsComponent)
}