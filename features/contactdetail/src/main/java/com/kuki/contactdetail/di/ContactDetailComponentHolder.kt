package com.kuki.contactdetail.di

object ContactDetailComponentHolder {

    var _contactsDetailHolder: ContactDetailsComponent? = null
    val contactsDetailHolder: ContactDetailsComponent
        get() = requireNotNull(_contactsDetailHolder)
}