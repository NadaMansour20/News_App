package com.android.newsapp.Ui.News

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.Api.ArticlesItem
import com.android.newsapp.R
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    var newsList: List<ArticlesItem?>?

    constructor(newsList: List<ArticlesItem?>?) : super() {
        this.newsList = newsList
    }

    class NewsViewHolder : RecyclerView.ViewHolder {
        val new_img: ImageView
        val news_name: TextView
        val news_description: TextView
        val news_date: TextView

        constructor(itemView: View) : super(itemView) {
            new_img = itemView.findViewById(R.id.news_img)
            news_name = itemView.findViewById(R.id.news_name)
            news_description = itemView.findViewById(R.id.news_details)
            news_date = itemView.findViewById(R.id.news_date)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val new_item = newsList?.get(position)
        holder.news_name.text = new_item?.title
        holder.news_description.text = new_item?.description
        holder.news_date.text = new_item?.publishedAt

        //load image from url
        Glide.with(holder.itemView).load(new_item?.urlToImage).into(holder.new_img)

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