package com.example.adminsaller.utils.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.adminsaller.R
import com.example.adminsaller.data.model.SellerData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDialog(
    sellerData: SellerData,
    onClickEdit: (SellerData) -> Unit,
    onClickCancel: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
            onClickCancel()
        },
        confirmButton = {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
            ) {

                var sellerName: String by remember { mutableStateOf(sellerData.sellerName) }
                var password: String by remember { mutableStateOf(sellerData.password) }
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    OutlinedTextField(
                        value = sellerName, onValueChange = {
                            sellerName = it
                        }, modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = { Text(text = "SellerName") },
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
                Spacer(modifier = Modifier.size(30.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 250.dp)) {
                    Button(
                        onClick = { onClickCancel() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
                    ) {
                        Text(text = "Cancel", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = {
                            Log.d("TAG", "EditDialog:${sellerName} ")

                            onClickEdit(SellerData(id = "",sellerName, password )) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
                    ) {
                        Text(text = "Edit", color = Color.Black)
                    }
                }
            }

        }
    )

}

//@Preview(showBackground = true)
//@Composable
//fun getDeleteDialogPreview() {
//    DeleteDialog(onClickDelete = { /*TODO*/ }) {
//
//    }
//}
