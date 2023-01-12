package com.test.channel9.presentation.uicomponents.alertdialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.test.channel9.R

@Composable
fun AlertDialogPopUp(isShowingDialog: Boolean, callback:() -> Unit) {
    AnimatedVisibility(visible = isShowingDialog) {
        AlertDialog(
            title = {
                Text(
                    text = stringResource(id = R.string.error_alert_dialog_title),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.error_alert_dialog_default_message),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            onDismissRequest = { },
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.55f),
            confirmButton = {},
            dismissButton = {
                OutlinedButton(onClick = { callback.invoke() }) {
                    Text(
                        text = stringResource(id = R.string.error_alert_dialog_dismiss_button_label),
                        style = TextStyle(
                            fontSize = 18.sp
                        ),
                        color = Color.Black
                    )
                }
            }
        )
    }
}