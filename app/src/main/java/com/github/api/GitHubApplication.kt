@file:Suppress("unused")

package com.github.api

import android.app.Application
import com.github.api.common.di.ApplicationModule
import com.github.api.common.di.NetworkModule
import toothpick.Toothpick
import toothpick.configuration.Configuration

class GitHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val configuration = if (!BuildConfig.DEBUG) {
            Configuration.forProduction()

        } else Configuration.forDevelopment().preventMultipleRootScopes()

        Toothpick.setConfiguration(configuration)

        val scope = Toothpick.openScope(this)
        scope.installModules(ApplicationModule(this), NetworkModule())
    }
}