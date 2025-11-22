package com.kuki.testing.di

import com.kuki.domain.repository.ContactsRepository
import com.kuki.testing.repository.TestContactsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class TestRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTestContactsRepository(impl: TestContactsRepository): ContactsRepository
}