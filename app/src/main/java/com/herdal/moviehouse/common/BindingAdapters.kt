package com.herdal.moviehouse.common

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.herdal.moviehouse.R
import com.herdal.moviehouse.utils.ApiConstants

fun ImageView.downloadImage(url: String?, placeholder: CircularProgressDrawable) {
    val options =
        RequestOptions().placeholder(placeholder).fitCenter().error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(ApiConstants.getPosterPath(url))
        .into(this)
}

fun getPlaceHolder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f // width size
        centerRadius = 30f // radius size
        start()
    }
}

@BindingAdapter("android:loadImage")
fun loadImage(view: ImageView, url: String?) {
    view.downloadImage(url, getPlaceHolder(view.context))
}