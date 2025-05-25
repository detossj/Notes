package com.deto.notes.ui.screens

import androidx.lifecycle.ViewModel
import com.deto.notes.data.TaskRepository

class SecondScreenViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    val taskList = taskRepository.getAllTasksStream()
}