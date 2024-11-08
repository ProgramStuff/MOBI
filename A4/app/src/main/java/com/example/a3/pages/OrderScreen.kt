package com.example.a3.pages


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun OrderScreen(modifier: Modifier = Modifier){


    var vanillaPrice = 40.00;
    var chocolatePrice = 45.00;
    var velvetPrice = 48.00;

    // Mutable variables for cake order count
    var vanillaCount = remember { mutableStateOf("0") };
    var chocolateCount = remember { mutableStateOf("0") };
    var velvetCount = remember { mutableStateOf("0") };

    // Mutable variables for individual cake order price
    var vanillaTotal = remember { mutableStateOf("0") };
    var chocolateTotal = remember { mutableStateOf("0") };
    var velvetTotal = remember { mutableStateOf("0") };

    // Mutable variables for order total and order status
    var orderTotal = remember { mutableStateOf("0") };
    var orderStatus = remember { mutableStateOf("") };


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(30.dp))

        /*** Order Page title ***/

        Text(text = "Order",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = "Place an order it will be ready for you tomorrow!",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.padding(30.dp))

        /*** Vanilla Cake Quantity Selection ***/

        Row ( Modifier.padding(end = 70.dp)) {
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
                        append("\tClassic Vanilla Dream QTY: \n")
                    }
                    // Apply style to the second section of the string
                    withStyle(
                        style = SpanStyle(
                        )
                    ) {
                        append("$40 for 10-inch")
                    }

                }
            )
            // Update the cake count, single cake price and order total on change
            TextField(modifier = Modifier
                .width(65.dp)
                .padding(start = 10.dp),
                value = vanillaCount.value,
                onValueChange = {vanillaCount.value = it
                    vanillaTotal.value = cakeTotal(it, vanillaPrice)
                    orderTotal.value = calcOrderTotal(vanillaTotal.value, chocolateTotal.value, velvetTotal.value)
                })


        }

        Spacer(modifier = Modifier.padding(20.dp))

        /*** Chocolate Cake Quantity Selection ***/

        Row ( Modifier.padding(end = 75.dp)) {
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
                        append("\tDecadent Chocolate Bliss:\n")
                    }
                    withStyle(
                        // Apply style to the second section of the string
                        style = SpanStyle(
                        )
                    ) {
                        append("$45 for 10-inch")
                    }

                }
            )
            // Update the cake count, single cake price and order total on change
            TextField(modifier = Modifier
                .width(110.dp)
                .padding(start = 30.dp), value = chocolateCount.value,
                onValueChange = {chocolateCount.value = it
                chocolateTotal.value = cakeTotal(it, chocolatePrice)
                    orderTotal.value = calcOrderTotal(vanillaTotal.value, chocolateTotal.value, velvetTotal.value)
                })
        }

        Spacer(modifier = Modifier.padding(20.dp))

        /*** Velvet Cake Quantity Selection ***/

        Row ( Modifier.padding(end = 60.dp)) {
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
                        append("\tRed Velvet Temptation:\n")
                    }
                    withStyle(
                        // Apply style to the second section of the string
                        style = SpanStyle(
                        )
                    ) {
                        append(
                            "$48 for 10-inch"
                        )
                    }

                }
            )
            // Update the cake count, single cake price and order total on change
            TextField(modifier = Modifier
                .width(95.dp)
                .padding(start = 40.dp),
                value = velvetCount.value,
                onValueChange = {velvetCount.value = it
                    velvetTotal.value = cakeTotal(it, velvetPrice)
                    orderTotal.value = calcOrderTotal(vanillaTotal.value, chocolateTotal.value, velvetTotal.value)
                })


        }

        Spacer(modifier = Modifier.padding(10.dp))

        /*** Cake Totals Selection ***/

        Row {
            Text(text = orderStatus.value)
        }
        Spacer(modifier = Modifier.padding(10.dp))

        Row {
            Text(text = "Vanilla Dream Total: $" + vanillaTotal.value)
        }
        Row {
            Text(text = "Decadent Chocolate Total: $" + chocolateTotal.value)
        }
        Row {
            Text(text = "Red Velvet Total: $" + velvetTotal.value)
        }
        Row {
            Text(text = "Order Total: $" + orderTotal.value)
        }


        Spacer(modifier = Modifier.padding(10.dp))

        // Update order status to display message to user
        Row {
            Button(onClick = {
                // Using inline conditional statement to return order message
                orderStatus.value =
                    if (orderTotal.value == "0.0" || orderTotal.value == "0") "Error Placing Order."
                else "Order Placed Successfully!"
            })
            {
                Text(text = "Place Order")
                
            }
        }

    }
}

/*** Functions ***/


fun cakeTotal(quantity: String, price: Double): String{
    // Expects the quantity of the selected cake and the price of that cake
    // Using toIntOrNull to valid the users input
    var amount = quantity.toIntOrNull();
    if (amount == null){
        return "Enter a number";
    }else {
        var total = amount * price;
        return total.toString()
    }
}

fun calcOrderTotal(vanillaTotal: String, chocolateTotal : String, velvetTotal : String): String{
    // Expects the total price for each individual cake
    // Using toIntOrNull to valid the users input

    var vanillaCost = vanillaTotal.toDoubleOrNull();
    var chocolateCost = chocolateTotal.toDoubleOrNull();
    var velvetCost = velvetTotal.toDoubleOrNull();

    // Set the cost of the cake to 0 if input is invalid
    if (vanillaCost == null){
        vanillaCost = 0.0
    }
    if(chocolateCost == null){
        chocolateCost = 0.0
    }
    if (velvetCost == null){
        velvetCost = 0.0
    }
    return (vanillaCost + chocolateCost + velvetCost).toString()
}

