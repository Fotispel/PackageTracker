package com.example.packagetracker

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import java.io.Serializable
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Parcel(
    val id: String = "",
    val name: String = "",
    val trackingNumber: String = "",
    val courier: String = "",
    val status: String = "Unknown",
    val isTracked: Boolean = false, // αν έχει βρεθεί από το API
    val lastUpdated: String = ""
) : Serializable

data class TrackingInfo(
    val trackingNumber: String,
    val courier: String,
    val status: String,
    val subtag: String,
    val subtagMessage: String,
    val originCity: String,
    val destinationCity: String,
    val expectedDelivery: String,
    val lastUpdated: String
) : Serializable

object ParcelRepository {
    private val _parcels = MutableStateFlow<List<Parcel>>(emptyList())
    val parcels: StateFlow<List<Parcel>> = _parcels.asStateFlow()

    private const val PREFS_NAME = "parcel_prefs"
    private const val KEY_PARCELS = "parcels_json"

    fun saveParcels(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(_parcels.value)
        prefs.edit().putString(KEY_PARCELS, json).apply()
    }

    fun loadParcels(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_PARCELS, null)
        if (json != null) {
            val type = object : TypeToken<List<Parcel>>() {}.type
            val list: List<Parcel> = Gson().fromJson(json, type)
            _parcels.value = list
        }
    }

    fun addParcel(name: String, trackingNumber: String, courier: String) {
        val newParcel = Parcel(
            id = UUID.randomUUID().toString(),
            name = name,
            trackingNumber = trackingNumber,
            courier = courier,
            status = "Pending...",
            isTracked = false
        )

        val currentList = _parcels.value.toMutableList()
        currentList.add(newParcel)
        _parcels.value = currentList

        // Άμεσα κάνε tracking
        trackParcel(newParcel.id, trackingNumber, courier)
    }

    fun updateParcelWithTrackingInfo(trackingNumber: String, trackingInfo: TrackingInfo) {
        val currentList = _parcels.value.toMutableList()
        val index = currentList.indexOfFirst { it.trackingNumber == trackingNumber }

        if (index != -1) {
            val updatedParcel = currentList[index].copy(
                courier = trackingInfo.courier,
                status = trackingInfo.subtagMessage.ifEmpty { trackingInfo.status },
                isTracked = true,
                lastUpdated = trackingInfo.lastUpdated
            )
            currentList[index] = updatedParcel
            _parcels.value = currentList

            Log.d("ParcelRepository", "Updated parcel: ${updatedParcel.name} with status: ${updatedParcel.status}")
        }
    }

    fun markParcelAsNotFound(trackingNumber: String) {
        val currentList = _parcels.value.toMutableList()
        val index = currentList.indexOfFirst { it.trackingNumber == trackingNumber }

        if (index != -1) {
            val updatedParcel = currentList[index].copy(
                status = "Not Found",
                isTracked = false
            )
            currentList[index] = updatedParcel
            _parcels.value = currentList
        }
    }

    fun trackParcel(parcelId: String, trackingNumber: String, courier: String) {
        // Καλούμε το CourierApiFetch
        CourierApiFetch.fetchTrackingInfo(trackingNumber, courier)
    }

    fun removeParcel(parcelId: String) {
        val currentList = _parcels.value.toMutableList()
        currentList.removeAll { it.id == parcelId }
        _parcels.value = currentList
    }
}