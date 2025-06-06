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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.deto.notes.R
import com.deto.notes.data.Note
import com.deto.notes.ui.AppViewModelProvider
import com.deto.notes.ui.screens.HomeViewModel

@Composable
fun SearchNote(
    scrollState: LazyListState,
    navController: NavController,
    innerPadding: PaddingValues,
    notes: List<Note>,
    modeSelection: Boolean,
    onModeSelectionChange: (Boolean) -> Unit,
    selectedNotes: List<Int>,
    setSelectedNotes: (List<Int>) -> Unit
) {

    var notesFilter by remember { mutableStateOf("") }

    BackHandler(enabled = modeSelection) {
        setSelectedNotes(emptyList())
        onModeSelectionChange(false)
    }

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
        val newSelected = if (selectedNotes.contains(id)) {
            selectedNotes - id
        } else {
            selectedNotes + id
        }
        setSelectedNotes(newSelected)
        if (newSelected.isEmpty()) onModeSelectionChange(false)
    }

    fun initSelection(id: Int) {
        onModeSelectionChange(true)
        setSelectedNotes(listOf(id))
    }



    var notesListFilter = notes.filter {
        it.title.contains(notesFilter, ignoreCase = true) || it.content.contains(notesFilter, ignoreCase = true)
    }

    NoteList(
        navController,
        innerPadding,
        notesListFilter,
        notesFilter,
        selectedNotes,
        modeSelection,
        { toggleSelection(it) },
        { initSelection(it) }
    )
}


