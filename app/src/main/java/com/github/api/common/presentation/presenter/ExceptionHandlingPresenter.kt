package com.github.api.common.presentation.presenter

import com.github.api.common.Executor
import com.github.api.common.presentation.Attachable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class ExceptionHandlingPresenter<V : Attachable>(
    view: V,
    job: Job,
    executor: Executor
) : CoroutinePresenter<V>(view, job, executor) {
    abstract val handler: CoroutineExceptionHandler

    override val coroutineContext: CoroutineContext
        get() = super.coroutineContext + handler
}