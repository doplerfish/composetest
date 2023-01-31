package com.compose.composetext

import android.util.Log
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import kotlinx.coroutines.flow.collect

private const val TAG = "EffectHandlers"

@Composable
fun EffectHandlers(effectViewModel: EffectViewModel) {
    LaunchedEffectFlowDemo(effectViewModel)
}

@Composable
fun LaunchedEffectFlowDemo(viewModel: EffectViewModel) {

    val scaffoldState = rememberScaffoldState()

//    val state = viewModel.sharedView.observeAsState()
//    LaunchedEffect(state){
//        when(state.value){
//            is EffectViewModel.ScreenEvents.ShowSnackbar -> {
//                scaffoldState.snackbarHostState.showSnackbar("state.message")
//            }
//            is EffectViewModel.ScreenEvents.Navigate -> {
//
//            }
//        }
//    }
}