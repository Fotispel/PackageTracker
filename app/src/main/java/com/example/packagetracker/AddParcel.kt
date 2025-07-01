package com.example.packagetracker

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun AddParcelDialog(onDismiss: () -> Unit) {
    var parcelCodeText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Please insert a new parcel")
        },
        text = {
            OutlinedTextField(
                value = parcelCodeText,
                onValueChange = { parcelCodeText = it },
                label = { Text("Parcel Code") }
            )
        },
        confirmButton = {
            TextButton(onClick = {
                if (parcelCodeText.isNotBlank()) {
                    println("Parcel code added: $parcelCodeText")
                    CourierApiFetch.fetchTrackingInfo(parcelCodeText)
                    onDismiss()
                } else {
                    println("No parcel code provided")
                }
            }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Dismiss")
            }
        }
    )
}


