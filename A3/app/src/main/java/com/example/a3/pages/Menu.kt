package com.example.a3.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a3.R

@Composable
fun Menu(modifier: Modifier = Modifier){

    // Get images from resource folder in drawable
    val vanillaImage = painterResource(R.drawable.vanilla)
    val chocolateImage = painterResource(R.drawable.chocolate)
    val velvetImage = painterResource(R.drawable.velvet)



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(30.dp))
        /*** Menu Page title ***/

        Text(text = "Menu",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = "Browse our handcrafted cakes!",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.padding(30.dp))

        /*** Vanilla Cake Section ***/

        Row ( Modifier.padding(end = 30.dp)) {
            // Display cake image
            Image(
                painter = vanillaImage,
                contentDescription = "Vanilla Cake",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(90.dp)
                    .width(110.dp)
                    .border(BorderStroke(1.dp, Color.Magenta))
            )
            Text(
                modifier = Modifier.padding(start = 5.dp),
                // Using an annotated to apply different styles to a single string
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
                    // Apply style to the second section of the string
                    withStyle(
                        style = SpanStyle(
                        )
                    ) {
                        append("\t$40 for 10-inch")
                    }

                }
            )
        }

        Spacer(modifier = Modifier.padding(20.dp))

        /*** Chocolate Cake Section ***/

        Row ( Modifier.padding(end = 20.dp)) {
            // Display cake image
            Image(
                painter = chocolateImage,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(90.dp)
                    .width(110.dp)
                    .border(BorderStroke(1.dp, Color.Magenta))
            )

            Text(
                modifier = Modifier.padding(start = 5.dp),
                // Using an annotated to apply different styles to a single string
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
                        append("\t$45 for 10-inch")
                    }

                }
            )
        }

        Spacer(modifier = Modifier.padding(20.dp))

        /*** Velvet Cake Section ***/

        Row ( Modifier.padding(end = 30.dp)) {
            // Display cake image
            Image(
                painter = velvetImage,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(90.dp)
                    .width(110.dp)
                    .border(BorderStroke(1.dp, Color.Magenta))
            )

            Text(
                modifier = Modifier.padding(start = 5.dp),
                // Using an annotated to apply different styles to a single string
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
                            "\t$48 for 10-inch"
                        )
                    }

                }
            )
        }

    }
}