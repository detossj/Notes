package com.deto.notes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deto.notes.ui.components.CustomBottomAppBar
import com.deto.notes.ui.components.CustomFloatingActionButton
import com.deto.notes.ui.components.CustomTopAppBar
import com.deto.notes.ui.components.TaskList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(Navigation: NavController) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            CustomTopAppBar(scrollBehavior,"Tareas")
        },
        bottomBar = {
            CustomBottomAppBar(Navigation)
        },
        floatingActionButton = {
            CustomFloatingActionButton(Navigation)
        },
        modifier = Modifier.nestedScroll((scrollBehavior.nestedScrollConnection))
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            TaskList(PaddingValues(0.dp))
        }

    }
}