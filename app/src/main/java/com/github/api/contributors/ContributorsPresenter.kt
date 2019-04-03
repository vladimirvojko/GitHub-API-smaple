package com.github.api.contributors

import com.github.api.GitHub
import com.github.api.common.ExceptionHandler
import com.github.api.common.Executor
import com.github.api.common.extensions.unit
import com.github.api.common.presentation.presenter.ExceptionHandlingPresenter
import com.github.api.model.GHContributor
import com.github.api.model.getContributorsAync
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val contributorsLimit = 25

class ContributorsPresenter @Inject constructor(
    v: ContributorsContract.View, job: Job, executor: Executor,
    private val api: GitHub,
    private val mapManager: ContributorsContract.MapManager
) : ExceptionHandlingPresenter<ContributorsContract.View>(v, job, executor), ContributorsContract.Presenter {
    override val handler = ExceptionHandler { _, exception ->
        view?.showMessage(exception.localizedMessage)
    }

    override fun start() = launch(executor.ui) {
        view?.showProgress()

        val contributors = api.getContributorsAync(KotlinRepository, contributorsLimit).await()

        contributors.map {
            Pair(it, api.getUserProfileAsync(it.login).apply { start() })
        }.forEach {
            it.first.location = it.second.await().location
        }

        view?.hideProgress()
        view?.showContributors(contributors)
    }.unit

    override fun showLocation(contributor: GHContributor) {
        mapManager.showLocationOnMap(contributor.location ?: return)
    }
}