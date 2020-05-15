package com.route.newsappc31.UI.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.route.notesapp.Base.BaseFragment


/**
 * Created by Mohamed Nabil Mohamed on 4/5/2020.
 * m.nabil.fci2015@gmail.com
 */

class TestFragment :BaseFragment(){


    lateinit var viewModel :HomeViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(activity).get(HomeViewModel::class.java);

    }
}