package com.deto.notes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.deto.notes.NotePage
import com.deto.notes.data.Note
import com.deto.notes.ui.AppViewModelProvider
import com.deto.notes.ui.components.CustomFloatingActionButton
import com.deto.notes.ui.components.CustomTopAppBar
import com.deto.notes.ui.components.NoteList
import com.deto.notes.ui.components.SearchContent
import androidx.compose.runtime.getValue
import com.deto.notes.ui.components.CustomBottomAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(Navigation: NavController, viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)){

    val noteList by viewModel.noteList.collectAsState(initial = emptyList())
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            CustomTopAppBar(scrollBehavior,"Notas")
        },
        bottomBar = {
            CustomBottomAppBar(Navigation)
        },
        floatingActionButton = {
            CustomFloatingActionButton(Navigation)
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            SearchContent(scrollState,Navigation, PaddingValues(0.dp), noteList)

        }
    }

}