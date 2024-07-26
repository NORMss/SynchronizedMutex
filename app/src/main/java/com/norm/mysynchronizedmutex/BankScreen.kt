package com.norm.mysynchronizedmutex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun BankScreen() {
    val bank = remember {
        Bank.getInstance()
    }
    val scope = rememberCoroutineScope()
    val currentBalance by bank.currentBalance

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "$currentBalance",
            fontSize = MaterialTheme.typography.displaySmall.fontSize,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.alpha(0.4f),
            text = "Current Balance",
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                scope.launch {
                    bank.receiveMany()
                }
            }
        ) {
            Text(
                text = "Receive"
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                repeat(
                    times = 3
                ) {
                    scope.launch {
                        bank.spendMany()
                    }
                }
            }
        ) {
            Text(
                text = "Spend"
            )
        }
    }
}