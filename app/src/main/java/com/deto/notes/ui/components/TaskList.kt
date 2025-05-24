package com.deto.notes.ui.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deto.notes.data.Task



@Composable
fun TaskList(innerPadding: PaddingValues, taskList: List<Task>) {


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
            items(taskList) {

                var isChecked by remember { mutableStateOf(it.completed) }
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = it.completed,
                            onCheckedChange = {
                                isChecked = it
                            }
                        )

                        Text(
                            text = it.title,
                            modifier = Modifier.padding(start = 8.dp),
                            fontWeight = FontWeight.Bold
                        )

                    }
                }
            }
        }
    }
}