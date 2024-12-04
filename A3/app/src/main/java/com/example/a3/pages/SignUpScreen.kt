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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(users: Array<User?>, onSignUpSuccess: () -> Unit) {
    var email = remember { mutableStateOf("") };
    var name = remember { mutableStateOf("") };
    var password = remember { mutableStateOf("") };
    var welcomeMsg = remember { mutableStateOf("") };
    var SignUpSuccess = remember { mutableStateOf(false)}
    var nameError = remember { mutableStateOf("")}
    var emailError = remember { mutableStateOf("")}

    var passwordError = remember { mutableStateOf("")}


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text = "Sign Up")
        Spacer(modifier = Modifier.padding(40.dp))
        /*** Name Field With Validation ***/

        OutlinedTextField(
            value = name.value,
            onValueChange = {
                name.value = it
                nameError.value = if (it.isBlank()) "Please enter name" else ""},
            label = { Text(text = "Name")}
        )
        if (nameError.value.isNotEmpty()) Text(text = nameError.value, color = MaterialTheme.colorScheme.error)

        Spacer(modifier = Modifier.padding(10.dp))

        /*** Email Field With Validation ***/

        OutlinedTextField(            value = email.value,
            onValueChange = {
                email.value = it
                emailError.value = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) "Invalid email" else ""
            },
            label = { Text(text = "Email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        if (emailError.value.isNotEmpty()) Text(text = emailError.value, color = MaterialTheme.colorScheme.error)

        Spacer(modifier = Modifier.padding(10.dp))

        /*** Password Field With Validation ***/

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
                // Using regex to compare user input to expected character
                val passwordPattern = Regex("^[a-zA-Z0-9@_]+$")

                // Only assign password value when it matches the regex
                passwordError.value = when {
                    it.length < 8 -> "Must be at least 8 characters"
                    !passwordPattern.matches(it) -> "Password can only contain letters, num @ _"
                    else -> ""
                }
            },
            label = { Text(text = "Password")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (passwordError.value.isNotEmpty())
            Text(text = passwordError.value, color = MaterialTheme.colorScheme.error)


        Spacer(modifier = Modifier.padding(10.dp))

        Button(onClick = {
            // Update the array with the new user
            SignUpSuccess.value = true
            if (SignUpSuccess.value) {
                users[2] = User(email.value, password.value)
                welcomeMsg.value = "Sign up Successful"
                //  Redirect to login screen
                onSignUpSuccess()
            }
        }) {
            Text(text = "Sign Up")
        }
        Text(text = welcomeMsg.value)
    }

}