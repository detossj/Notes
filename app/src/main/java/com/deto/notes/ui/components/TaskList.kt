package com.deto.notes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.deto.notes.data.Task
import com.deto.notes.ui.AppViewModelProvider
import com.deto.notes.ui.screens.toItemDetails
import com.deto.tasks.ui.screens.SecondViewModel
import com.deto.tasks.ui.screens.toItemDetails
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(navController: NavController, innerPadding: PaddingValues, taskList: List<Task>, taskFilter: String, onTaskClick: (Task)-> Unit, onTaskCheckChange: (Task) -> Unit) {




    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(taskList) { task->



                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .clickable {
                            onTaskClick(task)
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = task.completed,
                            onCheckedChange = {
                               onTaskCheckChange(task)
                            },
                            colors = if(task.completed) {
                                CheckboxDefaults.colors(
                                    checkedColor = Color.Gray,
                                    checkmarkColor = Color.DarkGray,
                                    uncheckedColor = Color.LightGray
                                )
                            } else {
                                CheckboxDefaults.colors()
                            }
                        )

                        Text(
                            text = highlightMatch(task.title, taskFilter, Color.Yellow),
                            modifier = Modifier.padding(start = 8.dp),
                            fontWeight = FontWeight.Bold,
                            color = if(task.completed) Color.Gray else Color.White
                        )

                    }
                }
            }
        }
    }
}