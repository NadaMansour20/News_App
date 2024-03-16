package com.android.newsapp.Ui.News

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.Api.SourcesItem
import com.android.newsapp.R
import com.android.newsapp.Ui.Category.CategoryData
import com.google.android.material.tabs.TabLayout


class NewsFragment : Fragment() {


    lateinit var category: CategoryData
    lateinit var search: SearchView
    lateinit var tab_layout: TabLayout
    lateinit var progressbar: ProgressBar
    lateinit var recyclerView: RecyclerView

    var viewModel = NewsViewModel()

    val news_adapter = NewsAdapter(null)


    // to get news by specific category
    companion object {
        fun getInstance(category: CategoryData): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.fragment_news, container, false)
        return inflater.inflate(R.layout.fragment_news, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        init()
        //initial view_model
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        add_data_view_model()

        viewModel.get_toHeadline_from_api(category)
        get_news_from_Browser()

    }

    fun add_data_view_model() {                    // or this
        viewModel.liveData_progress.observe(viewLifecycleOwner, Observer {
            progressbar.isVisible = it    //  receive true or false
        })

        viewModel.liveData_get_toheadline.observe(viewLifecycleOwner, Observer {
            set_response_to_tab(it)
        })

        viewModel.liveData_get_search_news.observe(viewLifecycleOwner, Observer {
            news_adapter.changData(it)
        })

        viewModel.liveData_get_source_news.observe(viewLifecycleOwner, Observer {
            news_adapter.changData(it)
        })

    }
    fun init() {
        tab_layout = requireView().findViewById(R.id.tab_layout)
        progressbar = requireView().findViewById(R.id.progress)
        search = requireView().findViewById(R.id.search_bar)
        recyclerView = requireView().findViewById(R.id.recycler_view_news)
        recyclerView.adapter = news_adapter


        // the action that taken when click on search_view
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.get_news_by_search(query!!)
                return true


            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false

            }

        })


    }


    fun set_response_to_tab(response: List<SourcesItem?>?) {

        response?.forEach {
            val tab = tab_layout.newTab()
            tab.text = it?.name
            tab.tag = it
            tab_layout.addTab(tab)
        }

        tab_layout.setOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //var source=tab?.position
                val source = tab?.tag as SourcesItem
                viewModel.getNewBySource(source)

            }

            // if select tab more than once
            override fun onTabReselected(tab: TabLayout.Tab?) {
                //var source=tab?.position
                val source = tab?.tag as SourcesItem
                viewModel.getNewBySource(source)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

        // default select first tab
        tab_layout.getTabAt(0)?.select()


    }



    ////call back to open news on Browser
    fun get_news_from_Browser() {
        news_adapter.news_item_click = object : NewsAdapter.news_item_clicklistener {
            override fun news_click(url: Uri) {
                //implicit intent
                val intent = Intent(Intent.ACTION_VIEW, url)
                startActivity(intent)
            }

        }


    }

}