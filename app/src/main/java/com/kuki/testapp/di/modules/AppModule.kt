package com.kuki.testapp.di.modules

import com.kuki.contacts.di.ContactsComponentHolder
import com.kuki.contacts.di.ContactsComponent
import com.kuki.contacts.di.DaggerContactsComponent
import com.kuki.data.di.component.DaggerRepositoryComponent
import com.kuki.data.di.component.RepositoryComponent
import com.kuki.data.di.module.RepositoryModule
import com.kuki.domain.repository.ContactsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRepositoryComponent(): RepositoryComponent {
        return DaggerRepositoryComponent.builder().repositoryModule(RepositoryModule()).build()
    }

//    @Provides
//    fun provideRepository(repositoryComponent: RepositoryComponent): Repository {
//        return repositoryComponent.repository()
//    }

    @Provides
    fun provideContactsRepository(repositoryComponent: RepositoryComponent): ContactsRepository {
        return repositoryComponent.contactsRepository()
    }

}