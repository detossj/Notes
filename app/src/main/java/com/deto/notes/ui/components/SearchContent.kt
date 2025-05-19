package com.deto.notes.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deto.notes.data.Note

@Composable
fun SearchContent( scrollState: LazyListState, navController: NavController, innerPadding: PaddingValues, notes: List<Note>) {

    var notesFilter by remember { mutableStateOf("") }

    LazyColumn(
        state = scrollState,
    ) {
        item {
            OutlinedTextField(
                value = notesFilter,
                onValueChange = {notesFilter = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text("Buscar notas") },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Buscar notas"
                    )
                }
            )
        }


    }

    var notesListFilter = notes.filter {
        it.title.contains(notesFilter, ignoreCase = true) || it.content.contains(notesFilter, ignoreCase = true)
    }

    NoteList(navController, innerPadding, notesListFilter,notesFilter)
}
