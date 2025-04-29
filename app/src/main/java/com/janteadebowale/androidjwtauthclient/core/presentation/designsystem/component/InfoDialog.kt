package com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.janteadebowale.androidjwtauthclient.R
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.theme.Poppins
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.theme.infoContainer
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.theme.onInfoContainer
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.theme.onSuccessContainer
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.theme.successContainer
import kotlinx.coroutines.delay

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
fun InfoDialog(
    info: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {

    Dialog(
        properties = DialogProperties(
            dismissOnClickOutside = false
        ), onDismissRequest = {

        }) {
        LaunchedEffect(Unit) {
            delay(4000L)
            onDismissRequest()
        }
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.infoContainer,
            tonalElevation = 8.dp,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(45.dp),
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onInfoContainer
                )
                Text(
                    text = info,
                    textAlign = TextAlign.Center,
                    color =  MaterialTheme.colorScheme.onInfoContainer
                )

                TextButton(onClick = { onDismissRequest() }) {
                    Text(
                        text = stringResource(R.string.ok),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

    }
}