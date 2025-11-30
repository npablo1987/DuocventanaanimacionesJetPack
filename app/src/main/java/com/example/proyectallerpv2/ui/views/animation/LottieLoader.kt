package com.example.proyectallerpv2.ui.views.animation

// Imports de Compose básicos
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Imports de Lottie para Compose
import com.airbnb.lottie.compose.*

// Import de tu recurso raw
import com.example.proyectallerpv2.R

/**
 * Composable que muestra la animación Lottie como loader,
 * junto con un mensaje de texto.
 */
@Composable
fun LottieLoader(
    modifier: Modifier = Modifier,
    mensaje: String = "Cargando pacientes..."
) {
    // 1) Cargamos la composición de Lottie desde res/raw/rocketloader.json
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.rocketloader)
    )

    // 2) Controlamos el progreso de la animación
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever, // repetir para siempre
        isPlaying = true                             // siempre en reproducción
    )

    // 3) Layout: centramos la animación y el texto
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // LottieAnimation dibuja la animación en pantalla
        LottieAnimation(
            composition = composition,  // qué animación
            progress = { progress },    // en qué porcentaje va
            modifier = Modifier
                .height(180.dp)        // alto del loader
                .fillMaxWidth()        // ocupa todo el ancho disponible
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texto con el mensaje de carga
        Text(
            text = mensaje,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}