package com.amadroid.rammytimer.controllers.fragment

import com.amadroid.rammytimer.repositories.SettingManager

/**
 * Created by Keigo Amai on 2018/09/19.
 */
class SettingViewModel(val settingManager: SettingManager, val version: String) {

    var shouldBeep: Boolean = false
        get() = settingManager.shouldBeep
        set(value) {
            settingManager.shouldBeep = value
            field = value
        }


    fun onShouldBeepChanged(shouldBeep: Boolean) {
        settingManager.shouldBeep = shouldBeep
        settingManager.apply()
    }
}