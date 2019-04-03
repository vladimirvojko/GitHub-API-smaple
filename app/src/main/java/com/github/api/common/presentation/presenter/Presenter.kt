package com.github.api.common.presentation.presenter

import com.github.api.common.extensions.weak
import com.github.api.common.presentation.Attachable

abstract class Presenter<V : Attachable>(view: V) {
    protected val reference = view.weak()
    protected val view: V? get() = reference.get()?.takeIf(Attachable::isAttached)
}