package com.example.proyectallerpv2.ui.views

// Importamos anotación @Composable
import androidx.compose.runtime.Composable

// Importamos las pantallas que vamos a usar
import com.example.proyectallerpv2.ui.views.HomeScreen
import com.example.proyectallerpv2.ui.views.ListadoPacientesScreen
import com.example.proyectallerpv2.ui.views.RegistroPacienteScreen

import androidx.compose.animation.AnimatedContent       // Contenedor animado entre estados
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn               // Efecto de entrada
import androidx.compose.animation.fadeOut              // Efecto de salida
import androidx.compose.animation.core.tween           // Para configurar duración
import androidx.compose.animation.togetherWith         // Combinar fadeIn y fadeOut


// Composable raíz de la aplicación de pacientes
@Composable
fun PacientesApp(
    pantallaActual: Pantalla,                 // Pantalla que se debe mostrar
    onCambiarPantalla: (Pantalla) -> Unit     // Función para cambiar de pantalla
) {
    // AnimatedContent se encarga de animar el cambio entre valores de pantallaActual
    AnimatedContent(
        targetState = pantallaActual,          // Estado que queremos animar (la pantalla)
        label = "AnimacionCambioPantalla",
        transitionSpec = {
            // Definimos cómo será la animación de entrada y salida
            // tween(600) = duración de 600 ms
            fadeIn(animationSpec = tween(2000)) togetherWith
                    fadeOut(animationSpec = tween(2000))
        }
    ) { pantalla ->
        when (pantalla) {
            // Si estamos en Home, dibujamos HomeScreen
            Pantalla.Home -> HomeScreen(
                onCambiarPantalla = onCambiarPantalla   // Le pasamos la función de navegación
            )

            // Si estamos en RegistroPaciente, dibujamos la pantalla de registro
            Pantalla.RegistroPaciente -> RegistroPacienteScreen(
                onVolver = { onCambiarPantalla(Pantalla.Home) }   // Al terminar, volver a Home
            )

            // Si estamos en ListadoPacientes, dibujamos la lista
            Pantalla.ListadoPacientes -> ListadoPacientesScreen(
                onVolver = { onCambiarPantalla(Pantalla.Home) }   // También permite volver a Home
            )
        }
    }
}
