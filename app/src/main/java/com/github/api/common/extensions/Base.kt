package com.github.api.common.extensions

import java.lang.ref.WeakReference

fun <T> T.weak() = WeakReference(this)

val Any.className: String get() = javaClass.simpleName
val Any.uniqueName: String get() = className + "@" + hashCode()

val Any?.unit: Unit get() = Unit