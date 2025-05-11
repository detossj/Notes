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
    Note(1, "Lista de compras", "Leche entera, pan integral recién horneado, huevos orgánicos, queso brie, café colombiano molido, tomates cherry, y si hay oferta, unas frutillas para el postre."),
    Note(2, "Tarea pendiente", "Terminar el informe mensual antes del viernes, aunque ya sabemos que lo voy a dejar para último momento como siempre. ¡Que no se diga que no avisé!"),
    Note(3, "Idea de negocio", "Una tienda online donde todo sea ecológico, desde el empaque hasta la atención al cliente. Productos sostenibles, sin plástico, y con entrega en bici. ¿Quién se prende?"),
    Note(4, "Receta", "Pasta al pesto casera: hojas frescas de albahaca, un par de dientes de ajo, piñones dorados, parmesano rallado del bueno y aceite de oliva extra virgen. ¡A revolver con ganas!"),
    Note(5, "Recordatorio médico", "Cita con el dentista el 15 de mayo a las 10:00 AM. Y esta vez sí acordate, porque la última vez terminaste cancelando por una siesta no planificada."),
    Note(6, "Libro por leer", "Empieza con 'Sapiens' de Yuval Noah Harari. Aparentemente te va a cambiar la forma de ver la humanidad. O al menos eso dicen en Goodreads."),
    Note(7, "Cita inspiradora", "“La vida es aquello que te pasa mientras estás ocupado haciendo otros planes.” – John Lennon. Ideal para poner en un fondo de pantalla con una montaña de fondo."),
    Note(8, "Plan de entrenamiento", "Lunes, miércoles y viernes: correr 30 minutos (aunque el lunes siempre arranca el martes). Martes y jueves: algo de yoga o estiramientos, sin excusas."),
    Note(9, "Cumpleaños", "No olvidarse del cumpleaños de mamá el 21 de junio. Este año va con desayuno sorpresa, flores, y quizás una escapada al campo si el clima acompaña."),
    Note(10, "Películas para ver", "Maratón pendiente: La La Land (otra vez), Interstellar con snacks intergalácticos, y El secreto de sus ojos porque siempre emociona."),
    Note(11, "Aprender", "Investigar sobre inteligencia artificial, machine learning y por qué todo el mundo ahora quiere ser prompt engineer. Empieza con un curso en Coursera o algo piola."),
    Note(12, "Proyecto personal", "Diseñar el sitio web personal en WordPress este mes, sin distracciones. Blog, portfolio, y un formulario de contacto que no termine en spam."),
    Note(13, "Reunión", "Reunión clave con el equipo de diseño el martes a las 3 PM. Llevá ideas, no mates la creatividad. Y por favor, llegá a horario esta vez."),
    Note(14, "Vacaciones soñadas", "Viajar a Japón durante la temporada de sakura. Recorrer Kyoto, perderse en las callecitas de Tokio y comer ramen en cada esquina. Sueño total."),
    Note(15, "Lista de deseos", "Una bicicleta nueva (de esas con canasto adelante), una cámara réflex para capturar momentos épicos, y una suscripción a un curso online que finalmente termines."),
    Note(16, "Frase motivadora", "No dejes para mañana lo que podés hacer hoy. Aunque si lo dejás para mañana, que al menos sea con una buena excusa."),
    Note(17, "Revisión del coche", "Pedir cita en el taller para la revisión anual. Que después no venga el susto cuando se prenda una lucecita roja en la autopista."),
    Note(18, "Contraseñas", "Cambiar las contraseñas de todas las redes sociales por seguridad. Usar un gestor de contraseñas de verdad esta vez, no post-its pegados en el monitor."),
    Note(19, "Plan de ahorro", "Ahorrar $100 al mes para el fondo de emergencia. Y no tocarlo para delivery ni compras impulsivas. (Sí, eso incluye otra planta para el balcón)."),
    Note(20, "Favoritos", "Café favorito: Flat white con leche de almendras. Restaurante de confianza: La Trattoria, donde el mozo ya te conoce y sabe tu pedido de memoria.")
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