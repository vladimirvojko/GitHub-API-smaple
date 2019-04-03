package com.github.api.common.di

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import toothpick.config.Module
import javax.inject.Provider

@Suppress("LeakingThis")
open class AndroidxActivityModule(activity: AppCompatActivity) : Module() {
    init {
        bind(Activity::class.java).toInstance(activity)
        bind(FragmentManager::class.java)
            .toProviderInstance(AndroidxFragmentManagerProvider(activity))

        bind(LayoutInflater::class.java).toProviderInstance(LayoutInflaterProvider(activity))
    }
}

internal class AndroidxFragmentManagerProvider(
    private val activity: Activity
) : Provider<FragmentManager> {
    override fun get(): FragmentManager = (activity as FragmentActivity).supportFragmentManager
}

internal class LayoutInflaterProvider(
    private val activity: Activity
) : Provider<LayoutInflater> {
    override fun get(): LayoutInflater = activity.layoutInflater
}