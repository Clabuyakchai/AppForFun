package com.kuki.data.di.module

import com.kuki.data.datasource.local.ContactsLocalDataSource
import com.kuki.data.datasource.local.impl.ContactsLocalDataSourceImpl
import com.kuki.data.repository.contact.ContactsDataRepository
import com.kuki.domain.repository.ContactsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideContactsRepository(repository: ContactsDataRepository): ContactsRepository = repository
}

@Module
interface RepositoryModuleBinds {
    @Singleton
    @Binds
    fun bindContactsLocalDataSource(localDataSource: ContactsLocalDataSourceImpl): ContactsLocalDataSource

}