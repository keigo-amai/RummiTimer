package com.amadroid.rammytimer.repositories

import android.content.Context
import com.kazakago.preferhythm.PrefClass
import com.kazakago.preferhythm.PrefField

/**
 * Created by Keigo Amai on 2018/07/22.
 */
@PrefClass
class Setting {

    companion object {
        private val periodVariation: Array<Int> = arrayOf(30, 60, 120, 180, 300)

        fun getPeriod(context: Context) = periodVariation[SettingManager(context).period]
    }

    @PrefField
    var period: Int = 1

//    @PrefField
//    var shouldBeep: Boolean = true
}
