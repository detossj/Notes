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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deto.notes.data.Task
import com.deto.notes.ui.AppViewModelProvider
import com.deto.tasks.ui.screens.SecondViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalBottomSheet(showBottomSheet: Boolean, scope: CoroutineScope, bottomSheetState: SheetState, onDismiss: () -> Unit, viewModel: SecondViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    var error by remember { mutableStateOf(false) }

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
                CustomOutlinedTextField(
                    value = viewModel.newTaskUiState.newTask.title,
                    onValueChange = {
                        viewModel.updateUiState(viewModel.newTaskUiState.newTask.copy(title = it))
                        error = it.isBlank() },
                    "Nueva Tarea",
                    16
                )

                Button(
                    onClick = {

                        error = viewModel.newTaskUiState.newTask.title.isBlank()
                        if (!error) {
                            scope.launch {
                                viewModel.saveItem()
                            }
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
