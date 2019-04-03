package com.github.api

import com.github.api.model.GHContributor
import com.github.api.model.GHUserProfile
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHub {

    @GET("/${Key.REPOS}/{${Key.OWNER}}/{${Key.REPO}}/${Key.CONTRIBUTORS}")
    fun getContributorsAsync(
        @Path(Key.OWNER) owner: String,
        @Path(Key.REPO) repository: String,
        @Query(Key.PER_PAGE) limit: Int
    ): Deferred<List<GHContributor>>

    @GET("/users/{${Key.LOGIN}}")
    fun getUserProfileAsync(@Path(Key.LOGIN) login: String): Deferred<GHUserProfile>

    object Key {
        const val HTML_URL = "html_url"
        const val AVATAR_URL = "avatar_url"
        const val OWNER = "owner"
        const val REPO = "repo"
        const val REPOS = "repos"
        const val PER_PAGE = "per_page"
        const val CONTRIBUTORS = "contributors"
        const val LOGIN = "login"
    }
}