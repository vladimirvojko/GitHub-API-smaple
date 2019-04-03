package com.github.api.common

import kotlinx.coroutines.CoroutineDispatcher

interface Executor {
    val io: CoroutineDispatcher
    val ui: CoroutineDispatcher
    val network: CoroutineDispatcher
}