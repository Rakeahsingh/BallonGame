package com.example.ballongame.balloonGameScreen.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CountDownTimer(
    time: Int = 60000,
    isRunning: Boolean = false,
    onTimerEnd: () -> Unit = {}
) {

    var currentTime by remember { mutableIntStateOf(time) }

    LaunchedEffect(key1 = currentTime, key2 = isRunning ){
        if (!isRunning){
            currentTime = time
            return@LaunchedEffect
        }
        if (currentTime > 0){
            delay(1000L)
            currentTime -= 1000
        } else {
             onTimerEnd()
        }
    }

    Text(
        text = (currentTime / 1000).toString(),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )

}