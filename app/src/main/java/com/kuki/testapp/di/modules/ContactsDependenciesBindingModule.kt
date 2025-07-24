package com.kuki.testapp.di.modules

import com.kuki.common.Dependencies
import com.kuki.common.DependenciesKey
import com.kuki.contacts.api.ContactsDependencies
import com.kuki.testapp.di.components.ActivityComponent
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