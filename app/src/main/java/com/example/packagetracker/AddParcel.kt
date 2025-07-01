package com.example.packagetracker

import androidx.compose.foundation.layout.Column
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
fun AddParcelDialog(
    onDismiss: () -> Unit,
    onAdd: (name: String, trackingNumber: String, courier: String) -> Unit
) {
    // UI για να πάρεις τα στοιχεία από τον χρήστη
    // πχ TextFields για name, trackingNumber, courier

    var name by remember { mutableStateOf("") }
    var trackingNumber by remember { mutableStateOf("") }
    var courier by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onAdd(name, trackingNumber, courier)
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Add Parcel") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )
                OutlinedTextField(
                    value = trackingNumber,
                    onValueChange = { trackingNumber = it },
                    label = { Text("Tracking Number") }
                )
                OutlinedTextField(
                    value = courier,
                    onValueChange = { courier = it },
                    label = { Text("Courier") }
                )
            }
        }
    )
}


