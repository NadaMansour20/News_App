package com.android.newsapp.Ui.Settings

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SettingViewModel : ViewModel() {


    var liveData_language = MutableLiveData<Int>()


    // get spinner items from resource.string
    fun change_language(spinner: Spinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                liveData_language.value = position
                /*if (position == 1) {
                    language("ar")
                } else if (position == 2) {
                    language("en")
                }
                 */
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }
}