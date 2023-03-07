package com.example.thuctap.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val label: String, val icons: ImageVector) {

    object Home : NavigationItem("home", "Home", Icons.Default.Home)

    object Notifications :
        NavigationItem("notifications", "Notifications", Icons.Default.Notifications)

    object Settings : NavigationItem("setting","Settings",Icons.Default.Settings)

    object Account: NavigationItem("account","Account",Icons.Default.AccountCircle)
    object EditAccount: NavigationItem("account/editAccount","Account",Icons.Default.AccountCircle)
    object Signin: NavigationItem("account/Signin","Signin",Icons.Default.AccountCircle)
    object SignUp: NavigationItem("account/Signin/SignUp","SignUp",Icons.Default.AccountCircle)
}
