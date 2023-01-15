package com.test.channel9.presentation.uicomponents.visibility

import android.content.res.Resources.getSystem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun Visibility(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    val contentSize = remember { mutableStateOf(IntSize.Zero) }

    Box(modifier = Modifier
        .onSizeChanged { size -> contentSize.value = size }) {
        if (visible || contentSize.value == IntSize.Zero) {
            content()
        } else {
            Spacer(modifier = Modifier.size(contentSize.value.width.pxToDp(), contentSize.value.height.pxToDp()))
        }
    }
}


fun Int.pxToDp(): Dp {
    return (this / getSystem().displayMetrics.density).dp
}