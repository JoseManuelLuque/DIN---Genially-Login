package com.jluqgon214.geniallylogin.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.jluqgon214.geniallylogin.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor()  : ViewModel() {
    private var _email = MutableStateFlow("")
    var email: StateFlow<String> = _email.asStateFlow()

    private var _password = MutableStateFlow("")
    var password: StateFlow<String> = _password.asStateFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible.asStateFlow()

    private var _rememberMe = MutableStateFlow(false)
    var rememberMe: StateFlow<Boolean> = _rememberMe.asStateFlow()

    private var _showDialog = MutableStateFlow(false)
    var showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    /* Variables y funciones de lista de idiomas */
    var languagueList = mutableMapOf<String, Boolean>("Español" to true, "Ingles" to false , "Aleman" to false, "Frances" to false)

    private var _languageSelected = mutableStateOf("ES") // Inicializa con el idioma seleccionado
    var languageSelected = _languageSelected

    fun updateSelectedLanguage(newLanguage: String) {
        _languageSelected.value = newLanguage.toString().take(2).uppercase()
    }

    /* Variables y funciones de lista de Opciones para hacer login */
    var listaLogin = mutableMapOf<String, Boolean>("Otros" to true,"Facebook" to false, "Office365" to false , "Twitter" to false, "LinkedIn" to false)

    private var _loginOptionSelected = mutableStateOf("Otros")
    var loginOptionSelected = _loginOptionSelected

    fun updateLoginListState() {
        listaLogin.forEach {
            it.value to false
        }
        listaLogin[_loginOptionSelected.value] to true
    }

    fun updateLoginOptionSelected(newOption: String) {
        _loginOptionSelected.value = newOption
    }

    /* Padding Globales */
    val defaultPadding = 24.dp
    val mediumPadding = 4.dp

    //Usuarios registrados
    val registeredUsers: List<User> = listOf(User("hola", "hola"))

    // Funciones para actulizar estados (updateEmail, updatePassword, etc.)
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updateShowPassword(showPassword: Boolean) {
        _passwordVisible.value = showPassword
    }

    fun updateShowDialog(newValue: Boolean) {
        _showDialog.value = newValue
    }

    fun updateRememberMe(newValue: Boolean) {
        _rememberMe.value = newValue
    }
}