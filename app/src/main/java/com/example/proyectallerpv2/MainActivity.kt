package com.example.proyectallerpv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectallerpv2.ui.theme.Proyectallerpv2Theme


// Imports para manejar estado en Compose
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// Importamos nuestro enum de pantallas y el composable raíz
import com.example.proyectallerpv2.ui.views.Pantalla
import com.example.proyectallerpv2.ui.views.PacientesApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyectallerpv2Theme {
                // Estado que indica qué pantalla se está mostrando
                // Partimos en la pantalla Home
                var pantallaActual by remember { mutableStateOf(Pantalla.Home) }

                // Llamamos al composable raíz de la app
                // Le pasamos:
                //  - pantallaActual: enum con la pantalla actual
                //  - onCambiarPantalla: función que permite cambiar el valor del estado
                PacientesApp(
                    pantallaActual = pantallaActual,
                    onCambiarPantalla = { nuevaPantalla ->
                        pantallaActual = nuevaPantalla   // Actualizamos el estado
                    }
                )

            }
        }
    }
}
