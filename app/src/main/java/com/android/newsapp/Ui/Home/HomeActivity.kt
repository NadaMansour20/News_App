package com.android.newsapp.Ui.Home

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.android.newsapp.InternetConnectivity
import com.android.newsapp.R
import com.android.newsapp.Ui.Category.CategoryData
import com.android.newsapp.Ui.Category.CategoryFragment
import com.android.newsapp.Ui.News.NewsFragment
import com.android.newsapp.Ui.Settings.SettingFragment

class HomeActivity : AppCompatActivity() {

    lateinit var menu: ImageView
    lateinit var drawerLayout: DrawerLayout
    lateinit var category: TextView
    lateinit var setting: TextView
    lateinit var category_intent: TextView
    var categoryFragment = CategoryFragment()
    var settingFragment = SettingFragment()
    var open_internet = InternetConnectivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        open_fragment(categoryFragment)
        init()
        double_call_back()

        if (!isNetworkConnected()) {
            open_internet.show(supportFragmentManager, " ")  //show button sheet
        }


    }

    fun init() {
        menu = findViewById(R.id.menu_img)
        drawerLayout = findViewById(R.id.drawer_layout)
        category = findViewById(R.id.category)
        setting = findViewById(R.id.setting)
        category_intent = findViewById(R.id.category_intent)


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

    //call back()
    fun double_call_back() {
        categoryFragment.double_callback_object = object : CategoryFragment.double_card_callback {

            override fun double_click(category_list: CategoryData) {
                if (isNetworkConnected()) {
                    open_fragment(NewsFragment.getInstance(category_list), true)
                    // to transfer category name in home page
                    category_intent.text = getText(category_list.title_id)
                } else {
                    open_internet.show(supportFragmentManager, " ")  //show button sheet

                }


            }

        }
    }

    fun open_fragment(fragment: Fragment, addtobackstack: Boolean = false) {
        val show_fragment = supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
        if (addtobackstack == true)
        // علشان لما ارجع صفحه ل ورا يرجع للصفحه ال قبلي
            show_fragment.addToBackStack(" ")

        show_fragment.commit()
    }


    fun isNetworkConnected(): Boolean {
        val wifi_manager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifi_is_enabled = wifi_manager.isWifiEnabled

        if (!wifi_is_enabled) {
            Toast.makeText(applicationContext, "NO Internet Connection", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


    //to change language should restart fragment ,but should restart main_activity to restart fragment
    fun restartFragment() {
        val intent = intent
        finish()
        startActivity(intent)
    }


}