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
    Note(1, "Lista de compras", "Leche, pan, huevos, queso, café, tomates."),
    Note(2, "Tarea pendiente", "Terminar el informe mensual antes del viernes."),
    Note(3, "Idea de negocio", "Tienda online de productos ecológicos y sostenibles."),
    Note(4, "Receta", "Pasta al pesto: albahaca, ajo, piñones, parmesano y aceite de oliva."),
    Note(5, "Recordatorio médico", "Cita con el dentista el 15 de mayo a las 10:00 AM."),
    Note(6, "Libro por leer", "Empieza a leer 'Sapiens' de Yuval Noah Harari."),
    Note(7, "Cita inspiradora", "“La vida es aquello que te pasa mientras estás ocupado haciendo otros planes.” – John Lennon"),
    Note(8, "Plan de entrenamiento", "Lunes, miércoles y viernes: correr 30 minutos."),
    Note(9, "Cumpleaños", "No olvidar el cumpleaños de mamá el 21 de junio."),
    Note(10, "Películas para ver", "La La Land, Interstellar, El secreto de sus ojos."),
    Note(11, "Aprender", "Investigar sobre inteligencia artificial y aprendizaje automático."),
    Note(12, "Proyecto personal", "Diseñar mi sitio web en WordPress este mes."),
    Note(13, "Reunión", "Reunión con el equipo de diseño el martes a las 3 PM."),
    Note(14, "Vacaciones soñadas", "Viajar a Japón durante la temporada de sakura."),
    Note(15, "Lista de deseos", "Bicicleta nueva, cámara réflex, suscripción a un curso online."),
    Note(16, "Frase motivadora", "No dejes para mañana lo que puedes hacer hoy."),
    Note(17, "Revisión del coche", "Pedir cita en el taller para la revisión anual."),
    Note(18, "Contraseñas", "Cambiar contraseñas de redes sociales por seguridad."),
    Note(19, "Plan de ahorro", "Ahorrar $100 al mes para el fondo de emergencia."),
    Note(20, "Favoritos", "Café favorito: Flat white. Restaurante: La Trattoria.")

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