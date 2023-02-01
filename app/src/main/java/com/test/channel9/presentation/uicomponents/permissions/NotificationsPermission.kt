package com.test.channel9.presentation.uicomponents.permissions

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NotificationsPermission() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val permissionCallback = MutableStateFlow(false)
    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            mutableStateOf(ContextCompat.checkSelfPermission(context,Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
            )
        }else {
            mutableStateOf(true)
        }
    }
    if (!hasNotificationPermission) {
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                hasNotificationPermission = isGranted
                permissionCallback.value = isGranted
            }
        )
        coroutineScope.launch {
            permissionCallback.collect{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

}