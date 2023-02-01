package com.compose.composetext.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.composetext.BottomNavItem
import com.compose.composetext.HomeScreen
import com.compose.composetext.Navigation
import com.compose.composetext.Screen

@Composable
fun ShowBottomNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = Screen.HomeScreen.route,
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Chats",
                        route = Screen.ChatsScreen.route,
                        icon = Icons.Default.Notifications,
                        badgeCount = 23
                    ),
                    BottomNavItem(
                        name = "Settings",
                        route = Screen.SettingsScreen.route,
                        icon = Icons.Default.Settings
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        BottomNavigation( navController = navController)
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            ThisHomeScreen()
        }
        composable(Screen.ChatsScreen.route) {
            ThisChatsScreen()
        }
        composable(Screen.SettingsScreen.route) {
            ThisSettingsScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    androidx.compose.material.BottomNavigation(
        modifier = Modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(
                                badge = { Badge { Text(text = item.badgeCount.toString()) } }
                            ) {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }

                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun ThisHomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "This Home Screen")
    }
}

@Composable
fun ThisChatsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "This Chats Screen")
    }
}

@Composable
fun ThisSettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "This Settings Screen")
    }
}