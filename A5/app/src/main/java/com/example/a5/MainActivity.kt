package com.example.a5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.a5.ui.theme.A5Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BouncingCircle()
                }
            }
        }
    }
}

@Composable
fun BouncingCircle() {
    /*
        Mutable state values are needed to continuously update and check the state of
        the ball which enables the movement of the balls.
     */
    val ballCount = remember { mutableStateOf(0) }
    val ballColors = remember { mutableListOf<Color>(Color.Red, Color.Blue,
        Color.Magenta, Color.Cyan, Color.Yellow, Color.Green)}
    val xValues = remember { mutableStateListOf<Float>() }
    val yValues = remember { mutableStateListOf<Float>() }
    val xDirections = remember { mutableStateListOf<Float>() }
    val yDirections = remember { mutableStateListOf<Float>() }
    val ballPositions = remember { mutableStateListOf<Offset>() }
    val gameIsOn = remember { mutableStateOf(true)}

    // Launched effect created a different thread for each ball to move independently
    LaunchedEffect(Unit) {
        /*
            Create 6 offset positions for balls.
            This state being updated will call the
            create ball function and delay for 3 seconds
         */

        for (i in 1..6){

            // x and y values lists are to randomly position balls on creation
            xValues.add((-120..170).random().toFloat())
            yValues.add((-340..390).random().toFloat())
            ballPositions.add(Offset(xValues[i-1], yValues[i-1]))
            // Randomly assign directions for the balls to go when created
            xDirections.add(if ((0..1).random() == 0) 3f else -3f)
            yDirections.add(if ((0..1).random() == 0) 3f else -3f)

            ballCount.value++

            // Delay the creating of each ball by 3 seconds.
            delay(3000)
            /*
                Setting gameIsOn to false will remove the balls from the screen and display a
                game over message, after 3 seconds to allow the user to see the sixth ball moving
             */

            if (ballCount.value == 6){
                delay(3000)
                gameIsOn.value = false
            }

        }
    }

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (!gameIsOn.value){
            Text(text = "Game Over!\nBall Count: 6")
        }else {
            Text(text = "Ball Count: " + ballCount.value.toString())

            /*
                Everytime the state of the lists are updated this will create a new ball
                forEachIndexed loop to assign each balls attributes separately
                Passing lists instead of individual values to allow them to be modified
                inside of the function
                Passing individual color to prevent assigning the same color to every ball
             */

            xValues.forEachIndexed { index, value ->
                CreateBall(
                    ballColors[index],
                    xValues,
                    yValues,
                    xDirections,
                    yDirections,
                    ballPositions,
                    index
                )
            }
        }
    }
}

@Composable
fun CreateBall(
    color: Color,
    x: MutableList<Float>,
    y: MutableList<Float>,
    xDirections: MutableList<Float>,
    yDirections: MutableList<Float>,
    ballPositions: MutableList<Offset>,
    index: Int
){
    /*
        Use mutable lists trigger this function, creating a new ball
        everytime the state is updated
     */

    /*
        Launched effect created a separate thread ensuring each ball can check its positioning
        at the same time
     */

    /*
        Animatable makes movement smooth, avoiding jittery movements
         than using infinite transition and
        animate float.
     */
    val xPos = remember { Animatable(x[index]) }
    val yPos = remember { Animatable(y[index]) }


    LaunchedEffect(Unit) {
        while (true){
            // Updating ball position and snapping it to that position
            xPos.snapTo(xPos.value + xDirections[index])
            yPos.snapTo(yPos.value + yDirections[index])

            // Storing positions to check for collision with screen edges and other balls
            x[index] += xDirections[index]
            y[index] += yDirections[index]
            ballPositions[index] = Offset(x[index], y[index])

            // Change the balls direction if it touches the sides of the screen
            // 50 is the size of each ball
            if (x[index] <= -180 || x[index] + 50 >= 230){
                xDirections[index] = -xDirections[index]
            }
            // Change the balls direction if it touches the top or bottom of the screen
            // 50 is the size of each ball
            if (y[index] <= -400 || y[index] + 50 >= 450){
                yDirections[index] = -yDirections[index]
            }

            // using for each indexed for current ball position and collision ball position
            ballPositions.forEachIndexed{positionIndex, position ->
                if (positionIndex != index){
                    /*
                        Get the distance between current ball and every other ball
                        use ballDistance value to detect ball collision
                     */
                    val ballDistance = (position - ballPositions[index]).getDistance()
                    if (ballDistance <= 50){
                        // Index for current ball
                        xDirections[index] = -xDirections[index]
                        yDirections[index] = -yDirections[index]
                        /*
                            Use position index to get the ball
                            being hit and change ball the direction
                         */
                        xDirections[positionIndex] = -xDirections[positionIndex]
                        yDirections[positionIndex] = -yDirections[positionIndex]
                    }
                }
            }
            // Delay the movement to slow the ball movement. This makes movement look smoother
            delay(20)
        }
    }

    /*
    Returns each ball to be rendered within the Box layout element
     */

    return Canvas(
        modifier = Modifier
            .size(50.dp)
            // Offset for the starting position of the ball
            .offset(x[index].dp, y[index].dp)
    ) {
        drawCircle(
            color = color,
            radius = size.minDimension / 2,

            )
    }
}
