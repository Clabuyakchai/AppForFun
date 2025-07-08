package com.kuki.feature1.di.component

import com.kuki.feature1.di.contract.inejct.FeatureInjectContract
import com.kuki.feature1.di.scope.FeatureScope
import com.kuki.feature1.domain.repository.FeatureRepository
import dagger.Component

@FeatureScope
@Component(dependencies = [FeatureRepository::class])
interface FeatureComponent: FeatureInjectContract {

    @Component.Builder
    interface Builder {

        fun repository(repository: FeatureRepository): Builder

        fun build(): FeatureComponent
    }
}