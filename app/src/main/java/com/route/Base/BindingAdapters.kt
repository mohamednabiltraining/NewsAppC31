package com.route.Base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * Created by Mohamed Nabil Mohamed on 4/5/2020.
 * m.nabil.fci2015@gmail.com
 */

@BindingAdapter("imageUrl")
fun changeImage(image: ImageView, url: String) {
    Glide.with(image)
        .load(url)
        .into(image)
}

