package com.example.proyectallerpv2.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectallerpv2.model.Paciente
import com.example.proyectallerpv2.repository.PacienteRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroPacienteScreen(
    onVolver: () -> Unit        // Función para volver (por ejemplo a Home)
) {
    // Obtenemos la instancia del repositorio
    val repository = remember { PacienteRepository.getInstance() }
    
    // Estados para guardar los campos del paciente
    var rut by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var diagnostico by remember { mutableStateOf("") }
    
    // Estado para mensajes de error
    var mensajeError by remember { mutableStateOf("") }

    // Estructura base con barra superior
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar Paciente") },
                navigationIcon = {
                    // Flecha para volver
                    IconButton(onClick = onVolver) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            // Campo RUT
            OutlinedTextField(
                value = rut,
                onValueChange = { rut = it },
                label = { Text("RUT") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del paciente") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo diagnóstico
            OutlinedTextField(
                value = diagnostico,
                onValueChange = { diagnostico = it },
                label = { Text("Diagnóstico") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mensaje de error si existe
            if (mensajeError.isNotEmpty()) {
                Text(
                    text = mensajeError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Botón para guardar paciente
            Button(
                onClick = {
                    // Validamos que los campos no estén vacíos
                    when {
                        rut.isBlank() -> mensajeError = "El RUT es obligatorio"
                        nombre.isBlank() -> mensajeError = "El nombre es obligatorio"
                        diagnostico.isBlank() -> mensajeError = "El diagnóstico es obligatorio"
                        else -> {
                            // Creamos el paciente y lo guardamos en el repositorio
                            val nuevoPaciente = Paciente(
                                rut = rut.trim(),
                                nombre = nombre.trim(),
                                diagnostico = diagnostico.trim()
                            )
                            repository.agregarPaciente(nuevoPaciente)
                            
                            // Limpiamos los campos y volvemos
                            mensajeError = ""
                            onVolver()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar paciente")
            }
        }
    }
}
