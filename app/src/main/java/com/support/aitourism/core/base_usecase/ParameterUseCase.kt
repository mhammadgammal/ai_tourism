package com.support.aitourism.core.base_usecase

abstract class ParameterUseCase<T, R> {
    abstract suspend fun execute(vararg parameters: T): R
}