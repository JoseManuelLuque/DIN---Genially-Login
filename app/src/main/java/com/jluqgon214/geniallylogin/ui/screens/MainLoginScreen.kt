package com.jluqgon214.geniallylogin.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jluqgon214.geniallylogin.R
import com.jluqgon214.geniallylogin.components.ShowDialog
import com.jluqgon214.geniallylogin.data.LoginViewModel
import com.jluqgon214.geniallylogin.ui.theme.ButtonColor
import com.jluqgon214.geniallylogin.ui.theme.UnboundedExtraBold

@Composable
fun MainLoginScreen(modifier: Modifier) {
    // Declaracion de variables
    val viewModel: LoginViewModel = hiltViewModel()
    var showDialog = viewModel.showDialog.collectAsState()
    var email = viewModel.email.collectAsState()
    var password = viewModel.password.collectAsState()
    val passwordVisible = viewModel.passwordVisible.collectAsState()
    val rememberMe = viewModel.rememberMe.collectAsState()

    // Contexto para el Toast
    val context = LocalContext.current

    // Contenido de la pantalla
    if (showDialog.value) {
        ShowDialog(
            onDismissRequest = { viewModel.updateShowDialog(false) }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(viewModel.defaultPadding),
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .padding(viewModel.defaultPadding)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 26.sp,
                fontFamily = UnboundedExtraBold
            )
        }

        // Botones de inicio de sesión
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            OutlinedButton(
                onClick = {
                    //TODO: Google Login
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = viewModel.mediumPadding)
                    .height(48.dp),
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                )
            )
            {

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_de_google),
                        contentDescription = "Google Logo",
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.CenterStart),
                    )
                    Text(
                        text = "Inicia sesión con Google",
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

            }

            OutlinedButton(
                onClick = {
                    viewModel.updateShowDialog(true)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = viewModel.mediumPadding)
                    .height(48.dp),
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                )
            )
            {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = viewModel.loginOptionSelected.value, modifier = Modifier.align(Alignment.CenterStart))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Arrow Down",
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
        }

        // Texto divisor: o con tu email o contraseña
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            Text(
                "o con tu email y constraseña:",
                modifier = Modifier
                    .weight(2.5f)
                    .align(Alignment.CenterVertically),
                fontSize = 12.sp,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }

        //Text Fields: Email y Contraseña
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            OutlinedTextField(
                value = email.value,
                onValueChange = { viewModel.updateEmail(it) },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { viewModel.updatePassword(it) },
                label = { Text("Contraseña") },
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility
                    val description =
                        if (passwordVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = { viewModel.updateShowPassword(!viewModel.passwordVisible.value) }) {
                        Icon(imageVector = image, description)
                    }
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row {
                RadioButton(
                    selected = rememberMe.value,
                    onClick = { viewModel.updateRememberMe(!viewModel.rememberMe.value) },
                    colors = RadioButtonColors(
                        selectedColor = ButtonColor,
                        disabledSelectedColor = Color.Gray,
                        disabledUnselectedColor = Color.White,
                        unselectedColor = Color.Black
                    )
                )
                Text(
                    "Recuérdame",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 12.sp
                )
            }


            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "¿Has olvidado tu contraseña?",
                fontSize = 12.sp,
                textDecoration = TextDecoration.Underline
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = viewModel.defaultPadding),
            onClick = {
                for (user in viewModel.registeredUsers) {
                    if (user.email == email.value && user.password == password.value) {
                        Toast.makeText(context, "Bienvenido ${user.email}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            colors = ButtonColors(
                containerColor = ButtonColor,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )
        ) {
            Text(text = "Entrar")
        }

        Text(
            text = "Entrar con single sing-on(SSO)",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textDecoration = TextDecoration.Underline
        )

        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Aún no tienes una cuenta?",
                fontSize = 12.sp,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "Registrate",
                textDecoration = TextDecoration.Underline,
                fontSize = 12.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}