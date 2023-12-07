package com.example.adminsaller.presentor.AddScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.adminsaller.R
import com.example.adminsaller.ui.theme.AdminSallerTheme
import com.example.adminsaller.ui.theme.Purple80
import org.orbitmvi.orbit.compose.collectSideEffect

class AddScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: SellerAddContract.ViewModel = getViewModel<SellerAddViewModel>()
        var progressState by remember {
            mutableStateOf(false)
        }

        val context = LocalContext.current
        viewModel.collectSideEffect() {
            when (it) {
                is SellerAddContract.SideEffect.ShowToast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is SellerAddContract.SideEffect.ProgressState -> {
                    progressState = it.state
                }
            }
        }
        SellerAddScreenContent(
            onEventDispatcher = viewModel::onEventDispatcher,
            isVisibleProgress = progressState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerAddScreenContent(
    onEventDispatcher: (SellerAddContract.Event) -> Unit,
    isVisibleProgress: Boolean
) {
    val context= LocalContext.current
    var sellerName: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFFFC107))
    ) {

        Image(
            painter = painterResource(id = R.drawable.trade),
            contentDescription = "trade",
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(top = 56.dp, start = 36.dp, end = 36.dp)
                .fillMaxWidth()
                .height(170.dp)
        )

        Column(modifier = Modifier.align(Alignment.Center)) {
            OutlinedTextField(
                value = sellerName, onValueChange = {
                    sellerName = it
                }, modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "SellerName") },
//                colors = colors(
//                    focusedBorderColor = Color(0xFFFF3951),
//                    unfocusedBorderColor = Color(0xFFFF7686),
//                ),
                singleLine = true
            )

            var isPasswordVisible by remember { mutableStateOf(false) }

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        Image(
                            painter = painterResource(id = if (isPasswordVisible) R.drawable.key1 else R.drawable.key2),
                            contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password"
                        )
                    }
                },
                singleLine = true,
                label = {
                    Text(text = "Password")
                },
            )
        }
        Button(
            onClick = {
                if (sellerName.length>3 && password.length>3){
                    onEventDispatcher.invoke(
                        SellerAddContract.Event.AddUser(
                            sellerName.trim(),
                            password.trim()
                        )
                    )
                } else{
                    Toast.makeText(context, "SellerName and password should be bigger than 3", Toast.LENGTH_SHORT).show()
                }

            }, modifier = Modifier
                .padding(bottom = 70.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor =
                (if (password.length > 3 && sellerName.length > 3) Color(0xFF2196F3) else Color(
                    0xFF03A9F4
                ))
            )
        ) {

            if (isVisibleProgress) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(2.dp),
                    color = Purple80,
                    strokeWidth = 4.dp
                )
            } else {
                Text(text = "Add Seller", fontSize = 22.sp)
            }
        }

    }
}


@Preview
@Composable
fun AddScreenPrev() {
    AdminSallerTheme() {
        SellerAddScreenContent(onEventDispatcher = {}, isVisibleProgress = false)
    }
}