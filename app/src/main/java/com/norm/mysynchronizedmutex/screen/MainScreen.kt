package com.norm.mysynchronizedmutex.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Composable
fun MainScreen() {
    val mutex = remember { Mutex() }

    var counter by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = Unit) {
        repeat(times = 10) {
            launch {
                mutex.withLock {
                    try {
                        delay(timeMillis = 1000)
                        counter++
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

//                mutex.lock()
//                try {
//                    delay(timeMillis = 1000)
//                    counter++
//                } finally {
//                    mutex.unlock()
//                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Counter: $counter",
            fontSize = MaterialTheme.typography.displaySmall.fontSize,
            fontWeight = FontWeight.Bold,
        )
    }
}