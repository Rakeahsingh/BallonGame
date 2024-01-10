package com.example.ballongame.balloonGameScreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random

@Composable
fun BalloonGame(
    radius: Float = 100f,
    enabled: Boolean = false,
    balloonColor: Color = Color.Red,
    tailColor: Color = Color.Gray,
    onBalloonClick: () -> Unit = {}
) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        var balloonPosition by remember {
            mutableStateOf(randomPosition(
                radius = radius,
                width = constraints.maxWidth,
                height = constraints.maxHeight
            ))
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(enabled){
                    if (!enabled){
                        return@pointerInput
                    }
                    detectTapGestures {
                        val distance = sqrt(
                            (it.x - balloonPosition.x).pow(2) +
                                    (it.y - balloonPosition.y).pow(2)
                        )

                        if (distance <= radius){
                            balloonPosition = randomPosition(
                                radius = radius,
                                width = constraints.maxWidth,
                                height = constraints.maxHeight
                            )
                            onBalloonClick()
                        }
                    }
                }
        ){
            drawLine(
                color = tailColor,
                start = balloonPosition,
                end = balloonPosition + Offset(0f, 80.dp.toPx()),
                strokeWidth = 3.dp.toPx()
            )

            drawCircle(
                color = balloonColor,
                radius = radius,
                center = balloonPosition
            )

        }

    }

}

private fun randomPosition(
    radius: Float,
    width: Int,
    height: Int
): Offset{

    return Offset(
        x = Random.nextInt( radius.roundToInt(), width - radius.roundToInt() ).toFloat(),
        y = Random.nextInt( radius.roundToInt(), height - radius.roundToInt() ).toFloat()
    )

}