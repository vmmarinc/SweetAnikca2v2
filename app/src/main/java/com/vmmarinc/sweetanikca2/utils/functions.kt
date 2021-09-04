package com.vmmarinc.sweetanikca2.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vmmarinc.sweetanikca2.R

fun loadItemPicture(picture: String?, imageView: AppCompatImageView) {
    Glide.with(imageView.context)
        .load(picture)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .error(R.drawable.logo)
        .into(imageView)
}