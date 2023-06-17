package com.vobbla16.mesh.ui.screens.marksScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.vobbla16.mesh.ui.MainScaffoldController
import com.vobbla16.mesh.ui.Screens
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarksScreen(navController: NavController, scaffoldController: MainScaffoldController) {
    val vm: MarksScreenViewModel = koinViewModel()
    val state = vm.viewState.value

    scaffoldController.uiState.value = scaffoldController.uiState.value.copy(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Marks screen") })
    })

    SideEffect {
        Log.d("RECOMPS", "MarksScreen recomposition occurred")
    }

    val vmActionsScope = rememberCoroutineScope()
    LaunchedEffect(key1 = vm.action) {
        vmActionsScope.launch {
            vm.action.onEach { action ->
                when (action) {
                    is MarksScreenAction.NavigateToLoginScreen -> {
                        navController.navigate(Screens.Login.route)
                    }
                }
            }.collect()
        }
    }

    Column {
        TabRow(selectedTabIndex = state.selectedTabIndex, tabs = {
            Tabs.values().forEachIndexed { index, tab ->
                Tab(
                    selected = index == state.selectedTabIndex,
                    onClick = { vm.switchTab(index) },
                    icon = tab.icon,
                    text = { Text(text = tab.title) }
                )
            }
        })

        if (state.isLoading) {
            CircularProgressIndicator(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
        }
        state.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
        }

        Tabs.values()[state.selectedTabIndex].subscreen(vm)
    }
}