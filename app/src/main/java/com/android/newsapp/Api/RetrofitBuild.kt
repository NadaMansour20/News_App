package com.android.newsapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuild {



    companion object{

        private var retrofit:Retrofit?=null

        private fun built_retrofit():Retrofit{

            if(retrofit==null) {
                retrofit = Retrofit.Builder().baseUrl("https://newsapi.org")
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }

                return retrofit!!

        }

    }

    fun get_api():ApiServices{
        return retrofit!!.create(ApiServices::class.java)

    }
}