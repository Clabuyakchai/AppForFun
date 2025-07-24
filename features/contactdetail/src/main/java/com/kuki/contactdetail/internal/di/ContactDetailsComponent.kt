package com.kuki.contactdetail.internal.di

import com.kuki.contactdetail.api.ContactDetailsDependencies
import com.kuki.contactdetail.internal.presentation.ContactDetailsViewModel
import dagger.Component

@Component(dependencies = [ContactDetailsDependencies::class])
internal interface ContactDetailsComponent : ContactDetailsDependencies {

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ContactDetailsDependencies): Builder

        fun build(): ContactDetailsComponent
    }

    fun viewModelFactory(): ContactDetailsViewModel.Factory
}