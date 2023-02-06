package com.compose.composetext

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.compose.composetext.ui.theme.ComposeTestTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val effectViewModel: EffectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
//            MainScreen()
//            EffectHandlers(effectViewModel = effectViewModel)
//            MyComposable()
//            Navigation()
//            ShowBottomNavigation()
//            DestinationsNavHost(navGraph = NavGraphs.root)
//            SupportDifferentScreenSizes()
              MotionLayout()
            }
        }
    }
}