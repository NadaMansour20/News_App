package com.android.newsapp.Ui

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.android.newsapp.R
import java.util.Locale


class SettingFragment : Fragment() {

    lateinit var spinner: Spinner
    lateinit var datalanguage: Array<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = view.findViewById(R.id.spinner)


        // get spinner items from resource.string
        datalanguage = arrayOf(
            getString(R.string.select_language),
            getString(R.string.arabic),
            getString(R.string.english)
        )

        fill_spinner(spinner, datalanguage)
        change_language()
    }


    // to fill data in spinner
    fun fill_spinner(spinner: Spinner, spinner_item: Array<String>) {
        val spinner_adapter = ArrayAdapter(
            requireContext(),
            androidx.transition.R.layout.support_simple_spinner_dropdown_item,
            spinner_item
        )

        spinner.adapter = spinner_adapter


    }

    //change app language

    fun change_language() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (position == 1) {
                    language("ar")
                } else if (position == 2) {
                    language("en")
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    // to change language of application
    fun language(lang: String) {
        val res: Resources = resources
        val config: Configuration = res.configuration
        val dis: DisplayMetrics = res.displayMetrics
        val locale = Locale(lang)
        Locale.setDefault(locale)
        config.setLocale(locale)
        res.updateConfiguration(config, dis)

        // casting requireActivity of fragment as activity and restart it
        (requireActivity() as HomeActivity).restartFragment()


    }


}