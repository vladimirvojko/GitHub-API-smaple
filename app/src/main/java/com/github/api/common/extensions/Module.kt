package com.github.api.common.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import toothpick.config.Module

fun Module.androidJob(owner: LifecycleOwner): Job = AndroidJob(owner.lifecycle)

class AndroidJob(lifecycle: Lifecycle) : Job by SupervisorJob(), LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun release() = cancel()
}