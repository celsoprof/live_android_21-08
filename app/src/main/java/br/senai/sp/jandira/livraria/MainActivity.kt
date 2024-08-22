package br.senai.sp.jandira.livraria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.livraria.screens.ListaLivrosScreen
import br.senai.sp.jandira.livraria.screens.NovoLivroScreen
import br.senai.sp.jandira.livraria.ui.theme.PreçoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            PreçoTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "listaLivros"
                ){
                    composable(route = "listaLivros"){ ListaLivrosScreen(navegador = navController) }
                    composable(route = "NovoLivro"){ NovoLivroScreen(navegador = navController) }
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
    PreçoTheme {
        Greeting("Android")
    }
}