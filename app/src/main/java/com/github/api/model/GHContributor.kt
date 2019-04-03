package com.github.api.model

import com.github.api.GitHub
import com.google.gson.annotations.SerializedName

data class GHContributor(
    val login: String,
    @SerializedName(GitHub.Key.HTML_URL) val htmlUrl: String,
    @SerializedName(GitHub.Key.AVATAR_URL) val avatar: String,
    val contributions: Int
) {
    var location: String? = null
}