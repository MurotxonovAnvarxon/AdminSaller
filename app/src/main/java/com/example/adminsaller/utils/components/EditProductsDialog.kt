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
import com.example.adminsaller.data.model.ProductsData
import com.example.adminsaller.data.model.SellerData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductsDialog(
    productsData: ProductsData,
    onClickEdit: (ProductsData) -> Unit,
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

                var productName: String by remember { mutableStateOf(productsData.productName) }
                var productCount: String by remember { mutableStateOf(productsData.productCount.toString()) }
                var productInitialPrice: String by remember { mutableStateOf(productsData.productInitialPrice.toString()) }
                var productSellingPrice: String by remember { mutableStateOf(productsData.productSellingPrice.toString()) }
                var productIsValid: String by remember { mutableStateOf(productsData.productIsValid.toString()) }
                var productComment: String by remember { mutableStateOf(productsData.productComment) }

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    OutlinedTextField(
                        value = productName, onValueChange = {
                            productName = it
                        }, modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = { Text(text = "productName") },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = productCount,
                        onValueChange = {
                            productCount = it
                        },  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                            .fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text(text = "count")
                        },
                    )
                    OutlinedTextField(
                        value = productInitialPrice, onValueChange = {
                            productInitialPrice = it
                        }, modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        label = { Text(text = "productInitialPrice") },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = productSellingPrice,
                        onValueChange = {
                            productSellingPrice = it
                        },  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                            .fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text(text = "productSellingPrice")
                        },
                    )
                    OutlinedTextField(
                        value = productIsValid, onValueChange = {
                            productIsValid = it
                        }, modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = { Text(text = "productIsValid") },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = productComment,
                        onValueChange = {
                            productComment = it
                        },  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                            .fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text(text = "productComment")
                        },
                    )
                }
                Spacer(modifier = Modifier.size(30.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 600.dp)) {
                    Button(
                        onClick = { onClickCancel() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
                    ) {
                        Text(text = "Cancel", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = {

                            onClickEdit(ProductsData(productID = "", productName = productName,productCount.toInt(),productInitialPrice.toInt(),productSellingPrice.toInt(),productIsValid.toBoolean(),productComment)) },
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
