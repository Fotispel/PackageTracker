package com.example.packagetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.packagetracker.ui.theme.PackageTrackerTheme
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PackageTrackerTheme {
                MainScreenContent()
            }
        }
    }
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

sealed class Screen(val route: String) {
    object Parcels: Screen("parcels_screen")
    object Profile: Screen("profile_screen")
    object Setting: Screen("setting_screen")
}




val Local_shipping: ImageVector
    get() {
        if (_Local_shipping != null) return _Local_shipping!!

        _Local_shipping = ImageVector.Builder(
            name = "Local_shipping",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(240f, 800f)
                quadToRelative(-50f, 0f, -85f, -35f)
                reflectiveQuadToRelative(-35f, -85f)
                horizontalLineTo(40f)
                verticalLineToRelative(-440f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(120f, 160f)
                horizontalLineToRelative(560f)
                verticalLineToRelative(160f)
                horizontalLineToRelative(120f)
                lineToRelative(120f, 160f)
                verticalLineToRelative(200f)
                horizontalLineToRelative(-80f)
                quadToRelative(0f, 50f, -35f, 85f)
                reflectiveQuadToRelative(-85f, 35f)
                reflectiveQuadToRelative(-85f, -35f)
                reflectiveQuadToRelative(-35f, -85f)
                horizontalLineTo(360f)
                quadToRelative(0f, 50f, -35f, 85f)
                reflectiveQuadToRelative(-85f, 35f)
                moveToRelative(0f, -80f)
                quadToRelative(17f, 0f, 28.5f, -11.5f)
                reflectiveQuadTo(280f, 680f)
                reflectiveQuadToRelative(-11.5f, -28.5f)
                reflectiveQuadTo(240f, 640f)
                reflectiveQuadToRelative(-28.5f, 11.5f)
                reflectiveQuadTo(200f, 680f)
                reflectiveQuadToRelative(11.5f, 28.5f)
                reflectiveQuadTo(240f, 720f)
                moveTo(120f, 600f)
                horizontalLineToRelative(32f)
                quadToRelative(17f, -18f, 39f, -29f)
                reflectiveQuadToRelative(49f, -11f)
                reflectiveQuadToRelative(49f, 11f)
                reflectiveQuadToRelative(39f, 29f)
                horizontalLineToRelative(272f)
                verticalLineToRelative(-360f)
                horizontalLineTo(120f)
                close()
                moveToRelative(600f, 120f)
                quadToRelative(17f, 0f, 28.5f, -11.5f)
                reflectiveQuadTo(760f, 680f)
                reflectiveQuadToRelative(-11.5f, -28.5f)
                reflectiveQuadTo(720f, 640f)
                reflectiveQuadToRelative(-28.5f, 11.5f)
                reflectiveQuadTo(680f, 680f)
                reflectiveQuadToRelative(11.5f, 28.5f)
                reflectiveQuadTo(720f, 720f)
                moveToRelative(-40f, -200f)
                horizontalLineToRelative(170f)
                lineToRelative(-90f, -120f)
                horizontalLineToRelative(-80f)
                close()
                moveTo(360f, 420f)
            }
        }.build()

        return _Local_shipping!!
    }

private var _Local_shipping: ImageVector? = null



val navigationItems = listOf(
    NavigationItem(
        title = "Parcels",
        icon = Local_shipping,
        route = Screen.Parcels.route
    ),
    NavigationItem(
        title = "Profile",
        icon = Icons.Default.Person,
        route = Screen.Profile.route
    ),
    NavigationItem(
        title = "Setting",
        icon = Icons.Default.Settings,
        route = Screen.Setting.route
    )
)

@Composable
fun MainScreenContent() {
    val navController = rememberNavController()
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedNavigationIndex.intValue == index,
                        onClick = {
                            selectedNavigationIndex.intValue = index
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.title)
                        },
                        label = {
                            Text(
                                item.title,
                                color = if (index == selectedNavigationIndex.intValue)
                                    MaterialTheme.colorScheme.primary
                                else
                                    Color.Unspecified
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.surface,
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Parcels.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Parcels.route) {
                ParcelScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Setting.route) {
                SettingScreen()
            }
        }
    }
}