package com.test.channel9.presentation.extensions

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions


fun NavBackStackEntry.requiredStringArg(key: String): String {
    return requireNotNull(arguments) { return "" }.run {
        requireNotNull(getString(key)) { "argument for $key is null" }
    }
}

fun NavController.navigate(route: String, vararg args: Pair<String, String>, builder: NavOptionsBuilder.() -> Unit) {
    navigate(route, navOptions(builder))
    requireNotNull(currentBackStackEntry?.arguments).apply {
        args.forEach { (key: String, arg: String) ->
            putString(key, arg)
        }
    }
}