package com.kuki.contacts.internal.di

import com.kuki.contacts.api.ContactsDependencies
import com.kuki.contacts.internal.presentation.ContactsViewModel
import dagger.Component

@ContactsScope
@Component(dependencies = [ContactsDependencies::class])
internal interface ContactsComponent : ContactsDependencies {

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ContactsDependencies): Builder

        fun build(): ContactsComponent
    }

    fun viewModelFactory(): ContactsViewModel.Factory
}