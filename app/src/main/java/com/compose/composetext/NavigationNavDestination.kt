package com.compose.composetext

import android.os.Parcelable
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.composetext.destinations.FirstScreenDestination
import com.compose.composetext.destinations.SecondScreenDestination
import com.compose.composetext.destinations.ThirdScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val id: String, val name: String, val isAdmin: Boolean) : Parcelable


@Destination(start = true)
@Composable
fun FirstScreen(navigator: DestinationsNavigator) {

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.kermit),
                contentDescription = "Logo",
            )
            Button(onClick = {
                navigator.navigate(
                    SecondScreenDestination(
                        User("someId", "Samson", true)
                    )
                )
            }) {
                Text(text = "Click to Navigate! ")
            }
        }
    }

}

@Destination
@Composable
fun SecondScreen(navigator: DestinationsNavigator, user: User) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column() {
            Button(
                onClick = {
                    navigator.navigate(ThirdScreenDestination(user))
                }, modifier = Modifier
            ) {
                Text(text = "Hi ${user.name}, click to go to next screen")
            }

            Button(
                onClick = {
                    navigator.navigate(FirstScreenDestination())
                }, modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                )
            ) {
                Text(text = "Hi ${user.name}, click to go back")
            }
        }
    }
}

@Destination
@Composable
fun ThirdScreen(navigator: DestinationsNavigator, user: User) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    Button(
        onClick = {
            navigator.navigate(SecondScreenDestination(user))
        }, modifier = Modifier
    ) {
        Text(text = "${user.name}, you are done! Click to go back.")
    }
    }
}

