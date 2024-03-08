package com.android.newsapp.Api

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun BindingAdapters(itemView: ImageView, url: String) {
    Glide.with(itemView)
        .load(url)
        .into(itemView)
}