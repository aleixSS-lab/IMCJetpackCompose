package org.insbaixcamp.imc_a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import org.insbaixcamp.imc_a.ui.theme.IMCATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BMIScreen()
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
fun BMIScreen(){
    var nom: String by remember{ mutableStateOf("") }
    var pes:Int by remember{ mutableStateOf(80)}
    var alcada:Int by remember{ mutableStateOf(180)}
    var imc:Double by remember{ mutableStateOf(0.0)}
    val alcadaMetres = alcada /100f

    Column() {
        Text(text = "Calculadora de IMC")
        TextField(
            value = "",
            onValueChange = {nom = it},
            label = { Text(text = "Peso") })


    Row(){
        Text(text = "Pes: $pes Kg")
        Button(onClick = {pes++}) { Text(text = "+")}
        Button(onClick = {pes--}) { Text(text = "-") }
    }

        Text(text = "Alçada: $alcada cm")
        Slider(
            value = alcada.toFloat(),
            onValueChange = {alcada = it.toInt()},
            valueRange = 100f..250f
        )

        Button( onClick = {

            imc = pes / (alcadaMetres * alacadaMetres)
        }){
            Text(text = "Calcular tu IMC")
        }
        Text(text = "Tu IMC es: $imc")

        }
if(imc !=0.0){
    Text(text = "IMC: $imc")
}
}
