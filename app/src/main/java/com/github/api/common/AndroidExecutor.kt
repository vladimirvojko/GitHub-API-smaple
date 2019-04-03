package com.github.api.common

import kotlinx.coroutines.Dispatchers

class AndroidExecutor : Executor {
    override val io = Dispatchers.IO
    override val ui = Dispatchers.Main
    override val network = Dispatchers.Default
}