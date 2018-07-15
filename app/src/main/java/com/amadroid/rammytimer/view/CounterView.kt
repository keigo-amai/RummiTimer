package com.amadroid.rammytimer.view

import android.content.Context
import android.os.Handler
import android.support.design.widget.Snackbar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.amadroid.rammytimer.R
import kotlinx.android.synthetic.main.content_main.view.*

/**
 * Created by Keigo Amai on 2018/07/15.
 */

class CounterView: FrameLayout {

    private var time: Int = 60
    private var isStarted = false
    private val delayMillis = 1000L

    private val countDownHandler = Handler()
    private val countDownTask = {
        time--
        counterText.text = "$time"
        if (time > 0) {
            startCountDown()
        } else {
            isStarted = false
        }
    }

    constructor(context: Context?): super(context)
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.content_main, this, true)

        counterText.setOnClickListener {
            if (!isStarted) {
                startCountDown()
                Snackbar.make(this, "Count down start!!", Snackbar.LENGTH_SHORT).show()
            } else {
                stopCountDown()
                Snackbar.make(this, "Count down stop!!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    internal fun stopCountDown() {
        countDownHandler.removeCallbacksAndMessages(null)
        isStarted = false
    }

    private fun startCountDown() {
        countDownHandler.postDelayed(countDownTask, delayMillis)
        isStarted = true
    }
}