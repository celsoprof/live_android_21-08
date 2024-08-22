package br.senai.sp.jandira.livraria.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import br.senai.sp.jandira.livraria.model.Livro
import br.senai.sp.jandira.livraria.repositorio.LivroRepositorio
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovoLivroScreen(modifier: Modifier = Modifier, navegador: NavHostController?) {

    val context = LocalContext.current
    val livroRepositorio = LivroRepositorio(context)

    val titulo = remember {
        mutableStateOf("")
    }

    val autor = remember {
        mutableStateOf("")
    }

    val dataPub = remember {
        mutableStateOf("")
    }

    val valor = remember {
        mutableStateOf("")
    }

    val isUsado = remember {
        mutableStateOf(false)
    }

    //Configuração DatePicker
    val showDatePicker = remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()
    val dataSelecionada = datePickerState.selectedDateMillis?.let {
        converterMillsParaData(it)
    } ?: " "

    if (showDatePicker.value){
        Popup(
            onDismissRequest = {showDatePicker.value = false}
        ) {
            Card(
                modifier = Modifier.padding(16.dp)
            ) {
                DatePicker(state = datePickerState, showModeToggle = false)
            }
        }
    }

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(Color(0xFF2196F3))
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Adicionar livro",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = {
                    navegador?.navigate("ListaLivros")
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Column(modifier = Modifier.padding(32.dp)) {
                    OutlinedTextField(
                        value = titulo.value,
                        shape = RoundedCornerShape(12.dp),
                        onValueChange = { titulo.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = "Titulo do livro")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = autor.value,
                        shape = RoundedCornerShape(12.dp),
                        onValueChange = { autor.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = "Autor do livro")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = dataSelecionada.toString(),
                        shape = RoundedCornerShape(12.dp),
                        onValueChange = { dataPub.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = "Data de publicação")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            IconButton(onClick = {
                                showDatePicker.value = !showDatePicker.value
                            }) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = ""
                                )
                            }

                        }
                    )
//                    if (showDatePicker.value){
//                        Popup(
//                            onDismissRequest = {showDatePicker.value = false}
//                        ) {
//                            Card(
//                                modifier = Modifier.padding(16.dp)
//                            ) {
//                                DatePicker(state = datePickerState, showModeToggle = false)
//                            }
//                        }
//                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = valor.value,
                        shape = RoundedCornerShape(12.dp),
                        onValueChange = { valor.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = "Valor do livro $")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isUsado.value,
                            onCheckedChange = { usado ->
                                isUsado.value = usado
                            }
                        )
                        Text(text = "Livro usado")
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = {
                            val livro = Livro(
                                titulo = titulo.value,
                                autor = autor.value,
                                dataPublicacao = dataSelecionada,
                                valor = valor.value.toDouble(),
                                usado = isUsado.value
                            )
                            val id = livroRepositorio.salvar(livro);
                            Toast.makeText(context, "O livro foi criado com o id: $id", Toast.LENGTH_LONG).show()
                            navegador?.navigate("listaLivros")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = Color(
                                    0xFF2196F3
                                )
                            )
                    ) {
                        Text(
                            text = "Adicionar Livro",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        },
        bottomBar = {

        },

        )

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun NovoLivroScreenPreview() {
    NovoLivroScreen(navegador = null)
}

fun converterMillsParaData(mills: Long): String {
    val formatador = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    formatador.timeZone = TimeZone.getTimeZone("GMT")
    return formatador.format(Date(mills))
}