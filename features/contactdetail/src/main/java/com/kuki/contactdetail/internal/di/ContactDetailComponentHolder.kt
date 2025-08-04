package com.kuki.contactdetail.internal.di

import com.kuki.contactdetail.api.ContactDetailsDependencies

internal object ContactDetailComponentHolder {

    @Volatile
    private var contactsDetailHolder: ContactDetailsComponent? = null

    fun getInstance(dependencies: ContactDetailsDependencies? = null): ContactDetailsComponent {
        return contactsDetailHolder
            ?: synchronized(this) {
                val currentThread = Thread.currentThread()
                println("Текущий поток: ${currentThread.name} (ID: ${currentThread.id})")
                println("Приоритет: ${currentThread.priority}")
                println("Состояние: ${currentThread.state}")
                contactsDetailHolder
                    ?: run {
//                        Thread.sleep(3000)
                        DaggerContactDetailsComponent
                            .builder()
                            .dependencies(
                                dependencies
                                    ?: throw NullPointerException("Component is null, you can't create component without dependencies")
                            )
                            .build()
                            .also { component ->
                                contactsDetailHolder = component
                            }
                    }
            }
    }

    fun destroy() {
        contactsDetailHolder = null
    }
}