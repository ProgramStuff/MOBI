package com.example.a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//                Surface container to display UI components
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    Function call for UI components in emulator/actual app
                ) {TextMessage(name = "Jordan Kelsey",
                    description = "Application for MOBI Assignment 1 to display my name and course",
                    subject="IT Programming")
            }
        }
    }
}

//Composable function
@Composable
fun TextMessage(name: String, description: String, subject: String, modifier: Modifier = Modifier) {
//  Initializing immutable variables
    val fontChoice = FontFamily.SansSerif
    val weightChoice = FontWeight.W700
    val textPositioning = TextAlign.Center

//    Box layout to display and move UI elements
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray),
        contentAlignment = Alignment.Center,
//    Defining the text content, styles and positioning
    ){
        Text(
            text = name,
            modifier = modifier,
            fontSize = 25.sp,
            color = Color.Black,
            style = TextStyle(
                fontFamily = fontChoice,
                fontWeight = weightChoice,
                fontStyle = FontStyle.Italic,
                letterSpacing = 0.5.em,
            )
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 80.dp, start = 38.dp, end = 38.dp),
            fontSize = 20.sp,
            color = Color.Yellow,
            style = TextStyle(
                fontFamily = fontChoice,
                fontWeight = weightChoice,
            )
        )
        Text(
            text = subject,
            modifier = Modifier.padding(top = 140.dp),
            fontSize = 15.sp,
            color = Color.Blue,
            style = TextStyle(
                fontFamily = fontChoice,
                fontWeight = weightChoice,
                )
        )
        OutlinedTextField(value = "",
            onValueChange = {},
            label = { Text(text = "Submit")},
            modifier = Modifier.fillMaxWidth()
                .padding(top = 250.dp)
        )
    }
}

//Composable preview function
@Preview(showBackground = true)
@Composable
fun MessagePreview() {
//    Calling function to render UI components within preview
    TextMessage(name = "Jordan Kelsey", description = "Application for MOBI to display my name and course", subject="IT Programming")
}

