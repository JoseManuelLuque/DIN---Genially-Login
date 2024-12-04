package com.jluqgon214.geniallylogin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jluqgon214.geniallylogin.R
import com.jluqgon214.geniallylogin.data.LoginViewModel
import com.jluqgon214.geniallylogin.ui.theme.ButtonColor

@Composable
fun TopBar(modifier: Modifier) {
    // Declaracion de variables

    val viewModel: LoginViewModel = hiltViewModel()

    var expanded = remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()


    // Contenido de la pantalla
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
        ) {
            //LOGO GENIALLY
            Image(
                painter = painterResource(R.drawable.iconotop),
                contentDescription = "Logo Genially",
                modifier = Modifier
                    .weight(2f)
                    .size(38.dp)
                    .align(Alignment.CenterVertically)
            )

            //BOTON

            Button(
                modifier = Modifier
                    .weight(1.5f)
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically),
                onClick = { /*TODO*/ },
                colors = ButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "Regístrate")
            }

            //IDIOMA

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier.clickable { expanded.value = true }
                ) {
                    if (expanded.value == true) {
                        Text(
                            viewModel.languageSelected.value,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            color = ButtonColor
                        )
                    } else {
                        Text(
                            viewModel.languageSelected.value,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "Localized description"
                    )
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                        scrollState = scrollState
                    ) {
                        viewModel.languagueList.forEach {idioma ->
                            DropdownMenuItem(
                                text = { Text(idioma.key) },
                                onClick = {
                                    viewModel.updateSelectedLanguage(idioma.key.toString())
                                    expanded.value = false
                                },
                                trailingIcon = {
                                    if (viewModel.languageSelected.value == idioma.key.take(2).uppercase()) {
                                        Icon(
                                            Icons.Default.Check,
                                            contentDescription = "Languague Selected"
                                        )
                                    }
                                }
                            )
                        }
                    }
                    LaunchedEffect(expanded.value) {
                        if (expanded.value) {
                            // Scroll to show the bottom menu items.
                            scrollState.scrollTo(scrollState.maxValue)
                        }
                    }
                }

            }
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}