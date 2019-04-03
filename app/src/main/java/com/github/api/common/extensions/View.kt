package com.github.api.common.extensions

import android.view.LayoutInflater
import android.view.View

val View.layoutInflater: LayoutInflater get() = LayoutInflater.from(context)
