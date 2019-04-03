package com.github.api.model

import com.github.api.GitHub

interface GHRepository {
    val name: String
    val owner: String
}

fun GitHub.getContributorsAync(repository: GHRepository, limit: Int) =
    getContributorsAsync(repository.owner, repository.name, limit)