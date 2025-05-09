package com.deto.notes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deto.notes.data.Note
import com.deto.notes.ui.components.CustomTopAppBar
import com.deto.notes.ui.components.NoteList
import com.deto.notes.ui.components.SearchContent


val notesList = listOf(
    Note(1, "Nota 1", "Contenido de la nota 1"),
    Note(2, "Nota 2", "Contenido de la nota 2"),
    Note(3, "Nota 3", "Contenido de la nota 3"),
    Note(4, "Nota 4", "Contenido de la nota 4"),
    Note(5, "Nota 5", "Contenido de la nota 5"),
    Note(6, "Nota 6", "Contenido de la nota 6"),
    Note(7, "Nota 7", "Contenido de la nota 7"),
    Note(8, "Nota 8", "Contenido de la nota 8"),
    Note(9, "Nota 9", "Contenido de la nota 9"),
    Note(10, "Nota 10", "Contenido de la nota 10")
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(Navigation: NavController){

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            CustomTopAppBar(scrollBehavior)
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            SearchContent(scrollState)
            NoteList(PaddingValues(0.dp), notesList)
        }
    }

}