package com.example.androidui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidui.ui.theme.AndroidUITheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Slider
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen() {
    // State variables
    var surfaceColor by rememberSaveable { mutableStateOf(Color.White) }
    var sliderValue by rememberSaveable { mutableStateOf(0f) }
    val textFieldValue = rememberSaveable { mutableStateOf(TextFieldValue()) }
    val showDialog = rememberSaveable { mutableStateOf(false) }

    // Actor names for LazyColumn
    val actorNames = listOf("Leonardo", "Meryl", "Tom", "Julia", "Brad")

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

        Button(onClick = { surfaceColor = Color.Red }) {
            Text("Toggle")
        }

        Button(onClick = { surfaceColor = Color.White }) {
            Text("Reset")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..100f,
                modifier = Modifier.weight(1f)
            )
            Text("${sliderValue.toInt()}")
        }

        TextField(
            value = textFieldValue.value,
            onValueChange = { textFieldValue.value = it }
        )

        // LazyColumn to display a list
        LazyColumn {
            items(actorNames.size) { index ->
                Text(actorNames[index])
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
    }
}
