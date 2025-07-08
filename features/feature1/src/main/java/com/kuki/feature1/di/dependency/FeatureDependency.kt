package com.kuki.feature1.di.dependency

import com.kuki.feature1.di.component.FeatureComponent
import com.kuki.feature1.domain.repository.FeatureRepository

object FeatureDependency {

    lateinit var featureRepository: FeatureRepository

    lateinit var featureComponent: FeatureComponent
}