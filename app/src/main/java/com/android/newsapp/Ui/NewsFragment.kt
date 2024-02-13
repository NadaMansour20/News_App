package com.android.newsapp.Ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.android.newsapp.Api.Constent
import com.android.newsapp.Api.ResourcesResponse
import com.android.newsapp.Api.RetrofitBuild
import com.android.newsapp.Api.SourcesItem
import com.android.newsapp.R
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {


    lateinit var tab_layout: TabLayout
    lateinit var progressbar: ProgressBar
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
    }

    fun init() {
        tab_layout = requireView().findViewById(R.id.tab_layout)
        progressbar = requireView().findViewById(R.id.progress)


    }

    fun get_toHeadline_from_api() {

        progressbar.isVisible = true

        RetrofitBuild.get_api().get_resources(Constent.api_key)
            .enqueue(object : Callback<ResourcesResponse> {

                override fun onResponse(
                    call: Call<ResourcesResponse>,
                    response: Response<ResourcesResponse>
                ) {

                    progressbar.isVisible = false

                    set_response_to_tab(response.body()?.sources)
                }

                override fun onFailure(call: Call<ResourcesResponse>, t: Throwable) {

                }

            })
    }

    fun set_response_to_tab(response: List<SourcesItem?>?) {

        response?.forEach {
            val tab = tab_layout.newTab()
            tab.text = it?.name
            tab_layout.addTab(tab)
        }


    }
}