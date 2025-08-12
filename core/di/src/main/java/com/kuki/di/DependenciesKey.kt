package com.kuki.di

import kotlin.reflect.KClass

@dagger.MapKey
annotation class DependenciesKey(val value: KClass<out Dependencies>)
