package com.kuki.domain.usecase

interface SyncUseCase<Param, Result> {

    operator fun invoke(param: Param): Result
}

fun <Result> SyncUseCase<Unit, Result>.call() = invoke(Unit)