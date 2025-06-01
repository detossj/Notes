package com.deto.notes.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.deto.notes.R
import com.deto.notes.ui.AppViewModelProvider
import com.deto.notes.ui.components.CustomOutlinedTextField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(Navigation: NavController, noteId: Int? = null, viewModel: NewNoteViewModel = viewModel(factory = AppViewModelProvider.Factory)){

    val homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)

    val noteList by homeViewModel.noteList.collectAsState(initial = emptyList())

    val scope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    val note = noteList.find { it.id == noteId }


    LaunchedEffect(note) {
        note?.let {
            viewModel.updateUiState(it.toItemDetails())
        }
    }

    //  Manejo del botón "Atras" del sistema
    BackHandler {
        scope.launch {
            viewModel.saveItem()
            Navigation.popBackStack()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Button(
                        onClick = {
                            scope.launch {
                                viewModel.saveItem()
                                Navigation.popBackStack()
                            }
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonColors(
                            contentColor = Color.White,
                            containerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier

                .padding(innerPadding)
                .verticalScroll(scrollState)
                .imePadding()
        ) {

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {

               if(note == null) {
                   CustomOutlinedTextField(
                       viewModel.newNoteUiState.newNote.title,
                       { viewModel.updateUiState(viewModel.newNoteUiState.newNote.copy(title = it)) },
                       stringResource(R.string.newnote_title_placeholder),
                       24
                   )

                   Text(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(16.dp),
                       text = viewModel.newNoteUiState.newNote.date,
                       fontSize = 14.sp,
                       color = Color.Gray

                   )

                   CustomOutlinedTextField(
                       viewModel.newNoteUiState.newNote.content,
                       { viewModel.updateUiState(viewModel.newNoteUiState.newNote.copy(content = it)) },
                       stringResource(R.string.newnote_content_placeholder),
                       16
                   )
               }
               else {
                   CustomOutlinedTextField(
                       viewModel.newNoteUiState.newNote.title,
                       { viewModel.updateUiState(viewModel.newNoteUiState.newNote.copy(title = it)) },note.title,
                       24
                   )

                   Text(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(16.dp),
                       text = viewModel.newNoteUiState.newNote.date,
                       fontSize = 14.sp,
                       color = Color.Gray
                   )

                   CustomOutlinedTextField(
                       viewModel.newNoteUiState.newNote.content,
                       { viewModel.updateUiState(viewModel.newNoteUiState.newNote.copy(content = it)) }
                       ,note.content,
                       16
                   )
               }

            }


        }

    }

}