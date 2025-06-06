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
import com.deto.notes.ui.AppViewModelProvider
import com.deto.notes.ui.components.CustomTopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.deto.notes.ui.components.CustomAlertDialog
import com.deto.notes.ui.components.CustomBottomAppBar
import com.deto.notes.ui.components.CustomBottomAppBarDelete
import com.deto.notes.ui.components.CustomFloatingActionButtonHome
import com.deto.notes.ui.components.SearchNote


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(Navigation: NavController, viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)){

    val noteList by viewModel.noteList.collectAsState(initial = emptyList())
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollState = rememberLazyListState()
    val (modeSelection, setModeSelection) = remember { mutableStateOf(false) }
    val (selectedNotes,setSelectedNotes) = remember { mutableStateOf<List<Int>>(emptyList()) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(scrollBehavior,"Notas")
        },
        bottomBar = {
            if(!modeSelection) {
                CustomBottomAppBar(Navigation)
            }
            else
            {
                CustomBottomAppBarDelete(Navigation,{
                    showDialog = true
                })
            }

        },
        floatingActionButton = {
            CustomFloatingActionButtonHome(Navigation)
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            SearchNote(
                scrollState,
                Navigation,
                PaddingValues(0.dp),
                noteList,
                modeSelection,
                setModeSelection,
                selectedNotes,
                setSelectedNotes
            )

        }

        CustomAlertDialog(showDialog,
            { showDialog = false },
            {
                viewModel.deleteNoteById(selectedNotes)
                setSelectedNotes(emptyList())
                setModeSelection(false)
            },
            selectedNotes.size,
            "Eliminar notas",
        )
    }

}