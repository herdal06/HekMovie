package com.herdal.moviehouse.utils.extensions

import android.content.Context
import android.util.DisplayMetrics

fun Int.dpToPx(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    return this * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}