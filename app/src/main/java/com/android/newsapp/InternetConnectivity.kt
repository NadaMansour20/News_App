package com.android.newsapp

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InternetConnectivity : BottomSheetDialogFragment() {


    private lateinit var wifi_manager: WifiManager
    lateinit var done_button: Button
    lateinit var switchh: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.no_intertnet_connection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wifi_manager =
            requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager


        init()

    }

    fun init() {
        switchh = requireView().findViewById(R.id.switchh)
        done_button = requireView().findViewById(R.id.done)
        switchh.isChecked = wifi_manager.isWifiEnabled

        fun toggle(enable: Boolean) {
            if (enable) {
                switchh.trackTintList = requireContext().getColorStateList(R.color.gray_dark)
                switchh.thumbTintList = requireContext().getColorStateList(R.color.green)
            }
            wifi_manager.isWifiEnabled = enable

        }


        switchh.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                toggle(isChecked)
            }

        })

        done_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                wifi_manager.isWifiEnabled = true

                dismiss()  //close bottom_sheet

            }
        })
    }


}