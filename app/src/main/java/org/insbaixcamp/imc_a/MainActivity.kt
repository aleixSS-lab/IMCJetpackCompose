package org.insbaixcamp.imc_a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.insbaixcamp.imc_a.ui.theme.IMCATheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BMIScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IMCATheme {
       BMIScreen()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIScreen(modifier: Modifier = Modifier){
    var nom: String by remember{ mutableStateOf("") }
    var pes:Int by remember{ mutableStateOf(80)}
    var alcada:Int by remember{ mutableStateOf(180)}
    var imc:Double by remember{ mutableStateOf(0.0)}
    val alcadaMetres = alcada / 100f

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Calculadora de IMC")
        TextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text(text = "Nombre") }
        )


        Row {
            Text(text = "Pes: $pes Kg ")
            Button(onClick = { if (pes >= 5) pes -= 5 }) { Text(text = "-5") }
            Button(onClick = { if (pes > 0) pes-- }) { Text(text = "-") }
            Button(onClick = { pes++ }) { Text(text = "+") }
            Button(onClick = { pes += 5 }) { Text(text = "+5") }
        }

        Text(text = "Alçada: $alcada cm")
        Slider(
            value = alcada.toFloat(),
            onValueChange = { alcada = it.toInt() },
            valueRange = 100f..250f
        )

        Button(onClick = {
            if (alcadaMetres > 0) {
                imc = (pes / (alcadaMetres * alcadaMetres)).toDouble()
            }
        }) {
            Text(text = "Calcular tu IMC")
        }

        if (imc != 0.0) {
            val imcFormateado = String.format(Locale.getDefault(), "%.2f", imc)
            val saludo = if (nom.isNotBlank()) "¡Hola $nom! " else ""
            Text(text = "${saludo}Tu IMC es: $imcFormateado")
        }
    }
}