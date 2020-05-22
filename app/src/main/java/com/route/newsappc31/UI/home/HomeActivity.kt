package com.route.newsappc31.UI.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.route.model.SourcesItem
import com.route.newsappc31.R
import com.route.newsappc31.databinding.ActivityHomeBinding
import com.route.notesapp.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>(), TabLayout.OnTabSelectedListener {

    val myViewModel : HomeViewModel by viewModel();


    override fun generateViewModel(): HomeViewModel {
       return myViewModel
//        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_home
    }

    val adapter=NewsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviewModel.getNewsSources()
        observeLiveData()
        initRecyclerView()
    }
    fun initRecyclerView(){
        recycler_view.adapter = adapter
    }

    fun observeLiveData() {
        mviewModel.sourcesLiveData.observe(this, Observer {
            hideLoadingDialog()
            setUpTabLayout(it)
        })
        mviewModel.showLoadingLiveData.observe(this, Observer {
            if (it) {
                showLoadingDialog(getString(R.string.loading))
            } else {
                hideLoadingDialog()
            }
        })
        mviewModel.messageStringLiveData.observe(this, Observer {
            showMessage(null,it,null,null,null,null,true)
        })
        mviewModel.newsList.observe(this, Observer {
            adapter.changeData(it)
        })
    }

    private fun setUpTabLayout(sources: List<SourcesItem?>?) {

        for (source in sources.orEmpty()) {
            val tab = tab_layout.newTab()
            tab.setText(source?.name)
            tab.setTag(source)
            tab_layout.addTab(tab)
        }
        tab_layout.addOnTabSelectedListener(this)
        tab_layout.getTabAt(0)?.select()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        val source = tab?.tag as SourcesItem
        mviewModel.getNewsBySourceId(source.id ?: "")

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val source = tab?.tag as SourcesItem
        mviewModel.getNewsBySourceId(source.id ?: "")

    }


}
