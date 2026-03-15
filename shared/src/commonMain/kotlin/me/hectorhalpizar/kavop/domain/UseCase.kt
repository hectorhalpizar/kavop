package me.hectorhalpizar.kavop.domain

/**
 * Template use to create use cases.
 */
interface UseCase<Input, Output> {
    suspend operator fun invoke(input: Input? = null) : Output?
}