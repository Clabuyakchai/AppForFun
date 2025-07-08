package com.kuki.testapp.di.components

import com.kuki.testapp.di.contract.inject.ActivityInjectContract
import com.kuki.testapp.di.contract.provide.AppProvideContract
import com.kuki.testapp.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [AppProvideContract::class])
interface ActivityComponent : ActivityInjectContract  {
}