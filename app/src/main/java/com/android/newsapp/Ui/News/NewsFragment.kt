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
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.Api.NewsResponse
import com.android.newsapp.Api.ResourcesResponse
import com.android.newsapp.Api.RetrofitBuild
import com.android.newsapp.Api.SourcesItem
import com.android.newsapp.R
import com.android.newsapp.Ui.Category.CategoryData
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {


    lateinit var tab_layout: TabLayout
    lateinit var progressbar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var category: CategoryData
    lateinit var search: SearchView

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
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        get_toHeadline_from_api()
        get_news_from_Browser()


    }

    val news_adapter = NewsAdapter(null)

    fun init() {
        tab_layout = requireView().findViewById(R.id.tab_layout)
        progressbar = requireView().findViewById(R.id.progress)
        search = requireView().findViewById(R.id.search_bar)
        recyclerView = requireView().findViewById(R.id.recycler_view_news)
        recyclerView.adapter = news_adapter


        // the action that taken when click on search_view
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                get_news_by_search(query!!)
                return true


            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false

            }

        })


    }

    fun get_news_by_search(query: String) {
        progressbar.isVisible = true

        RetrofitBuild.get_api().get_all_news_by_search(query)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressbar.isVisible = false
                    news_adapter.changData(response.body()?.articles)

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    progressbar.isVisible = false

                }

            })
    }

    fun get_toHeadline_from_api() {

        progressbar.isVisible = true

        RetrofitBuild.get_api().get_tobheadline_resources(category.id)
            .enqueue(object : Callback<ResourcesResponse> {

                override fun onResponse(
                    call: Call<ResourcesResponse>,
                    response: Response<ResourcesResponse>
                ) {

                    progressbar.isVisible = false

                    set_response_to_tab(response.body()?.sources)
                }

                override fun onFailure(call: Call<ResourcesResponse>, t: Throwable) {
                    progressbar.isVisible = false

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

        tab_layout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //var source=tab?.position
                val source = tab?.tag as SourcesItem
                getNewBySource(source)

            }

            // if select tab more than once
            override fun onTabReselected(tab: TabLayout.Tab?) {
                //var source=tab?.position
                val source = tab?.tag as SourcesItem
                getNewBySource(source)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

        // default select first tab
        tab_layout.getTabAt(0)?.select()


    }

    private fun getNewBySource(source: SourcesItem) {
        progressbar.isVisible = true

        RetrofitBuild.get_api().get_all_news_by_source(source.id!!)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressbar.isVisible = false
                    news_adapter.changData(response.body()?.articles)
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    progressbar.isVisible = false
                }

            })

    }

    ////call back to open news on Browser
    fun get_news_from_Browser() {
        news_adapter.news_item_click = object : NewsAdapter.news_item_clicklistener {
            override fun news_click(url: Uri) {
                val intent = Intent(Intent.ACTION_VIEW, url)
                startActivity(intent)
            }

        }


    }
}