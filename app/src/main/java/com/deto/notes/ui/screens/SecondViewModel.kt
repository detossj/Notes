package com.deto.tasks.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deto.notes.data.Task
import com.deto.notes.data.TaskRepository
import kotlinx.coroutines.launch
import kotlin.Int


class SecondViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    val taskList = taskRepository.getAllTasksStream()

    var newTaskUiState by mutableStateOf(NewTaskUIState())
        private set

    fun updateUiState(newTask: NewTask){
        newTaskUiState =
            NewTaskUIState(newTask = newTask, isEntryValid = validateInput(newTask))
    }

    suspend fun saveItem() {
        if(validateInput()){
            val task = newTaskUiState.newTask.toTask()
            if (task.id != 0) {
                taskRepository.updateTask(task)
            } else {
                taskRepository.insertTask(task)
            }
        }
    }

    fun clearTask() {
        newTaskUiState = NewTaskUIState()
    }

    private fun validateInput(uiState: NewTask = newTaskUiState.newTask) : Boolean {
        return with(uiState) {
            title.isNotBlank()
        }
    }

    fun toggleTaskCompletion(task: Task){
        val updatedTask = task.copy(completed = !task.completed)

        viewModelScope.launch {
            taskRepository.updateTask(updatedTask)
        }
    }

}
data class NewTaskUIState(
    val newTask: NewTask = NewTask(),
    val isEntryValid: Boolean = false
)

data class NewTask(
    val id: Int = 0,
    val title: String = "",
    val completed: Boolean = false
)

fun NewTask.toTask(): Task = Task(
    id = id,
    title = title,
    completed = completed
)

fun Task.toItemUiState(isEntryValid: Boolean = false): NewTaskUIState = NewTaskUIState(
    newTask = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Task.toItemDetails(): NewTask = NewTask(
    id = id,
    title = title,
    completed = completed
)
