package com.compose.composetext

sealed class Screen(val route: String){
    object SplashScreen: Screen("splash_screen")
    object HomeScreen: Screen("home_screen")
    object DetailScreen: Screen("detail_screen")
    object ChatsScreen: Screen("chats_screen")
    object SettingsScreen: Screen("settings_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
