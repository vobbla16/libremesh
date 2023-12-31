package com.vobbla16.mesh

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vobbla16.mesh.ui.Screens
import com.vobbla16.mesh.ui.screens.homeworkScreen.HomeworkScreen
import com.vobbla16.mesh.ui.screens.loginScreen.LoginScreen
import com.vobbla16.mesh.ui.screens.marksScreen.MarksScreen
import com.vobbla16.mesh.ui.screens.profileScreen.ProfileScreen
import com.vobbla16.mesh.ui.screens.scheduleScreen.ScheduleScreen
import com.vobbla16.mesh.ui.theme.MeshTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {
            MeshTheme {
                val navController = rememberNavController()

                val vm: MainActivityViewModel = koinViewModel()

                MainScaffold(navController, vm) {
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Schedule.route
                    ) {
                        composable(Screens.Schedule.route) {
                            ScheduleScreen(navController, vm)
                        }
                        composable(Screens.Marks.route) {
                            MarksScreen(navController, vm)
                        }
                        composable(Screens.Login.route) {
                            LoginScreen(navController, vm)
                        }
                        composable(Screens.Profile.route) {
                            ProfileScreen(navController, vm)
                        }
                        composable(Screens.Homework.route) {
                            HomeworkScreen(navController, vm)
                        }
                    }
                }
            }
        }
    }
}
