package com.github.api.contributors

import com.github.api.common.presentation.Attachable
import com.github.api.model.GHContributor

interface ContributorsContract {
    interface View : Attachable {
        fun showMessage(text: String)
        fun showProgress()
        fun hideProgress()
        fun showContributors(contributors: List<GHContributor>)
    }

    interface Presenter {
        fun start()
        fun showLocation(contributor: GHContributor)
    }

    interface MapManager {
        fun showLocationOnMap(location: String)
    }
}