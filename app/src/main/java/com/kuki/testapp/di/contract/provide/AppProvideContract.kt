package com.kuki.testapp.di.contract.provide

import com.kuki.domain.repository.ContactsRepository

interface AppProvideContract {

    fun contactsRepository(): ContactsRepository
}