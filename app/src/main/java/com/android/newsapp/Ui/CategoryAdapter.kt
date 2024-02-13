package com.android.newsapp.Ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.newsapp.R
import com.google.android.material.card.MaterialCardView

class CategoryAdapter : Adapter<CategoryAdapter.CategoryViewHolder> {
    val category_list: List<CategoryData>

    constructor(category_list: List<CategoryData>) : super() {
        this.category_list = category_list
    }

    class CategoryViewHolder : ViewHolder {
        var category_img: ImageView? = null
        var category_title: TextView? = null
        var material_card: MaterialCardView? = null

        constructor(itemView: View) : super(itemView) {
            category_img = itemView.findViewById(R.id.category_img)
            category_title = itemView.findViewById(R.id.category_title)
            material_card = itemView.findViewById(R.id.card)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            if (viewType == CATEGORY_LEFT_STYLE)
                R.layout.category_item_left_style
            else
                R.layout.category_item_right_style, parent, false
        )

        return CategoryViewHolder(view)
    }

    val CATEGORY_LEFT_STYLE = 10
    val CATEGORY_RIGH_STYLE = 20

    // used when recycler view return more than different view
    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) return CATEGORY_LEFT_STYLE
        else return CATEGORY_RIGH_STYLE
    }

    override fun getItemCount(): Int {
        return category_list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category_data_item = category_list[position]
        holder.category_img?.setImageResource(category_data_item.imge_id)
        holder.category_title?.setText(category_data_item.title_id)
        holder.material_card?.setBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                category_data_item.color_id
            )
        )

        if (click_category != null) {
            holder.material_card?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    click_category!!.card_onclick(holder.category_title)
                }

            })
        }
    }

    var click_category: click_on_category_card? = null

    interface click_on_category_card {
        fun card_onclick(categoryTitle: TextView?)
    }


}