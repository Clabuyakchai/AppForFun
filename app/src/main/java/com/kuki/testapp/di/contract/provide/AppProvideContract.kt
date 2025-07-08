package com.kuki.testapp.di.contract.provide

import com.kuki.contacts.di.ContactsComponent
import com.kuki.domain.repository.ContactsRepository
import com.kuki.feature1.domain.repository.FeatureRepository

interface AppProvideContract {

//    fun featureRepository(): FeatureRepository

    fun contactsComponent(): ContactsComponent

    fun contactsRepository(): ContactsRepository
}