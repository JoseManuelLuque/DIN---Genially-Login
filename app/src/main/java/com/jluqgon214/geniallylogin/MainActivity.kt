package com.jluqgon214.geniallylogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.jluqgon214.geniallylogin.components.TopBar
import com.jluqgon214.geniallylogin.ui.screens.MainLoginScreen
import com.jluqgon214.geniallylogin.ui.theme.GeniallyLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeniallyLoginTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //MainLoginScreen(modifier = Modifier.padding(innerPadding))
                    TopBar(modifier = Modifier.padding(innerPadding))
                    MainLoginScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}