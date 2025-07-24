package com.kuki.contactdetail.internal.di

internal object ContactDetailComponentHolder {

    var _contactsDetailHolder: ContactDetailsComponent? = null
    val contactsDetailHolder: ContactDetailsComponent
        get() = requireNotNull(_contactsDetailHolder)
}