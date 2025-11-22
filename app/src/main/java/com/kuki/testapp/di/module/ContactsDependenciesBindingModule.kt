package com.kuki.testapp.di.module

import com.kuki.contacts.api.ContactsDependencies
import com.kuki.di.Dependencies
import com.kuki.di.DependenciesKey
import com.kuki.testapp.di.component.ActivityComponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ContactsDependenciesBindingModule {

    @Binds
    @IntoMap
    @DependenciesKey(ContactsDependencies::class)
    fun bindContactsDependencies(impl: ActivityComponent): Dependencies
}