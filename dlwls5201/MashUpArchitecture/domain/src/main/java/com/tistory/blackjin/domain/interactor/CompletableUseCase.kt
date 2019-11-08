package com.tistory.blackjin.domain.interactor

import com.tistory.blackjin.domain.schedulers.SchedulersProvider
import io.reactivex.Completable

abstract class CompletableUseCase<in Params>(
    private val schedulersProvider: SchedulersProvider
) {
    protected abstract fun buildUseCaseCompletable(params: Params): Completable

    fun get(params: Params): Completable = buildUseCaseCompletable(params)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
}
