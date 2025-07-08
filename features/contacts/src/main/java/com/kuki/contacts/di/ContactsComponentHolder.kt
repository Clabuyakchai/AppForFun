package com.kuki.contacts.di

object ContactsComponentHolder {

    var _contactsComponent: ContactsComponent? = null
    val contactsComponent: ContactsComponent
        get() = requireNotNull(_contactsComponent)
}