package com.kuki.domain.usecase

interface AsyncUseCase<Param, Result> {

    suspend operator fun invoke(param: Param): Result
}

suspend fun <Result> AsyncUseCase<Unit, Result>.call() = invoke(Unit)