package com.android.newsapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuild {

    companion object {

        private var retrofit: Retrofit? = null

        private fun built_retrofit(): Retrofit {

            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(Constent.base_url)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }

            return retrofit!!

        }

        fun get_api(): ApiServices {
            return built_retrofit().create(ApiServices::class.java)

        }
    }
}