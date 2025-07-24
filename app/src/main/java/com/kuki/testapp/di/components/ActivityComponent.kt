package com.kuki.testapp.di.components

import com.kuki.contactdetail.api.ContactDetailsDependencies
import com.kuki.contacts.api.ContactsDependencies
import com.kuki.testapp.di.contract.inject.ActivityInjectContract
import com.kuki.testapp.di.contract.provide.AppProvideContract
import com.kuki.testapp.di.modules.ContactDetailsDependenciesBindingModule
import com.kuki.testapp.di.modules.ContactsDependenciesBindingModule
import com.kuki.testapp.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppProvideContract::class],
    modules = [
        ContactDetailsDependenciesBindingModule::class,
        ContactsDependenciesBindingModule::class
    ]
)
interface ActivityComponent
    : ActivityInjectContract,
    ContactDetailsDependencies,
    ContactsDependencies {
}