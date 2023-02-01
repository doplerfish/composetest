package com.compose.composetext

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.delay

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
//            route = Screen.DetailScreen.route + "?name={name}", // for optional "name" argument
            route = Screen.DetailScreen.route + "/{name}", arguments = listOf(navArgument("name") {
                type = NavType.StringType
                defaultValue = "Amir"
                nullable = true
            })
        ) { entry ->
            DetailScreen(name = entry.arguments?.getString("name"))
        }
    }

}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.8f, animationSpec = tween(durationMillis = 500, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))
        delay(3000L)
        navController.navigate(Screen.HomeScreen.route)
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.kermit),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}

@Composable
fun HomeScreen(navController: NavController) {

    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        TextField(
            value = text, onValueChange = { text = it }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.DetailScreen.withArgs(text))
            }, modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "To DetailScreen")
        }
    }
}

@Composable
fun DetailScreen(name: String?) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello $name")
    }

}