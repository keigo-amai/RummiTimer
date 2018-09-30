package com.amadroid.rammytimer.controllers.setting

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.amadroid.rammytimer.R
import com.amadroid.rammytimer.repositories.SettingManager

/**
 * Created by Keigo Amai on 2018/09/19.
 */
class SettingViewModel(context: Context, val version: String) {

    private val settingManager: SettingManager = SettingManager(context)

    var period: Int = 0
        get() = settingManager.period
        set(value) {
            settingManager.period = value
            field = value
        }

    var shouldBeep: Boolean = false
        get() = settingManager.shouldBeep
        set(value) {
            settingManager.shouldBeep = value
            field = value
        }

    val spinnerAdapter: ArrayAdapter<CharSequence>
            = ArrayAdapter.createFromResource(context, R.array.durations_text, android.R.layout.simple_spinner_item)
            .apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

    fun onPeriodSelected(period: Int) {
        settingManager.period = period
        settingManager.apply()
    }

    fun onShouldBeepChanged(shouldBeep: Boolean) {
        settingManager.shouldBeep = shouldBeep
        settingManager.apply()
    }

    fun invokeChildsClick(parentView: View) {
        if (parentView is ViewGroup) {
            for (i: Int in 0..parentView.childCount) {
                parentView.getChildAt(i)?.performClick()
            }
        }
    }
}