package com.example.androidui.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androidui.R
import com.example.androidui.viewmodel.MyViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun MyScreen() {

    // Create or retrieve a ViewModel instance
    val myViewModel: MyViewModel = viewModel()

    // Access the surfaceColorInt property from the ViewModel instance
    val surfaceColorInt by myViewModel::surfaceColorInt
    // Convert surfaceColorInt to Color
    var surfaceColor = Color(surfaceColorInt)
    var sliderValue by rememberSaveable { mutableStateOf(0f) }
    var textFieldText by rememberSaveable { mutableStateOf("") }
    val showDialog = rememberSaveable { mutableStateOf(false) }

    val actorNames = List(50) { "Actor ${it + 1}" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(color = surfaceColor, modifier = Modifier.fillMaxWidth()) {
            Text("Hello World", modifier = Modifier.padding(16.dp))
        }

        Button(onClick = {
            myViewModel::toggleColor.invoke()
        }) {
            Text("Toggle")
        }

        Button(onClick = {
            myViewModel::resetColor.invoke()
        }) {
            Text("Reset")
        }

        // Update TextField to use textFieldText
        TextField(
            value = textFieldText,
            onValueChange = { textFieldText = it },
            label = { Text("Enter text") }
        )


        Row(verticalAlignment = Alignment.CenterVertically) {
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..100f,
                modifier = Modifier.weight(1f)
            )
            Text("${sliderValue.toInt()}")
        }



        LazyColumn(modifier = Modifier.height(200.dp)) {
            items(actorNames.size) { index ->
                // Each item with a fixed height
                Text(actorNames[index], modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(8.dp))
            }
        }


        // Card with clickable image
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable { showDialog.value = true }
        ) {
            Image(
                painter = painterResource(id = R.drawable.my_image),
                contentDescription = "Sample Image"
            )
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Dialog Title") },
                text = { Text("Hello there!") },
                confirmButton = {
                    Button(
                        onClick = { showDialog.value = false }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }}
