package com.deto.notes.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.deto.notes.NotePage
import com.deto.notes.data.Note
import com.deto.notes.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteList(navController: NavController, innerPadding: PaddingValues, notes: List<Note>, notesFilter: String, selectedNotes: List<Int>, modeSelection: Boolean, onToggleSelection: (Int) -> Unit, onLongPress: (Int) -> Unit) {


    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .combinedClickable(
                            onClick = {
                                if (modeSelection) {
                                    onToggleSelection(note.id)
                                } else {
                                    navController.navigate(NotePage(note.id))
                                }
                            },
                            onLongClick = {
                                onLongPress(note.id)
                            }
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 20.dp, vertical = 20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        val titlePreview = if (note.title.length > 13) {
                            note.title.take(13) + "..."
                        } else {
                            note.title
                        }

                        val contentPreview = if (note.content.length > 60) {
                            note.content.take(60) + "..."
                        } else {
                            note.content
                        }

                        Text(
                            text = highlightMatch(titlePreview, notesFilter, Color.Yellow),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = highlightMatch(contentPreview, notesFilter, Color.Yellow),
                            color = Color.Gray,
                        )
                        Text(
                            text = note.date,
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                        if (modeSelection) {
                            if (selectedNotes.contains(note.id)) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = Color(0xFFFFC107),
                                    modifier = Modifier.align(Alignment.End)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(R.drawable.radio_button_unchecked_24dp_1f1f1f_fill0_wght400_grad0_opsz24),
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.align(Alignment.End)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun highlightMatch(text: String, notesFilter: String, highlightColor: Color): AnnotatedString {

    // AnnotatedString que permite aplicar estilos a partes específicas del texto
    return buildAnnotatedString {

        // Si el notesFilter está vacío, se retorna el texto completo sin resaltar
        if (notesFilter.isEmpty()) {
            append(text)
            return@buildAnnotatedString
        }

        // Busca la posición de la primera aparición del query en el texto (ignorando mayúsculas/minúsculas)
        val index = text.indexOf(notesFilter, ignoreCase = true)

        // Si no hay coincidencia, agrega el texto completo sin ningún estilo especial
        if (index == -1) {
            append(text)
        } else {

            //Agrega la parte del texto que va antes de la coincidencia(notesFilter) y sin resaltar
            append(text.substring(0, index))

            //Agrega la parte coincidente con el notesFilter y se cambia de color
            withStyle(style = SpanStyle(color = highlightColor)) {
                append(text.substring(index, index + notesFilter.length))
            }

            //Agrega el resto del texto después de la coincidencia(notesFilter) y sin resaltar
            append(text.substring(index + notesFilter.length))
        }
    }
}



