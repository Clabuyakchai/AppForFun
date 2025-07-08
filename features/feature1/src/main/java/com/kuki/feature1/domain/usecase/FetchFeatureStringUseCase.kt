package com.kuki.feature1.domain.usecase

import com.kuki.feature1.domain.repository.FeatureRepository
import javax.inject.Inject

class FetchFeatureStringUseCase @Inject constructor(
    private val repository: FeatureRepository
) {

    fun invoke(): String {
        return repository.fetchFeature1()
    }
}