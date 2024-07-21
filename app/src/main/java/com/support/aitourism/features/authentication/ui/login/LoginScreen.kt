package com.support.aitourism.features.authentication.ui.login

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.support.aitourism.R

const val TAG: String = "LoginScreen"

@Composable
fun LoginScreen() {

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

            LoginKeyBoard(label = "Email", icon = Icons.Outlined.Email)
            Box(modifier = Modifier.height(20.dp))
            Text(
                text = "Password",
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 31.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            LoginKeyBoard(
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
                onClick = { Log.d(TAG, "LoginScreen: Login btn pressed") }) {
                Text(text = "Login", fontSize = 24.sp)
            }
        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginKeyBoard(
    label: String,
    icon: ImageVector,
    obscureText: Boolean = false,
) {
    var valueModifier by remember {
        mutableStateOf("")
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = valueModifier,
        onValueChange = { valueModifier = it },
        label = { Text(label) },
        singleLine = true,
        leadingIcon = {
            Image(imageVector = icon, contentDescription = null)
        },

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = Color.Black, // Label color when focused
            unfocusedLabelColor = Color.Gray, // Label color when not focused
            focusedBorderColor = Color.Black, // Border color when focused
            unfocusedBorderColor = Color.LightGray, // Border color when not focused
            containerColor = Color.White, // Set container color to transparent
            cursorColor = Color.Black,
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
