package com.kuki.testapp.di.contract.provide

import com.kuki.contactdetail.di.ContactDetailsComponent
import com.kuki.contacts.di.ContactsComponent
import com.kuki.domain.repository.ContactsRepository

interface AppProvideContract {

//    fun featureRepository(): FeatureRepository

    fun contactsComponent(): ContactsComponent

    fun contactDetailsComponent(): ContactDetailsComponent

    fun contactsRepository(): ContactsRepository
}