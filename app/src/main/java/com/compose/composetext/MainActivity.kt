package com.compose.composetext

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import com.compose.composetext.ui.theme.BottomNavigation
import com.compose.composetext.ui.theme.ShowBottomNavigation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : ComponentActivity() {

    private val effectViewModel: EffectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
              MainScreen()
//            EffectHandlers(effectViewModel = effectViewModel)
//            MyComposable()
//            Navigation()
//            ShowBottomNavigation()
        }
    }
}