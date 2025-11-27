package com.example.proyectallerpv2.ui.views

// Importamos anotación @Composable
import androidx.compose.runtime.Composable

// Importamos las pantallas que vamos a usar
import com.example.proyectallerpv2.ui.views.HomeScreen
import com.example.proyectallerpv2.ui.views.ListadoPacientesScreen
import com.example.proyectallerpv2.ui.views.RegistroPacienteScreen

// Composable raíz de la aplicación de pacientes
@Composable
fun PacientesApp(
    pantallaActual: Pantalla,                 // Pantalla que se debe mostrar
    onCambiarPantalla: (Pantalla) -> Unit     // Función para cambiar de pantalla
) {
    // Dependiendo del valor de pantallaActual mostramos una u otra pantalla
    when (pantallaActual) {
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
