package com.example.a3.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(modifier: Modifier = Modifier){


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(30.dp))

        /*** Home Page title ***/

        Text(text = "Sweet Haven Bakery",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))

        // Home page introduction

        Text(text = "Welcome to Sweet Haven Bakery! Indulge in our handcrafted delights. " +
                "Explore our signature cakes, made with" +
                " the finest ingredients. Treat yourself today!",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
            )

        Spacer(modifier = Modifier.padding(20.dp))

        /*** Vanilla Cake Info Selection ***/

        Row {
            Text(
                // I am using an annotated to apply different styles to a single string
                text = buildAnnotatedString {
                    withStyle(
                        // Apply style to the first section of the string
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                        )
                    ) {
                        append("1.\tClassic Vanilla Dream: \n")
                    }
                    withStyle(
                        // Apply style to the second section of the string
                        style = SpanStyle(
                        )
                    ) {
                        append("\nA soft vanilla cake layered with" +
                                " vanilla buttercream, perfect for any occasion.")
                    }

                }
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        /*** Chocolate Cake Info Selection ***/

        Row {
            Text(
                // I am using an annotated to apply different styles to a single string
                text = buildAnnotatedString {
                    withStyle(
                        // Apply style to the first section of the string
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                        )
                    ) {
                        append("2.\tDecadent Chocolate Bliss:\n")
                    }
                    withStyle(
                        // Apply style to the second section of the string
                        style = SpanStyle(
                        )
                    ) {
                        append("\nRich, fudgy chocolate cake with velvety chocolate ganache, designed " +
                                "for the true chocolate lover.\n")
                    }

                }
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        /*** Velvet Cake Info Selection ***/

        Row {
            Text(
                // I am using an annotated to apply different styles to a single string
                text = buildAnnotatedString {
                    withStyle(
                        // Apply style to the first section of the string
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                        )
                    ) {
                        append("3.\tRed Velvet Temptation:\n")
                    }
                    withStyle(
                        // Apply style to the second section of the string
                        style = SpanStyle(
                        )
                    ) {
                        append(
                            "\nA velvety red cake with a hint of cocoa, topped with" +
                                    " cream cheese frosting. This elegant treat is as" +
                                    " beautiful as it is delicious.\n"
                        )
                    }

                }
            )
        }

    }
}