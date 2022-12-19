package com.herdal.moviehouse.utils.extensions

import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> T.executeWithAction(action: T.() -> Unit) {
    action()
    executePendingBindings()
}