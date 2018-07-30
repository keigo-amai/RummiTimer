package com.amadroid.rammytimer.controllers.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.amadroid.rammytimer.BuildConfig
import com.amadroid.rammytimer.R
import com.amadroid.rammytimer.repositories.SettingManager
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initSpinner()
    }

    private fun initSpinner() {

        val settingManager = SettingManager(context!!)
        val period = settingManager.period

        val spinnerAdapter = ArrayAdapter.createFromResource(activity, R.array.durations_text, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        setPeriod.adapter = spinnerAdapter

        setPeriod.setSelection(period)

        periodContainer.setOnClickListener {
            setPeriod.performClick()
        }

        setPeriod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                settingManager.period = position
                settingManager.apply()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }

        versionText.text = "バージョン ${BuildConfig.VERSION_NAME}"
    }
}
