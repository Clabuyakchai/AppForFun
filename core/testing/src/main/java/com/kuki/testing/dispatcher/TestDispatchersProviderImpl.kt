package com.kuki.testing.dispatcher

import com.kuki.di.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class TestDispatchersProviderImpl @Inject constructor(
    private val testDispatcher: CoroutineDispatcher
): DispatchersProvider {


    override fun main(): CoroutineDispatcher = testDispatcher

    override fun computation(): CoroutineDispatcher = testDispatcher

    override fun io(): CoroutineDispatcher = testDispatcher

    override fun unconfined(): CoroutineDispatcher = testDispatcher
}