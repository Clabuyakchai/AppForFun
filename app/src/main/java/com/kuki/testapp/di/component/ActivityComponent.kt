package com.kuki.testapp.di.component

import com.kuki.contactdetail.api.ContactDetailsDependencies
import com.kuki.contacts.api.ContactsDependencies
import com.kuki.testapp.di.contract.inject.ActivityInjectContract
import com.kuki.testapp.di.contract.provide.AppProvideContract
import com.kuki.testapp.di.module.ContactDetailsDependenciesBindingModule
import com.kuki.testapp.di.module.ContactsDependenciesBindingModule
import com.kuki.testapp.di.scope.ActivityScope
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