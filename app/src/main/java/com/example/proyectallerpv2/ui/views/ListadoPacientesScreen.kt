package com.example.proyectallerpv2.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectallerpv2.model.Paciente
import com.example.proyectallerpv2.repository.PacienteRepository
import com.example.proyectallerpv2.ui.views.animation.LottieLoader

import kotlinx.coroutines.delay // Para simular una espera en LaunchedEffect


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoPacientesScreen(
    onVolver: () -> Unit    // Función para volver a Home
) {
    // Obtenemos la instancia del repositorio
    val repository = remember { PacienteRepository.getInstance() }

    // Estado con la lista de pacientes del repositorio
    var pacientes by remember { mutableStateOf(repository.obtenerTodosPacientes()) }

    // Estado que indica si estamos "cargando" información
    //**** NUEVO
    var estaCargando by remember { mutableStateOf(true) }

    //**** NUEVO
    // Mensaje que se mostrará junto al indicador de carga
    var mensajeCarga by remember { mutableStateOf("Cargando pacientes...") }


    // Actualizamos la lista cada vez que entramos a la pantalla
    LaunchedEffect(Unit) {
        mensajeCarga = "Cargando pacientes..." //NUEVO *****

        estaCargando = true//NUEVO *****

        delay(5000)//NUEVO *****

        pacientes = repository.obtenerTodosPacientes()


        // Apagamos el indicador de carga
        estaCargando = false//NUEVO *****

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de Pacientes") },
                navigationIcon = {
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
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            // 1) Si estamos cargando, mostramos el indicador de progreso
            if (estaCargando) {
                LottieLoader(
                    modifier = Modifier.align(Alignment.Center),
                    mensaje = mensajeCarga        // por ejemplo: "Cargando pacientes..."
                )
            } else {


                if (pacientes.isEmpty()) {
                // Si no hay pacientes mostramos un mensaje
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No hay pacientes registrados",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Agrega un paciente desde el menú",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                // Mostramos la lista de pacientes
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(pacientes) { paciente ->
                        // Card para cada paciente
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = paciente.nombre,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "RUT: ${paciente.rut}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Diagnóstico: ${paciente.diagnostico}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }//finsi existen pacientes!

                }// fin espera

            }
        }
    }
}
