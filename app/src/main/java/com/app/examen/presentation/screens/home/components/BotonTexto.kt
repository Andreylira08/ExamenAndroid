package com.app.examen.presentation.screens.home.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun BotonTexto(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Arquitectura",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    """
                    MVVM + Clean Architecture
                    - CountryRepository define cómo obtener países
                    
                    Estrategia de guardado de preferencias
                    - 
                    
                    Búsqueda
                    - Filtramos países por nombre que se pasa como common desde la API
                    """.trimIndent(),
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = onDismiss, modifier = Modifier.padding(8.dp)) {
                    Text("Ok")
                }
            }
        }
    }
}