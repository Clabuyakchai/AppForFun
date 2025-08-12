package com.kuki.contactdetail.internal.di

import com.kuki.contactdetail.api.ContactDetailsDependencies
import com.kuki.utils.compose.ComponentHolder

internal class ContactDetailComponentHolder(dependencies: ContactDetailsDependencies) :
    ComponentHolder {

    val contactDetailsComponent: ContactDetailsComponent = DaggerContactDetailsComponent
        .builder()
        .dependencies(dependencies)
        .build()
}