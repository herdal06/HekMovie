package com.herdal.moviehouse.utils.extensions

import android.content.Context
import android.util.DisplayMetrics

fun Boolean?.orFalse() = this ?: false

val Int?.orZero: Int get() = this ?: 0

fun Int.dpToPx(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    return this * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}