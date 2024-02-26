package com.android.newsapp.Ui.Settings

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.newsapp.R
import com.android.newsapp.Ui.Home.HomeActivity
import java.util.Locale


class SettingFragment : Fragment() {

    lateinit var spinner: Spinner
    lateinit var datalanguage: Array<String>
    var viewModel = SettingViewModel()
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

        viewModel = ViewModelProvider(this).get(SettingViewModel::class.java)

        add_data_view_model()

        // get spinner items from resource.string
        datalanguage = arrayOf(
            getString(R.string.select_language),
            getString(R.string.arabic),
            getString(R.string.english)
        )

        fill_spinner(spinner, datalanguage)
        viewModel.change_language(spinner)
    }

    fun add_data_view_model() {

        viewModel.liveData_language.observe(viewLifecycleOwner, Observer {
            if (it == 1) {
                language("ar")
            } else if (it == 2) {
                language("en")
            }
        })
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