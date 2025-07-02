package com.example.packagetracker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun ParcelScreen() {
    val showAddScreen = remember { mutableStateOf(false) }
    val parcels by ParcelRepository.parcels.collectAsState()
    val isRefreshing = remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing.value)

    // Pull-to-refresh function
    val onRefresh: () -> Unit = {
        isRefreshing.value = true
        // Refresh all parcels
        parcels.forEach { parcel ->
            ParcelRepository.trackParcel(parcel.id, parcel.trackingNumber, parcel.courier)
        }
        // Reset refresh state after tracking requests complete
        // In a real app, you'd want to track when all requests finish
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000) // Simulate network delay
            isRefreshing.value = false
        }
    }

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

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = onRefresh,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Text(
                    text = "Parcel Tracker",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (parcels.isEmpty()) {
                    // Empty state message
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "No parcels added yet.\nTap + to add your first parcel!",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Pull down to refresh when you have parcels",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                } else {
                    // LazyColumn for parcels
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(parcels, key = { it.id }) { parcel ->
                            ParcelCard(
                                parcel = parcel,
                                onDelete = { ParcelRepository.removeParcel(parcel.id) },
                                onRefresh = {
                                    ParcelRepository.trackParcel(parcel.id, parcel.trackingNumber, parcel.courier)
                                }
                            )
                        }

                        // Add space at the end for the floating button
                        item {
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }
            }
        }

        // Floating Add Button
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            AddButton(onClick = { showAddScreen.value = true })
        }
    }
}


@Composable
fun ParcelCard(
    parcel: Parcel,
    onDelete: () -> Unit,
    onRefresh: () -> Unit
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            //if light theme, use surface color, otherwise use surface variant
            containerColor = if (isSystemInDarkTheme()) {
                Color.Black
            } else {
                Color.White
            }
        ),
        border = BorderStroke(
            2.dp,
            if (parcel.isTracked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Parcel Icon",
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = parcel.name,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Row {
                    IconButton(onClick = onRefresh) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = onDelete) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tracking Information
            InfoRow(label = "Status", value = parcel.status)
            InfoRow(label = "Courier", value = parcel.courier)
            InfoRow(label = "Tracking Number", value = parcel.trackingNumber)

            if (parcel.lastUpdated.isNotEmpty() && parcel.lastUpdated != "N/A") {
                InfoRow(label = "Last Updated", value = parcel.lastUpdated)
            }

            // Status indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = if (parcel.isTracked) "✓ Tracked" else "○ Pending",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (parcel.isTracked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun AddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.size(64.dp),
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}