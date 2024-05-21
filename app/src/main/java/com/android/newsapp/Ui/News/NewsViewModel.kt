package com.android.newsapp.Ui.News

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.newsapp.Api.ArticlesItem
import com.android.newsapp.Api.RetrofitBuild
import com.android.newsapp.Api.SourcesItem
import com.android.newsapp.Ui.Category.CategoryData
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {


    var liveData_get_source_news = MutableLiveData<List<ArticlesItem?>?>()
    var liveData_get_search_news = MutableLiveData<List<ArticlesItem?>?>()
    var liveData_progress = MutableLiveData(false)
    var liveData_get_toheadline = MutableLiveData<List<SourcesItem?>?>()


    //kotlin coroutine
    fun getNewBySource(source: SourcesItem) {
        //progressbar.isVisible = true
        liveData_progress.value = true


        viewModelScope.launch {
            try {
                val result = RetrofitBuild.get_api().get_all_news_by_source(source.id!!)
                liveData_progress.value = false
                liveData_get_source_news.value = result.articles

            } catch (ex: Exception) {
                liveData_progress.value = false
            }
        }

    }

    fun get_toHeadline_from_api(category: CategoryData) {

        liveData_progress.value = true

        viewModelScope.launch {
            try {
                val result = RetrofitBuild.get_api().get_tobheadline_resources(category.id)
                liveData_progress.value = false
                liveData_get_toheadline.value = result.sources
            } catch (ex: Exception) {
                liveData_progress.value = false

            }
        }
    }

    fun get_news_by_search(query: String) {
        liveData_progress.value = true


        viewModelScope.launch {
            try {
                val result = RetrofitBuild.get_api().get_all_news_by_search(query)
                liveData_progress.value = false
                //news_adapter.changData(response.body()?.articles)
                liveData_get_search_news.value = result.articles
            } catch (ex: Exception) {
                liveData_progress.value = false

            }
        }


    }


}