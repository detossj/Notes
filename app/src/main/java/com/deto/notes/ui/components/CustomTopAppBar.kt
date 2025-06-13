package com.deto.notes.ui.components


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.deto.notes.ui.theme.onPrimaryLight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(scrollBehavior : TopAppBarScrollBehavior, title: String) {

    LargeTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 32.sp,
                color = onPrimaryLight
            )},
        scrollBehavior = scrollBehavior
    )



}