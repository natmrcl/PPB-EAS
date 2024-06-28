package com.example.cgv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != "login" && currentRoute != "splash" && currentRoute != "hero" && currentRoute != "signup") {
                BottomNavigationBar(navController = navController)
            }
        },
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "splash",
                modifier = Modifier.padding(paddingValues) // Apply padding here
            ) {
                composable("splash") { SplashScreen(navController) }
                composable("hero") { HeroScreen(navController) }
                composable("login") { LoginScreen(navController) }
                composable("signup") { SignUp(navController) }
                composable("home") { KatalogScreen(navController) }
                composable("topup") { TopUpScreen(navController) }
                composable("profile") { ProfileScreen(navController) }

            }
        }
    )
}
