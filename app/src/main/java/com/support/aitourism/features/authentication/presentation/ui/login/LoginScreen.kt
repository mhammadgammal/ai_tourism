package com.support.aitourism.features.authentication.presentation.ui.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.support.aitourism.R
import com.support.aitourism.features.authentication.data.datasource.remote_datasource.Resource
import com.support.aitourism.features.authentication.presentation.view_model.AuthenticationViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {

    val viewModel = hiltViewModel<AuthenticationViewModel>()
    val result = viewModel.result.collectAsState()

    val email = mutableStateOf("")

    val password = mutableStateOf("")
    Box(
        modifier = Modifier

            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                ),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Welcome",
                modifier = Modifier.padding(
                    start = 60.dp,
                    top = 150.dp,
                    bottom = 75.dp
                ),
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Email",
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 31.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            LoginKeyBoard(
                valueModifier = email,
                label = "Email", icon = Icons.Outlined.Email
            )
            Box(modifier = Modifier.height(20.dp))
            Text(
                text = "Password",
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 31.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            LoginKeyBoard(
                valueModifier = password,
                label = "Password",
                icon = Icons.Outlined.Lock,
                obscureText = true
            )
            Box(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                onClick = { viewModel.login(email.value, password.value) }) {
                if (result.value is Resource.Loading) {
                    CircularProgressIndicator()
                } else {
                    Text(text = "Login", fontSize = 24.sp)
                }
            }

            if (result.value is Resource.Success) {
                onLoginSuccess()
            } else if (result.value is Resource.Failure) {
                Toast.makeText(
                    LocalContext.current,
                    (result.value as Resource.Failure).exception.message!!,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginKeyBoard(
    valueModifier: MutableState<String>,
    label: String,
    icon: ImageVector,
    obscureText: Boolean = false,
) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = valueModifier.value,
        onValueChange = { valueModifier.value = it },
        label = { Text(label) },
        singleLine = true,
        leadingIcon = {
            Image(imageVector = icon, contentDescription = null)
        },

        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White, // Set container color to transparent
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            errorContainerColor = Color.Transparent,
            cursorColor = Color.Black,
            selectionColors = LocalTextSelectionColors.current,
            focusedBorderColor = Color.Black, // Border color when focused
            unfocusedBorderColor = Color.LightGray, // Border color when not focused
            focusedLabelColor = Color.Black, // Label color when focused
            unfocusedLabelColor = Color.Gray, // Label color when not focused
        ),
        visualTransformation = if (obscureText) {
            if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        } else {
            VisualTransformation.None
        },
        trailingIcon = if (obscureText) {
            {
                Image(
                    painter = if (passwordVisibility) {
                        painterResource(id = R.drawable.visibility_24px)
                    } else {
                        painterResource(id = R.drawable.visibility_off_24px)
                    },
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        passwordVisibility = !passwordVisibility
                    }
                )
            }
        } else {
            null
        },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            )
    )
}
