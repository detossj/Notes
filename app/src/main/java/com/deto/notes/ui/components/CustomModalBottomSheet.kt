package com.deto.notes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deto.notes.data.Task
import com.deto.notes.ui.screens.taskList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalBottomSheet(showBottomSheet: Boolean, scope: CoroutineScope, bottomSheetState: SheetState, onDismiss: () -> Unit) {
    var newTask by remember { mutableStateOf("") }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    bottomSheetState.hide()
                    onDismiss()
                }
            },
            sheetState = bottomSheetState,
            dragHandle = null
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                CustomOutlinedTextField(newTask, { newTask = it }, "Nueva Tarea", 16)

                Button(
                    onClick = {
                        if (newTask.isNotBlank()) {
                            taskList.add(Task(taskList.size + 1, newTask, false))
                            newTask = ""
                        }
                        scope.launch {
                            bottomSheetState.hide()
                            onDismiss()
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Guardar")
                }
            }
        }
    }
}
