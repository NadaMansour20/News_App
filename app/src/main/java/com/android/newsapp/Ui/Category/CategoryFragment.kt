package com.android.newsapp.Ui.Category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.R

class CategoryFragment : Fragment() {


    val category_list = listOf(
        CategoryData("business", R.drawable.bussines, R.string.business, R.color.orange),
        CategoryData("technology", R.drawable.technology, R.string.technology, R.color.blue_dark),
        CategoryData("general", R.drawable.general, R.string.general, R.color.blue_light),
        CategoryData("health", R.drawable.health, R.string.health, R.color.pink),
        CategoryData("science", R.drawable.science, R.string.science, R.color.yellow),
        CategoryData("sports", R.drawable.ball, R.string.sports, R.color.red),
        CategoryData("entertainment", R.drawable.bussines, R.string.entertainment, R.color.purple)
    )
    lateinit var recyclerView: RecyclerView
    var categoryAdapter = CategoryAdapter(category_list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        call_back()
    }


    fun init() {
        recyclerView = requireView().findViewById(R.id.recycler_view)
        recyclerView.adapter = categoryAdapter
    }


    //call back --> card_click
    fun call_back() {
        categoryAdapter.click_category = object : CategoryAdapter.click_on_category_card {
            override fun card_onclick(category_list: CategoryData) {

                double_callback_object?.double_click(category_list)
            }

        }
    }

    var double_callback_object: double_card_callback? = null

    interface double_card_callback {
        fun double_click(category_list: CategoryData)
    }


}