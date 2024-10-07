package com.example.a2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a2.ui.theme.A2Theme
import kotlin.reflect.typeOf


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator(modifier: Modifier = Modifier) {
//    Mutable variable for state management
    var num1 = remember { mutableStateOf("0") };
    var num2 = remember { mutableStateOf("0") };
    var operator = remember { mutableStateOf("+") };
    var result = remember { mutableStateOf("0") };

//Column layout to add rows for buttons, improving UI
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(text = "Result", fontSize = 20.sp)

        //Displays the result above other components like a real calculator. Improves UI
        //The value of result is updated when the '=' button is clicked
        Text(text = result.value,
            Modifier
                .background(color = Color.Gray)
                .padding(10.dp)
                .width(260.dp))

        Spacer(modifier = Modifier.padding(10.dp))
        /*
         * Text field for user input, updates mutable variables when the value of the text field
         * is changed
         */

        Row {
            TextField(modifier = Modifier
                .padding(10.dp)
                .width(130.dp),
                value = num1.value, onValueChange = { num1.value = it } )
            // Dispay the operator to improve UI
            Text(modifier = Modifier.padding(top = 25.dp), text = operator.value)

            TextField(modifier = Modifier
                .padding(10.dp)
                .width(130.dp),
                value = num2.value,
                onValueChange = { num2.value = it })
        }

        Spacer(modifier = Modifier.padding(10.dp))

        /*
         * Buttons for selecting the operation to perform, they update the value of
         * of the mutable operator variable
         */
        Row {
            Button(onClick = {operator.value = "*"}) {
                Text(text = "x")
            }
            Button(modifier = Modifier
                .padding(start = 8.dp, end = 8.dp),
                onClick = {operator.value = "/"}) {
                Text(text = "/")
            }
            Button(modifier = Modifier
                .padding(end = 8.dp),
                onClick = {operator.value = "+"}) {
                Text(text = "+")
            }
            Button(onClick = {operator.value = "-"}) {
                Text(text = "-")
            }
        }
        /*
         * Button to calculate the equation. When clicked calls the calculate
         * function
         */
        Row {
            Button(onClick = {result.value = calculate(operator.value, num1.value, num2.value)}) {
                Text(text = "=")
            }
        }

    }
}

fun calculate(operator: String, num1: String, num2: String): String {
    /*
     * Converting the strings passed in to Doubles or null.
     * If the values contain anything other than a number this will return null
     * allowing for error checking
     */
    var fNum = num1.toDoubleOrNull()
    var sNum = num2.toDoubleOrNull()
    if (fNum == null || sNum == null){
        return "Enter numbers only"
    }
    /*
     * Using a when case (similar to a switch case) to perform the desired operation
     */
    var result = when (operator){
        "*" -> fNum * sNum
        // Check for attempting to divide by zero and return error message
        "/" -> if (sNum == 0.0){
            return "Cannot Divide by Zero"
        }else{
            fNum / sNum
        }
        "+" -> fNum + sNum
        "-" -> fNum - sNum
        else -> {
            0
        }
    }
    /*
     * Converting the result to String to display in a text box without errors
     */
    return result.toString()

}

// Preview function for easily editing UI
@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    A2Theme {
        Calculator()
    }
}
