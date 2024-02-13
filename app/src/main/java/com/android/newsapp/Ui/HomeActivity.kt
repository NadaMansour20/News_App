package com.android.newsapp.Ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.android.newsapp.R

class HomeActivity : AppCompatActivity() {

    lateinit var menu: ImageView
    lateinit var drawerLayout: DrawerLayout
    lateinit var category: TextView
    lateinit var setting: TextView
    var categoryFragment = CategoryFragment()
    var settingFragment = SettingFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        open_fragment(categoryFragment)
        init()
        double_call_back()

    }

    fun init() {
        menu = findViewById(R.id.menu_img)
        drawerLayout = findViewById(R.id.drawer_layout)
        category = findViewById(R.id.category)
        setting = findViewById(R.id.setting)

        menu.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                drawerLayout.open()
            }

        })
        category.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                open_fragment(categoryFragment)
                drawerLayout.close()
            }

        })
        setting.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                open_fragment(settingFragment)
                drawerLayout.close()

            }

        })
    }

    fun open_fragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
    }

    //call back()
    fun double_call_back() {
        categoryFragment.double_callback_object = object : CategoryFragment.double_card_callback {
            override fun double_click(categoryTitle: TextView?) {


            }

        }
    }


    //to change language should restart fragment ,but should restart main_activity to restart fragment
    fun restartFragment() {
        val intent = intent
        finish()
        startActivity(intent)
    }


}