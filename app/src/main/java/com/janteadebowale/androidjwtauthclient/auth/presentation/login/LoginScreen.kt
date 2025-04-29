package com.janteadebowale.androidjwtauthclient.auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.janteadebowale.androidjwtauthclient.R
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.AppButton
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.DialogState
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.DialogType
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.PasswordComponent
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.ScreenPreview
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.ToastDialog
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.UsernamePhoneComponent
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.supportWideScreen
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.theme.AndroidJwtAuthClientTheme
import com.janteadebowale.androidjwtauthclient.core.presentation.ui.ConsumeNavEvents
import org.koin.androidx.compose.koinViewModel

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/

@Composable

fun LoginRoute(
    onRegister: () -> Unit,
    onNavigateToHome : () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    var openAlertDialog by remember { mutableStateOf(false) }

    var dialogState by remember {
        mutableStateOf(DialogState())
    }
    when {
        openAlertDialog -> {
            ToastDialog(state = dialogState) {
                openAlertDialog = false
            }
        }
    }

    ConsumeNavEvents(viewModel.loginNavChannel,lifecycleOwner) {
        when(it){
            is LoginNavEvent.Error -> {
                keyBoardController?.hide()
                dialogState = DialogState(
                    message = it.errorMessage,
                    type = DialogType.Error
                )
                openAlertDialog = true
            }
            LoginNavEvent.Success -> {
               onNavigateToHome()
            }
        }
    }

    LoginScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onRegister = {
            onRegister()
        }
    )
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onEvent: (LoginUiEvent) -> Unit,
    onRegister: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .supportWideScreen()
            .padding(vertical = 50.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {


        Text(
            text = stringResource(id = R.string.login_header),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
        )

        Text(
            text = stringResource(id = R.string.welcome_back),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            ),
        )

        UsernamePhoneComponent(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.username,
            onValueChanged = {
                onEvent(LoginUiEvent.OnUsernameChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone
            ),
            keyboardActions = KeyboardActions(onNext = {
                passwordFocusRequester.requestFocus()
            })
        )

        PasswordComponent(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester),
            value = state.password,
            onValueChanged = {
                onEvent(LoginUiEvent.OnPasswordChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                onEvent(LoginUiEvent.OnLoginClicked)
            })
        )

        Spacer(modifier = Modifier.height(5.dp))

        AppButton(
            isLoading = state.isLoading,
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.login),
            enableState = state.canLogin
        ) {
            if (!state.isLoading) {
                focusManager.clearFocus()

                onEvent(LoginUiEvent.OnLoginClicked)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = {
                onRegister()
            }) {
                Text(text = "New User?")
            }
        }

    }
}

@ScreenPreview
@Composable
private fun LoginScreenPreview() {
    AndroidJwtAuthClientTheme {
        LoginScreen(
            state = LoginState(),
            onEvent = {},
            onRegister = {}
        )
    }
}

