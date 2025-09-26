package com.kuki.testapp.di.contract.provide

import com.kuki.di.DispatchersProvider
import com.kuki.domain.repository.ContactsRepository

interface AppProvideContract {

    fun contactsRepository(): ContactsRepository

    fun dispatchersProvider(): DispatchersProvider
}