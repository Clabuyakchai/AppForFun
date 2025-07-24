package com.kuki.testapp.di.modules

import com.kuki.common.Dependencies
import com.kuki.common.DependenciesKey
import com.kuki.contactdetail.api.ContactDetailsDependencies
import com.kuki.testapp.di.components.ActivityComponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ContactDetailsDependenciesBindingModule {

    @Binds
    @IntoMap
    @DependenciesKey(ContactDetailsDependencies::class)
    fun bindContactDetailsDependencies(impl: ActivityComponent): Dependencies
}