package com.thermosnap.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thermosnap.com.ui.theme.ThermoSnapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThermoSnapTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TemperatureConversion(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Helper color function
fun helpColor(hex: String): Color {
    return Color((android.graphics.Color.parseColor(hex)))
}

// Defining a custom colors
val screenBackground = helpColor("#e3e3e3")
val mainFonts = helpColor("#333333")
val lightFont = helpColor("#9e9e9e")
val boxContainer = helpColor("#ffffff")
val textFieldLine = helpColor("#e3e3e3")

// Main composable function
@Composable
fun TemperatureConversion(modifier: Modifier) {

    // Using states for ui changes and logic
    var inputTempValue by rememberSaveable {
        mutableStateOf("")
    }

    var outputTempValue by rememberSaveable {
        mutableStateOf("")
    }

    var inputScale by rememberSaveable {
        mutableStateOf("Celsius")
    }

    var outputScale by rememberSaveable {
        mutableStateOf<String?>("Fahrenheit")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = screenBackground)
    ) {

        // Main heading font
        Column(
            modifier = Modifier
                .padding(top = 44.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "TEMPERATURE CONVERSION",
                fontWeight = FontWeight.W900,
                fontSize = 20.sp,
                color = mainFonts,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Input heading text
            Text(
                "TEMPERATURE TO BE CONVERTED:",
                color = lightFont,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 40.dp, bottom = 4.dp)
                    .align(Alignment.Start)
            )
            Box(
                modifier = Modifier
                    .padding(start = 18.dp, end = 18.dp)
                    .background(
                        color = boxContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 6.dp,
                        bottom = 6.dp,
                        end = 16.dp
                    )
                ) {

                    // Input text field
                    TextField(
                        value = inputTempValue, onValueChange = {
                            inputTempValue = it
                        },
                        placeholder = {
                            Text(
                                "0",
                                color = mainFonts
                            )
                        },
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = mainFonts,
                            unfocusedTextColor = mainFonts,
                            focusedIndicatorColor = textFieldLine,
                            unfocusedLabelColor = textFieldLine,
                            unfocusedContainerColor = boxContainer,
                            focusedContainerColor = boxContainer,
                        )
                    )

                    // Input temperature scale buttons
                    Row(
                        modifier = Modifier
                            .background(
                                color = screenBackground,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Button(
                            onClick = {
                                inputScale = "Fahrenheit"
                            }, modifier = Modifier
                                .padding(start = 2.dp, top = 2.dp, bottom = 3.dp)
                                .height(34.dp)
                                .width(115.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = mainFonts,
                                containerColor = if (inputScale == "Fahrenheit") boxContainer else screenBackground
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                "Fahrenheit",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Button(
                            onClick = {
                                inputScale = "Kelvin"
                            }, modifier = Modifier
                                .padding(top = 2.dp, bottom = 3.dp)
                                .height(34.dp)
                                .width(115.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = mainFonts,
                                containerColor = if (inputScale == "Kelvin") boxContainer else screenBackground
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                "Kelvin",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Button(
                            onClick = {
                                inputScale = "Celsius"
                            }, modifier = Modifier
                                .padding(top = 2.dp, bottom = 3.dp, end = 2.dp)
                                .height(34.dp)
                                .width(115.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = mainFonts,
                                containerColor = if (inputScale == "Celsius") boxContainer else screenBackground
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                "Celsius",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(48.dp))

            // Output heading text
            Text(
                "TO WHICH SCALE:",
                color = lightFont,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 40.dp, bottom = 4.dp)
                    .align(Alignment.Start)
            )

            // Output temperature scale buttons
            Box(
                modifier = Modifier
                    .padding(start = 18.dp, end = 18.dp)
                    .background(
                        color = boxContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 6.dp,
                        end = 16.dp,
                        bottom = 6.dp
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .background(
                                color = screenBackground,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Button(
                            onClick = {
                                outputScale = "Fahrenheit"
                            }, modifier = Modifier
                                .padding(start = 2.dp, top = 2.dp, bottom = 3.dp)
                                .height(34.dp)
                                .width(115.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = mainFonts,
                                containerColor = if (outputScale == "Fahrenheit") boxContainer else screenBackground
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                "Fahrenheit",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Button(
                            onClick = {
                                outputScale = "Kelvin"
                            }, modifier = Modifier
                                .padding(top = 2.dp, bottom = 3.dp)
                                .height(34.dp)
                                .width(115.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = mainFonts,
                                containerColor = if (outputScale == "Kelvin") boxContainer else screenBackground
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                "Kelvin",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Button(
                            onClick = {
                                outputScale = "Celsius"
                            }, modifier = Modifier
                                .padding(top = 2.dp, bottom = 3.dp, end = 2.dp)
                                .height(34.dp)
                                .width(115.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = mainFonts,
                                containerColor = if (outputScale == "Celsius") boxContainer else screenBackground
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                "Celsius",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(48.dp))

            // Conversion result
            Text(
                "RESULT:",
                color = lightFont,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 40.dp, bottom = 4.dp)
                    .align(Alignment.Start)
            )

            Box(
                modifier = Modifier
                    .padding(start = 18.dp, end = 18.dp)
                    .background(
                        color = boxContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 6.dp,
                        end = 16.dp,
                        bottom = 6.dp
                    )
                ) {

                    // Result value and temperature scale
                    Row(
                        modifier = Modifier
                            .background(
                                color = boxContainer,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {

                        if (inputScale == "Celsius" && outputScale == "Fahrenheit") {
                            val inputTempValueToDouble = inputTempValue.toDoubleOrNull() ?: 0.0
                            val result = (inputTempValueToDouble * 9 / 5) + 32
                            outputTempValue = formatTemperature(result)
                        } else if (inputScale == "Fahrenheit" && outputScale == "Celsius") {
                            val inputTempValueToDouble = inputTempValue.toDoubleOrNull() ?: 0.0
                            val result = (inputTempValueToDouble - 32) * 5 / 9
                            outputTempValue = formatTemperature(result)
                        } else if (inputScale == "Celsius" && outputScale == "Kelvin") {
                            val inputTempValueToDouble = inputTempValue.toDoubleOrNull() ?: 0.0
                            val result = inputTempValueToDouble + 273.15
                            outputTempValue = formatTemperature(result)
                        } else if (inputScale == "Kelvin" && outputScale == "Celsius") {
                            val inputTempValueToDouble = inputTempValue.toDoubleOrNull() ?: 0.0
                            val result = inputTempValueToDouble - 273.15
                            outputTempValue = formatTemperature(result)
                        } else if (inputScale == "Fahrenheit" && outputScale == "Kelvin") {
                            val inputTempValueToDouble = inputTempValue.toDoubleOrNull() ?: 0.0
                            val result = (inputTempValueToDouble + 459.67) * 5 / 9
                            outputTempValue = formatTemperature(result)
                        } else if (inputScale == "Kelvin" && outputScale == "Fahrenheit") {
                            val inputTempValueToDouble = inputTempValue.toDoubleOrNull() ?: 0.0
                            val result = (inputTempValueToDouble - 273.15) * 9 / 5 + 32
                            outputTempValue = formatTemperature(result)
                        } else {
                            val inputTempValueToDouble = inputTempValue.toDoubleOrNull() ?: 0.0
                            outputTempValue = inputTempValueToDouble.toString()
                        }

                        Text(
                            "$outputTempValue $outputScale",
                            modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                            fontWeight = FontWeight.W600,
                            fontSize = 18.sp,
                            color = mainFonts
                        )
                    }
                }
            }
        }
    }
}

// Helper function to format temperature with up to four decimal places
fun formatTemperature(value: Double): String {
    return if (value % 1 == 0.0) {
        value.toInt().toString()
    } else {
        "%.4f".format(value).trimEnd('0').trimEnd('.')
    }
}