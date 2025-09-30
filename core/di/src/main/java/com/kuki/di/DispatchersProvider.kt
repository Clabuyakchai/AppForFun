package com.kuki.di

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {

    fun main(): CoroutineDispatcher

    fun computation(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun unconfined(): CoroutineDispatcher
}