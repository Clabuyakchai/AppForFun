package com.kuki.testapp.di.modules

import com.kuki.data.di.component.DaggerRepositoryComponent
import com.kuki.data.di.component.RepositoryComponent
import com.kuki.data.di.module.RepositoryModule
import com.kuki.di.DaggerDiComponent
import com.kuki.di.DiComponent
import com.kuki.di.DispatchersProvider
import com.kuki.domain.repository.ContactsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRepositoryComponent(dispatchersProvider: DispatchersProvider): RepositoryComponent {
        return DaggerRepositoryComponent.factory().create(dispatchersProvider)
    }

    @Singleton
    @Provides
    fun provideContactsRepository(repositoryComponent: RepositoryComponent): ContactsRepository {
        return repositoryComponent.contactsRepository()
    }

    @Singleton
    @Provides
    fun provideDiComponent(): DiComponent {
        return DaggerDiComponent.builder().build()
    }

    @Singleton
    @Provides
    fun provideDispatchersProvider(diComponent: DiComponent): DispatchersProvider =
        diComponent.dispatchersProvider()

}