package com.kuki.feature1.domain.repository

import kotlin.properties.Delegates

interface FeatureRepositoryProvider {
    val featureRepository: FeatureRepository

    companion object: FeatureRepositoryProvider by FeatureRepositoryStore
}

object FeatureRepositoryStore: FeatureRepositoryProvider {

    override var featureRepository: FeatureRepository by Delegates.notNull()
}