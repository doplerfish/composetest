package com.compose.composetext

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.composetext.utils.WindowInfo
import com.compose.composetext.utils.rememberWindowInfo

@Composable
fun SupportDifferentScreenSizes() {

    val windowInfo = rememberWindowInfo()

    if (windowInfo.screenHeightInfo is WindowInfo.WindowType.Compact)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(10) { Item(index = it, color = Color.Cyan) }
            items(10) { Item(index = it, color = Color.Green) }
        }
    else
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(10) { Item(index = it, color = Color.Cyan) }
            }
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(10) { Item(index = it, color = Color.Green) }
            }
        }
}

@Composable
fun Item(index: Int, color: Color) {
    Text(
        text = "Item $index",
        fontSize = 25.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(16.dp)
    )
}