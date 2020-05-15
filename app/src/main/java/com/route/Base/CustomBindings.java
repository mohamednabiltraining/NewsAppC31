package com.route.Base;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * Created by Mohamed Nabil Mohamed on 4/5/2020.
 * m.nabil.fci2015@gmail.com
 */
public class CustomBindings {
    @BindingAdapter("imageUrl")
    public static void  changeImage(ImageView image,String url){
        Glide.with(image)
                .load(url)
                .into(image);
    }

}
