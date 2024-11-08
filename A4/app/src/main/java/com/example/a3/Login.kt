package com.example.a3

import android.database.sqlite.SQLiteOpenHelper
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.a3.pages.LoginScreen
import com.example.a3.pages.SignUpScreen
import com.example.a3.pages.User
import androidx.activity.ComponentActivity


// Login takes two lambda function or a function with no return value or parameters
// This allows redirect to WelcomeScreen after login and
// Redirect to login after successful signup
@Composable
fun Login (modifier: Modifier= Modifier,
           onLoginSuccess: () -> Unit,
           onSignUpSuccess: () -> Unit
){

//    The bottom nav bar items and icons
    val navItemList = listOf(
        NavItem(name = "Login", Icons.Default.AccountCircle),
        NavItem(name = "Sign Up", Icons.Default.Create),
    )
//    Selected option for current page choice
    var selectedOption by remember { mutableStateOf(value = 0)}
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar={
            // Nav bar containing the text and icons
            // Updates the selectedOption from index
            NavigationBar {
                navItemList.forEachIndexed{index, navItem ->
                    NavigationBarItem(selected = selectedOption==index,
                        onClick = {selectedOption = index},
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = "icon")
                        },
                        label ={
                            Text(text = navItem.name)
                        }
                    )
                }
            }
        }
    ){innerPadding ->
        LoginNav(modifier = Modifier.padding(innerPadding),
            selectedOption,
            onLoginSuccess,
            // Redirect to login
            onSignUpSuccess = { selectedOption = 0})
    }
}

//LoginNav defines mutable array of null User to share and save user info between login and signup
@Composable
fun LoginNav(modifier: Modifier,
             selectedOption: Int,
             onLoginSuccess: () -> Unit,
             onSignUpSuccess: () -> Unit) {
    val users: Array<User?> = remember { arrayOfNulls(3) }
    val user = User("admin@gmail.com", "password")
    val user2 = User("test@gmail.com", "password")
    users[0] = user
    users[1] = user2

    when(selectedOption){

        // When selectedOption is changed change the activity
        0-> LoginScreen(onLoginSuccess = onLoginSuccess, users = users)
        1-> SignUpScreen(onSignUpSuccess = onSignUpSuccess, users = users)
    }
}

