package com.example.ballongame.balloonGameScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ballongame.balloonGameScreen.components.BalloonGame
import com.example.ballongame.balloonGameScreen.components.CountDownTimer

@Composable
fun BalloonGameScreen() {

    var points by remember { mutableIntStateOf(0) }
    var isTimerRunning by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Points: $points",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Button(onClick = {
                isTimerRunning = !isTimerRunning
                points = 0
            }
            ) {

                Text(
                    if (isTimerRunning) "Reset"
                    else "Start"
                )

            }

            CountDownTimer(
                isRunning = isTimerRunning
            ){
                isTimerRunning = false
            }

        }

        BalloonGame(

            enabled = isTimerRunning

        ){
            points++
        }

    }

}