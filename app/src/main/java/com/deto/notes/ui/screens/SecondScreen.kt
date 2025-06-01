package com.deto.notes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.deto.notes.data.Task
import com.deto.notes.ui.AppViewModelProvider
import com.deto.notes.ui.components.CustomBottomAppBar
import com.deto.notes.ui.components.CustomFloatingActionButtonSecond
import com.deto.notes.ui.components.CustomModalBottomSheet
import com.deto.notes.ui.components.CustomTopAppBar
import com.deto.notes.ui.components.SearchTask
import com.deto.notes.ui.components.TaskList
import com.deto.tasks.ui.screens.SecondViewModel
import com.deto.tasks.ui.screens.toItemDetails
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(Navigation: NavController, viewModel: SecondViewModel = viewModel(factory = AppViewModelProvider.Factory) ) {

    val taskList by viewModel.taskList.collectAsState(initial = emptyList())
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    var showBottomSheet by remember { mutableStateOf(false) }

    CustomModalBottomSheet(
        viewModel.newTaskUiState.newTask.title,
        { viewModel.updateUiState(viewModel.newTaskUiState.newTask.copy(title = it)) },
        "Nueva Tarea",
        showBottomSheet,
        scope,
        bottomSheetState,
        {
            showBottomSheet = false
            viewModel.clearTask()
        }
    )

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
                        viewModel.clearTask()
                        showBottomSheet = true
                        bottomSheetState.show()
                    }
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SearchTask(
                scrollState,
                Navigation,
                PaddingValues(0.dp),
                taskList,
                onTaskClick = { task ->
                    scope.launch {
                        viewModel.updateUiState(task.toItemDetails())
                        showBottomSheet = true
                        bottomSheetState.show()
                    }
                },
                { task -> viewModel.toggleTaskCompletion(task)}
            )
        }
    }
}