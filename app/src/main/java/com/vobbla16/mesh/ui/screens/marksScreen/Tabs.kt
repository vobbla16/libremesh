package com.vobbla16.mesh.ui.screens.marksScreen

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.vobbla16.mesh.ui.screens.marksScreen.subscreens.ShowByDate
import com.vobbla16.mesh.ui.screens.marksScreen.subscreens.ShowBySubject
import com.vobbla16.mesh.R
import androidx.compose.ui.res.painterResource

enum class Tabs(
    val title: String,
    val icon: @Composable () -> Unit,
    val subscreen: @Composable (MarksScreenViewModel) -> Unit
) {
    @SuppressLint("NewApi")
    ShowByDate("By date", {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "calendar icon"
        )
    }, { ShowByDate(it) }
    ),

    @SuppressLint("NewApi")
    ShowBySubject("By subject", {
        Icon(
            painter = painterResource(id = R.drawable.subject_24dp),
            contentDescription = "subject icon"
        )
    }, { ShowBySubject(it) })
}