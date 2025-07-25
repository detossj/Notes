package com.deto.notes.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deto.notes.R
import com.deto.notes.data.Task

@Composable
fun SearchTask(
    scrollState: LazyListState,
    navController: NavController,
    innerPadding: PaddingValues,
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit,
    onTaskCheckChange: (Task) -> Unit,
    modeSelection: Boolean,
    onModeSelectionChange: (Boolean) -> Unit,
    selectedTasks: List<Int>,
    setSelectedTasks: (List<Int>) -> Unit
){

    var tasksFilter by remember { mutableStateOf("") }

    BackHandler(enabled = modeSelection) {
        setSelectedTasks(emptyList())
        onModeSelectionChange(false)
    }

    LazyColumn(
        state = scrollState,
    ) {
        item {
            OutlinedTextField(
                value = tasksFilter,
                onValueChange = {tasksFilter = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Buscar notas"
                    )
                },
                placeholder = { Text(text = stringResource(R.string.search_placeholder)) },
                shape = RoundedCornerShape(30.dp)
            )
        }


    }

    fun toggleSelection(id: Int) {
        val newSelected = if (selectedTasks.contains(id)) {
            selectedTasks - id
        } else {
            selectedTasks + id
        }
        setSelectedTasks(newSelected)
        if (selectedTasks.isEmpty()) onModeSelectionChange(false)
    }

    fun initSelection(id: Int) {
        onModeSelectionChange(true)
        setSelectedTasks(listOf(id))
    }

    var taskListFilter = tasks.filter {
        it.title.contains(tasksFilter, ignoreCase = true)
    }

    TaskList(
        innerPadding,
        taskListFilter,
        tasksFilter,
        onTaskClick,
        onTaskCheckChange,
        selectedTasks,
        modeSelection,
        { toggleSelection(it) },
        { initSelection(it) }
    )
}