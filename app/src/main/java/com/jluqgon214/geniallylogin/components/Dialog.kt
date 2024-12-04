package com.jluqgon214.geniallylogin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jluqgon214.geniallylogin.data.LoginViewModel

@Composable
fun ShowDialog(viewModel: LoginViewModel, onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
    )
    {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            viewModel.listaLogin.forEach { loginOption ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clickable {
                                viewModel.updateLoginOptionSelected(loginOption.key.toString())
                                viewModel.updateLoginListState()
                                viewModel.updateShowDialog(false)
                            }
                    ) {
                        Text(
                            text = loginOption.key,
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.CenterStart),
                            textAlign = TextAlign.Center,
                        )
                        RadioButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            selected = viewModel.loginOptionSelected.value == loginOption.key,
                            onClick = {
                                /*viewModel.listaLogin.forEach {
                                    it.value to false
                                }
                                viewModel.loginOptionSelected.value to true
                                viewModel.updateLoginOptionSelected(loginOption.key)
                                viewModel.updateShowDialog(false)*/
                            },
                        )

                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Black)
                        )
                    }


                }
            }
        }
    }
}