package com.github.api.common

import android.util.Log
import com.github.api.BuildConfig
import com.github.api.common.extensions.uniqueName
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

abstract class ExceptionHandler : AbstractCoroutineContextElement(CoroutineExceptionHandler),
    CoroutineExceptionHandler {

    final override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler.Key
}

@Suppress("FunctionName")
inline fun ExceptionHandler(
    crossinline f: (context: CoroutineContext, exception: Throwable) -> Unit
): CoroutineExceptionHandler = object : ExceptionHandler() {
    override fun handleException(context: CoroutineContext, exception: Throwable) =
        f(context, exception).also {
            if (BuildConfig.DEBUG) Log.e(uniqueName, "Handled exception: ", exception)
        }
}