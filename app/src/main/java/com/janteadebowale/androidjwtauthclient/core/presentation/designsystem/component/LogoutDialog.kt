package com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.janteadebowale.androidjwtauthclient.R
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.theme.Poppins

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
fun LogoutDialog(
    info: String,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    title: String? = null,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
) {

    AlertDialog(
        shape = RoundedCornerShape(8.dp),
        icon = icon,
        title = {
            title?.let {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = Poppins,
                    fontSize = 15.sp
                )
            }

        }, text = {
            Text(text = info)
        }, onDismissRequest = {
            // onCancel()
        }, confirmButton = {

            TextButton(onClick = { onConfirm() }) {
                Text(
                    text = stringResource(R.string.confirm),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        dismissButton = {

            TextButton(onClick = { onCancel() }) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        })
}