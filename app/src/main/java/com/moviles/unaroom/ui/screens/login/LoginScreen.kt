package com.moviles.unaroom.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MeetingRoom
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moviles.unaroom.ui.components.AppButton
import com.moviles.unaroom.ui.components.AppTextField
import com.moviles.unaroom.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                Snackbar(
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
                    containerColor = AppError,
                    contentColor = AppBackground,
                    shape = RoundedCornerShape(18.dp),
                    snackbarData = snackbarData
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 26.dp, vertical = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LoginHeader()

                Spacer(modifier = Modifier.height(56.dp))

                AppTextField(
                    value = email,
                    label = "Email",
                    placeholder = "student@university.edu",
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(22.dp))

                AppTextField(
                    value = password,
                    label = "Password",
                    placeholder = "•••••••",
                    onValueChange = { password = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(22.dp))

                AppButton(
                    text = "Login",
                    onClick = {
                        if (email.isBlank() || password.isBlank()) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Please fill in all fields",
                                    withDismissAction = true
                                )
                            }
                        } else {
                            onLoginClick()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(34.dp))

                Text(
                    text = "Demo: Use any email and password",
                    color = AppSecondaryText,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun LoginHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(82.dp)
                .background(
                    color = AppSurfaceVariant,
                    shape = RoundedCornerShape(22.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.MeetingRoom,
                contentDescription = null,
                tint = AppPrimary,
                modifier = Modifier.size(38.dp)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "UnaRoom",
            style = MaterialTheme.typography.headlineLarge,
            color = AppPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Classroom Reservation",
            color = AppSecondaryText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}