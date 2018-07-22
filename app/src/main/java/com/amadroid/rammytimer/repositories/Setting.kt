package com.amadroid.rammytimer.repositories

import com.kazakago.preferhythm.PrefClass
import com.kazakago.preferhythm.PrefField

/**
 * Created by Keigo Amai on 2018/07/22.
 */
@PrefClass
class Setting {

    companion object {
        val periodVariation: Array<Long> = arrayOf(30, 60, 120, 180, 300)
    }

    @PrefField
    var period: Int = 1

    @PrefField
    var shouldBeep: Boolean = true
}
