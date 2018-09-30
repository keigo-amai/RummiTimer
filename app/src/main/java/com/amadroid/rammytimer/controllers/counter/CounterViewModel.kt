package com.amadroid.rammytimer.controllers.counter

import android.app.Application
import android.arch.lifecycle.*
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import com.amadroid.rammytimer.repositories.Setting
import com.amadroid.rammytimer.repositories.SettingManager

/**
 * Created by Keigo Amai on 2018/09/30.
 */
class CounterViewModel(application: Application): AndroidViewModel(application) {

    private val delayMillis = 1000L

    private val toneGenerator = ToneGenerator(AudioManager.STREAM_SYSTEM, ToneGenerator.MAX_VOLUME)

    private val settingManager: SettingManager = SettingManager(application)

    val timeObservable = MutableLiveData<String>()

    private var time = 0
    private var isStarted = false
    var showToastAction: ((String) -> Unit)? = null

    private val countDownHandler = Handler()
    private val countDownTask:() -> Unit = {
        if (time > 0) {
            time--
            val min = time / 60
            val timeStr = if (min > 0) String.format("%d:%d", min, time % 60) else "$time"
            timeObservable.value = timeStr
            countDown()

            when(time) {
                0 -> toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP2)
                in 1..5 -> {
                    if (settingManager.shouldBeep) {
                        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP)
                    }
                }
            }
        } else {
            isStarted = false
        }
    }

    init {
        resetCountDown()
    }

    fun onCounterViewClicked() {
        if (!isStarted) {
            startCountDown()
            showToastAction?.invoke("Count down start!!")
        } else {
            stopCountDown()
            showToastAction?.invoke("Count down stop!!")
        }
    }

    fun resetCountDown() {
        stopCountDown()
        time = Setting.getPeriod(getApplication())
        timeObservable.value = "Start!"
    }

    fun stopCountDown() {
        countDownHandler.removeCallbacksAndMessages(null)
        isStarted = false
    }

    private fun startCountDown() {
        countDown()
    }

    private fun countDown() {
        countDownHandler.postDelayed(countDownTask, delayMillis)
        isStarted = true
    }
}