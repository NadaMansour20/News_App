package com.android.newsapp.Ui.News

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.newsapp.Api.ArticlesItem
import com.android.newsapp.Api.NewsResponse
import com.android.newsapp.Api.ResourcesResponse
import com.android.newsapp.Api.RetrofitBuild
import com.android.newsapp.Api.SourcesItem
import com.android.newsapp.Ui.Category.CategoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {


    var liveData_get_source_news = MutableLiveData<List<ArticlesItem?>?>()
    var liveData_get_search_news = MutableLiveData<List<ArticlesItem?>?>()
    var liveData_progress = MutableLiveData(false)
    var liveData_get_toheadline = MutableLiveData<List<SourcesItem?>?>()
    fun getNewBySource(source: SourcesItem) {
        //progressbar.isVisible = true
        liveData_progress.value = true

        RetrofitBuild.get_api().get_all_news_by_source(source.id!!)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    liveData_progress.value = false
                    //news_adapter.changData(response.body()?.articles)

                    liveData_get_source_news.value = response.body()?.articles

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    liveData_progress.value = false
                }

            })

    }

    fun get_toHeadline_from_api(category: CategoryData) {

        liveData_progress.value = true

        RetrofitBuild.get_api().get_tobheadline_resources(category.id)
            .enqueue(object : Callback<ResourcesResponse> {

                override fun onResponse(
                    call: Call<ResourcesResponse>,
                    response: Response<ResourcesResponse>
                ) {

                    liveData_progress.value = false

                    //set_response_to_tab(response.body()?.sources)
                    liveData_get_toheadline.value = response.body()?.sources
                }

                override fun onFailure(call: Call<ResourcesResponse>, t: Throwable) {
                    liveData_progress.value = false

                }

            })
    }

    fun get_news_by_search(query: String) {
        liveData_progress.value = true

        RetrofitBuild.get_api().get_all_news_by_search(query)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    liveData_progress.value = false
                    //news_adapter.changData(response.body()?.articles)
                    liveData_get_search_news.value = response.body()?.articles

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    liveData_progress.value = false

                }

            })
    }

}