package com.example.smartstudenthub

import android.os.CountDownTimer

object FocusTimerManager {

    var timeLeftInMillis = 25 * 60 * 1000L
    var timerRunning = false
    var currentMode = "Work Focus"

    private var countDownTimer: CountDownTimer? = null

    private var onTickCallback: ((Long) -> Unit)? = null
    private var onFinishCallback: (() -> Unit)? = null

    fun start(
        onTick: (Long) -> Unit,
        onFinish: () -> Unit
    ) {

        if (timerRunning) return

        onTickCallback = onTick
        onFinishCallback = onFinish

        countDownTimer = object : CountDownTimer(
            timeLeftInMillis,
            1000
        ) {

            override fun onTick(millisUntilFinished: Long) {

                timeLeftInMillis = millisUntilFinished

                onTickCallback?.invoke(timeLeftInMillis)
            }

            override fun onFinish() {

                timeLeftInMillis = 0
                timerRunning = false

                onFinishCallback?.invoke()

                clearCallbacks()
            }

        }.start()

        timerRunning = true
    }

    fun pause() {

        countDownTimer?.cancel()

        countDownTimer = null

        timerRunning = false

        clearCallbacks()
    }

    fun reset() {

        countDownTimer?.cancel()

        countDownTimer = null

        timerRunning = false

        clearCallbacks()

        timeLeftInMillis = when (currentMode) {

            "Short Break" ->
                5 * 60 * 1000L

            "Long Break" ->
                15 * 60 * 1000L

            else ->
                25 * 60 * 1000L
        }
    }

    fun setMode(
        mode: String,
        milliseconds: Long
    ) {

        countDownTimer?.cancel()

        countDownTimer = null

        timerRunning = false

        clearCallbacks()

        currentMode = mode

        timeLeftInMillis = milliseconds
    }

    private fun clearCallbacks() {

        onTickCallback = null
        onFinishCallback = null
    }
}