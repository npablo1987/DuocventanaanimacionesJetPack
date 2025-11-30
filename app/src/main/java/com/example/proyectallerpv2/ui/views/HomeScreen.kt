package com.example.proyectallerpv2.ui.views   // Paquete ui.views

// Imports de layout y componentes de Material3
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*   // TopAppBar, Button, etc.
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCambiarPantalla: (Pantalla) -> Unit    // Función para navegar a otras pantallas
) {
    // Estado que controla si el menú desplegable está abierto o cerrado
    var menuAbierto by remember { mutableStateOf(false) }

    // Scaffold: estructura básica de la pantalla (barra superior + contenido)
    Scaffold(
        topBar = {
            // Barra superior de la pantalla
            TopAppBar(
                title = { Text("Registro de Pacientes") },   // Título en la barra
                actions = {
                    // Botón de tres puntitos para abrir el menú
                    IconButton(onClick = { menuAbierto = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert, // Icono vertical de menú
                            contentDescription = "Menú"
                        )
                    }

                    // Menú desplegable que aparece desde la barra
                    DropdownMenu(
                        expanded = menuAbierto,                // ¿Está abierto?
                        onDismissRequest = { menuAbierto = false } // Lo cerramos al hacer click fuera
                    ) {
                        // Opción del menú: ir al registro de pacientes
                        DropdownMenuItem(
                            text = { Text("Registrar paciente") },
                            onClick = {
                                menuAbierto = false                               // Cerrar menú
                                onCambiarPantalla(Pantalla.RegistroPaciente)      // Navegar
                            }
                        )

                        // Opción del menú: ir al listado de pacientes
                        DropdownMenuItem(
                            text = { Text("Ver listado de pacientes") },
                            onClick = {
                                menuAbierto = false                               // Cerrar menú
                                onCambiarPantalla(Pantalla.ListadoPacientes)      // Navegar
                            }
                        )
                    }
                }
            )//dfin rio var
        }
    ) { paddingValues ->
        // Contenido principal de la pantalla Home
        Column(
                modifier = Modifier
                    .padding(paddingValues)  // Padding que deja espacio para la barra
                    .fillMaxSize()
                    .padding(16.dp),         // Margen interno adicional
                verticalArrangement = Arrangement.Center,      // Centrado vertical
                horizontalAlignment = Alignment.CenterHorizontally // Centrado horizontal
            ) {
                // Texto de bienvenida
                Text(
                    text = "Bienvenido al sistema de pacientes",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(16.dp))   // Espacio

                // Botón para ir directo a registrar un paciente
                Button(
                    onClick = { onCambiarPantalla(Pantalla.RegistroPaciente) }
                ) {
                    Text("Registrar nuevo paciente")
                }

                Spacer(modifier = Modifier.height(8.dp))    // Espacio

                // Botón contorneado para ir al listado
                OutlinedButton(
                    onClick = { onCambiarPantalla(Pantalla.ListadoPacientes) }
                ) {
                    Text("Ver listado de pacientes")
                }
        }
    }
}
