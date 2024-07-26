package com.norm.mysynchronizedmutex

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class Bank private constructor() {
    private val mutex = Mutex()

    private val _currentBalance = mutableIntStateOf(0)
    val currentBalance: State<Int> = _currentBalance

    suspend fun receiveMany() {
        mutex.withLock {
            delay(1000)
            _currentBalance.intValue += 1000
        }
    }

    suspend fun spendMany(amount: Int = 500) {
        if (_currentBalance.intValue >= amount) {
            mutex.withLock {
                try {
                    delay(
                        timeMillis = 1000
                    )
                    if (_currentBalance.intValue >= amount) {
                        _currentBalance.intValue -= amount
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: Bank? = null

        fun getInstance(): Bank {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Bank().also { INSTANCE = it }
            }
        }
    }
}