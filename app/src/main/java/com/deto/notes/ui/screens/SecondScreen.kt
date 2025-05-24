package com.deto.notes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deto.notes.data.Task
import com.deto.notes.ui.components.CustomBottomAppBar
import com.deto.notes.ui.components.CustomFloatingActionButtonSecond
import com.deto.notes.ui.components.CustomModalBottomSheet
import com.deto.notes.ui.components.CustomTopAppBar
import com.deto.notes.ui.components.TaskList
import kotlinx.coroutines.launch

var taskList = mutableListOf<Task>(
    Task(1,"Sacar la ropa",true),
    Task(2,"Ir a comprar pan",true),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(Navigation: NavController) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    var showBottomSheet by remember { mutableStateOf(false) }

    CustomModalBottomSheet(showBottomSheet, scope, bottomSheetState, { showBottomSheet = false })

    Scaffold(
        topBar = {
            CustomTopAppBar(scrollBehavior, "Tareas")
        },
        bottomBar = {
            CustomBottomAppBar(Navigation)
        },
        floatingActionButton = {
            CustomFloatingActionButtonSecond(
                onClick = {
                    scope.launch {
                        showBottomSheet = true
                        bottomSheetState.show()
                    }
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TaskList(PaddingValues(0.dp), taskList)
        }
    }
}