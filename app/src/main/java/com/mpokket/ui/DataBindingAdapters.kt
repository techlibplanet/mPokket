package com.mpokket.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mpokket.R


@BindingAdapter("android:src")
fun setImageUrl(view: ImageView, url: String) {
    Glide.with(view.context).load(url).circleCrop()
        .placeholder(R.drawable.github_placeholder)
        .into(view)
}

@BindingAdapter("android:src")
fun setImageResource(view: ImageView, src: Int) {
    Glide.with(view.context).load(src).into(view)
}