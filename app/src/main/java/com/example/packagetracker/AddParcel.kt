package com.example.packagetracker

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable



@Composable
fun AddParcelDialog() {
    var ParcelCode = -1

    AlertDialog(
        onDismissRequest = {
            //close dialog

        },
        title = {
            Text(text = "Please insert a new parcel")
        },
        text = {
            TextField(
                value = "",
                onValueChange = { ParcelCode = it.toIntOrNull() ?: -1 },
                label = { Text("Parcel Name") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Parcel"
                    )
                }
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (ParcelCode != -1) {
                        // Handle adding the parcel with ParcelCode
                        // For example, save it to a database or a list
                    } else {
                        // Handle invalid input
                    }
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    // Handle dismiss
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}