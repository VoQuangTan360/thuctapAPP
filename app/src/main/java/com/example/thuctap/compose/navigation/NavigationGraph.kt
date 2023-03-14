package com.example.firebaseauthyt.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.thuctap.EditProfile
import com.example.thuctap.Profile
import com.example.thuctap.TapProfile
import com.example.thuctap.compose.Login.SigInScreen
import com.example.thuctap.compose.Login.view
import com.example.thuctap.compose.navigation.NavigationItem


@Composable
fun NavigationController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {

        composable(NavigationItem.Home.route) {
//            Home()
        }

        composable(NavigationItem.Notifications.route) {
//            Notifications()
        }

        composable(NavigationItem.Settings.route) {
//            Settings()
        }

        composable(NavigationItem.Account.route) {
            Profile(navController)
        }
        composable(NavigationItem.EditAccount.route) {
            EditProfile()
        }
        composable(NavigationItem.Signin.route) {
            SigInScreen(navController)
        }
        composable(NavigationItem.SignUp.route) {
            view()
        }

    }


}
@Composable
fun Navigation() {

    val navController = rememberNavController()

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Settings,
        NavigationItem.Notifications,
        NavigationItem.Account
    )


    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.background) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route


                items.forEach {
                    BottomNavigationItem(selected = currentRoute == it.route,
                        label = {
                            Text(
                                text = it.label,
                                color = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = it.icons, contentDescription = null,
                                tint = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )

                        },

                        onClick = {
                            if(currentRoute!=it.route){

                                navController.graph?.startDestinationRoute?.let {
                                    navController.popBackStack(it,true)
                                }

                                navController.navigate(it.route){
                                    launchSingleTop = true
                                }

                            }

                        })

                }


            }


        }) {

        NavigationController(navController = navController)

    }

}
