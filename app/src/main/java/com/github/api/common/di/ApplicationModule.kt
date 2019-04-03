package com.github.api.common.di

import android.app.Application
import android.content.Context
import com.github.api.common.AndroidExecutor
import com.github.api.common.Executor
import toothpick.config.Module

class ApplicationModule(application: Application) : Module() {
    init {
        bind(Context::class.java).toInstance(application)
        bind(Executor::class.java).toInstance(AndroidExecutor())
    }
}