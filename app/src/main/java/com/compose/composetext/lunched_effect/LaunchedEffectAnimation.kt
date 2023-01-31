package com.compose.composetext.lunched_effect

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun LaunchedEffectAnimation(counter: Int) {

    val animateble = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = counter){
        animateble.animateTo(counter.toFloat())
    }
}