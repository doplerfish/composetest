package com.compose.composetext.utils

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Utils {

    fun Float.toDp(): Dp {
        return (this / Resources.getSystem().displayMetrics.density).dp
    }
}