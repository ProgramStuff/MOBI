package com.example.a3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a3.ui.theme.A3Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            A3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartApp()
                }
            }
        }
    }
}


@Composable
fun StartApp() {
    val isLoggedIn = remember {mutableStateOf(false)}
    val isSignedUp = remember {mutableStateOf(false)}

    if (isLoggedIn.value) {
        WelcomeScreen()
    } else {
        // The lambda function that is called on successful login
        Login(onLoginSuccess = {isLoggedIn.value = true},
            // Successful set the value state to true to navigate to login
            onSignUpSuccess = { isSignedUp.value = true }
        )
    }
}

@Preview
@Composable
fun preview(){
    StartApp()
}

