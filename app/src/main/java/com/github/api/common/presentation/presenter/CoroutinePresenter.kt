package com.github.api.common.presentation.presenter

import com.github.api.common.Executor
import com.github.api.common.presentation.Attachable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutinePresenter<V : Attachable>(
    view: V,
    protected val job: Job,
    protected val executor: Executor
) : Presenter<V>(view), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = executor.ui + job
}