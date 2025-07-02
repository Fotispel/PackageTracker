package com.example.packagetracker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SettingScreen() {
    val showAddScreen = remember { mutableStateOf(false) }
    val parcels by ParcelRepository.parcels.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (showAddScreen.value) {
            AddParcelDialog(
                onDismiss = { showAddScreen.value = false },
                onAdd = { name, trackingNumber, courier ->
                    ParcelRepository.addParcel(name, trackingNumber, courier)
                    showAddScreen.value = false
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}