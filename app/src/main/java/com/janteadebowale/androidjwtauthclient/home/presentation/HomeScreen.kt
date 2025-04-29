package com.janteadebowale.androidjwtauthclient.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.SettingsSystemDaydream
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.janteadebowale.androidjwtauthclient.R
import com.janteadebowale.androidjwtauthclient.auth.presentation.login.LoginUiEvent
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.AppButton
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.DialogState
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.DialogType
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.DropDownItem
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.HomeTopBar
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.InfoDialog
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.LogoutDialog
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.ToastDialog
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component.supportWideScreen
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.theme.AndroidJwtAuthClientTheme
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.theme.Poppins
import com.janteadebowale.androidjwtauthclient.core.presentation.ui.ConsumeNavEvents
import com.janteadebowale.androidjwtauthclient.core.presentation.ui.showToast
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
fun HomeRoute(
    onLogout: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyBoardController = LocalSoftwareKeyboardController.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    var openAlertDialog by remember { mutableStateOf(false) }

    var showLogoutDialog by remember { mutableStateOf(false) }

    var forceLogout by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        LogoutDialog(
            title = stringResource(id = R.string.logout),
            info = stringResource(id = R.string.confirm_logout_message),
            onCancel = {
                showLogoutDialog = false
            },
            onConfirm = {
                showLogoutDialog = false
                viewModel.onEvent(HomeUiEvent.Logout)
            })
    }

    if(forceLogout){
        InfoDialog(info = "Token Expired. Login required!") {
            forceLogout = false
            viewModel.onEvent(HomeUiEvent.Logout)
        }
    }


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

    HomeScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onLogout = {
            showLogoutDialog = true
        }
    )

    ConsumeNavEvents(eventFlow = viewModel.homeNavChannel, lifecycleOwner = lifecycleOwner) {
        when (it) {
            is HomeNavEvent.OnError -> {
                keyBoardController?.hide()
                if (it.code.isNotEmpty() && it.code == "403") {
                    context.showToast("Token Expired. Login required!")
                    forceLogout = true
                } else {
                    context.showToast(it.code)
                    dialogState = DialogState(
                        message = it.errorMessage,
                        type = DialogType.Error
                    )
                    openAlertDialog = true
                }
            }

            HomeNavEvent.OnLogout -> {
                onLogout()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    state: HomeState,
    onEvent: (HomeUiEvent) -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            HomeTopBar(
                title = stringResource(R.string.home_title),
                dropDownMenuItems = listOf(
                    DropDownItem(
                        icon = Icons.Default.DarkMode,
                        title = "Dark Mode"
                    ),
                    DropDownItem(
                        icon = Icons.Default.LightMode,
                        title = "Light Mode"
                    ),
                    DropDownItem(
                        icon = Icons.Default.SettingsSystemDaydream,
                        title = "System Default"
                    ),
                    DropDownItem(
                        icon = Icons.Default.PowerSettingsNew,
                        title = "Logout"
                    )
                ),
                onDropDownMenuItemClick = { index ->
                    when (index) {
                        0 -> onEvent(HomeUiEvent.DarkMode)
                        1 -> onEvent(HomeUiEvent.LightMode)
                        2 -> onEvent(HomeUiEvent.SystemDefault)
                        3 -> onLogout()
                    }
                },
                startContent = {
                    Image(
                        painter = painterResource(id = if (LocalContentColor.current.luminance() < 0.5f) R.drawable.dark_logo else R.drawable.light_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .supportWideScreen()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            BioDataItem(
                data = BioData(
                    title = "Name",
                    value = state.name,
                    color = generateColor()
                )
            )

            BioDataItem(
                data = BioData(
                    title = "Phone",
                    value = state.phone,
                    color = generateColor()
                )
            )

            BioDataItem(
                data = BioData(
                    title = "Session ID",
                    value = state.sessionId,
                    color = generateColor()
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            AppButton(
                isLoading = state.isLoading,
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.auth_me),
                enableState = !state.isLoading
            ) {
                onEvent(HomeUiEvent.LoadBioData)
            }
        }
    }
}


@Composable
fun BioDataItem(data: BioData, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(60.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = data.color),
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = data.title,
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )

            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(data.color.copy(alpha = 0.2f)), contentAlignment = Alignment.Center
            ) {

                Text(
                    text = data.value,
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

data class BioData(
    val title: String, val value: String, val color: Color,
)


@Composable
fun generateColor(minColor: Int = 50, maxColor: Int = 255): Color {
    val red = (minColor..maxColor).random()
    val green = (minColor..maxColor).random()
    val blue = (minColor..maxColor).random()
    return Color(red, green, blue)
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AndroidJwtAuthClientTheme {
        HomeScreen(
            state = HomeState(),
            onEvent = {},
            onLogout = {}
        )
    }
}