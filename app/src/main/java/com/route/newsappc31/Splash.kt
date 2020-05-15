package com.route.newsappc31

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.route.newsappc31.UI.home.HomeActivity
import com.route.notesapp.Base.BaseActivity

class Splash : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(activity,
                HomeActivity::class.java))
            finish()
        },2000);
    }
}
