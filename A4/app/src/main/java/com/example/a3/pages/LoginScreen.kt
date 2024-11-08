package com.example.a3.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// User class to hold implements login function
class User(private val validUsername: String, private val validPassword: String) {
    fun login(inputUsername: String, inputPassword: String): String {
        return if (inputUsername == validUsername && inputPassword == validPassword) {
            "Login Success"
        } else {
            "Login Failed."
        }
    }
}

// Login screen takes a lambda function or a function with no return value or parameters
// This allows navigation to WelcomeScreen after login
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, users: Array<User?>) {
    var email = remember { mutableStateOf("") };
    var password = remember { mutableStateOf("") };
    var welcomeMsg = remember { mutableStateOf("") };
    var emailError = remember { mutableStateOf("")}



    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text = "Login")
        Spacer(modifier = Modifier.padding(40.dp))


        /*** Email with validation ***/

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
                emailError.value = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) "Invalid email" else ""
            },
            label = { Text(text = "Email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        if (emailError.value.isNotEmpty()) Text(text = emailError.value, color = MaterialTheme.colorScheme.error)

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(value = password.value, onValueChange = {password.value = it},
            label = { Text(text = "Password")})

        Spacer(modifier = Modifier.padding(10.dp))

        // Loop through each user in array and try to login with current values
        Button(onClick = {
            users.forEach { user ->
                if (user != null) {
                    welcomeMsg.value = user.login(email.value, password.value)
                    if (welcomeMsg.value == "Login Success") {
                        onLoginSuccess()
                    }
                }

            }
        }) {
            Text(text = "Login")
        }
        Text(text = welcomeMsg.value)
    }

}