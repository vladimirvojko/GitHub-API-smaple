package com.github.api.contributors

import android.app.Activity
import android.content.Intent
import android.net.Uri
import javax.inject.Inject

class MapManager @Inject constructor(
    private val activity: Activity
) : ContributorsContract.MapManager {
    override fun showLocationOnMap(location: String) {
        val locationUri = Uri.parse("geo:0,0?q=$location")

        val intent = Intent(Intent.ACTION_VIEW, locationUri)
        intent.`package` = "com.google.android.apps.maps"

        activity.startActivity(Intent.createChooser(intent, "Google Maps"))
    }
}