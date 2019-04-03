package com.github.api.contributors

import com.github.api.common.di.AndroidxActivityModule
import com.github.api.common.extensions.androidJob
import kotlinx.coroutines.Job

class ContributorsModule(activity: ContributorsActivity) : AndroidxActivityModule(activity) {
    init {
        bind(Job::class.java).toInstance(androidJob(activity))
        bind(ContributorsContract.View::class.java).toInstance(activity)
        bind(ContributorsContract.Presenter::class.java).to(ContributorsPresenter::class.java).singletonInScope()
        bind(ContributorsContract.MapManager::class.java).to(MapManager::class.java).singletonInScope()
    }
}