package com.github.api.common.actviity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.github.api.common.di.AndroidxActivityModule
import com.github.api.common.extensions.uniqueName
import com.github.api.common.presentation.Attachable
import toothpick.Scope
import toothpick.Toothpick

abstract class BaseActivity : AppCompatActivity(), Attachable {

    protected abstract val layoutResId: Int

    override var isAttached: Boolean = false
        protected set

    override fun onCreate(savedInstanceState: Bundle?) {
        val scope = createScope()
        installModules(scope)
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, scope)

        isAttached = true
        if (layoutResId != 0) {
            setContentView(layoutResId)
        }
    }

    override fun onDestroy() {
        isAttached = false
        if (isFinishing) Toothpick.closeScope(uniqueName)
        super.onDestroy()
    }

    protected open fun createScope(): Scope = Toothpick.openScopes(application, uniqueName)

    @CallSuper
    protected open fun installModules(scope: Scope) {
        scope.installModules(AndroidxActivityModule(this))
    }
}