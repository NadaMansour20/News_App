package com.android.newsapp.Ui.News

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.Api.ArticlesItem


import com.android.newsapp.R
import com.android.newsapp.databinding.NewsItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    var newsList: List<ArticlesItem?>?

    constructor(newsList: List<ArticlesItem?>?) : super() {
        this.newsList = newsList
    }

    class NewsViewHolder(val item_binding: NewsItemBinding) :
        RecyclerView.ViewHolder(item_binding.root) {

        fun Bind(item: ArticlesItem?) {
            item_binding.news = item
            item_binding.invalidateAll()  // to review data_binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

        val view_binding: NewsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.news_item, parent, false
        )

        return NewsViewHolder(view_binding)

    }

    override fun getItemCount(): Int {
        return newsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val new_item = newsList?.get(position)

        holder.Bind(new_item)

        //load image from url
        //Glide.with(holder.itemView).load(new_item?.urlToImage).into(holder.item_binding.newsImg)

        //call back to open news on Browser
        if (news_item_click != null) {
            holder.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    news_item_click?.news_click(new_item?.url?.toUri()!!)
                }

            })
        }
    }


    fun changData(newsList: List<ArticlesItem?>?) {
        this.newsList = newsList
        notifyDataSetChanged()


    }

    var news_item_click: news_item_clicklistener? = null

    interface news_item_clicklistener {
        fun news_click(url: Uri)
    }
}