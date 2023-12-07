package com.example.adminsaller.presentor.addProductScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.adminsaller.presentor.AddScreen.SellerAddContract
import com.example.adminsaller.presentor.productScreen.ProductContract
import com.example.adminsaller.ui.theme.Purple80
import org.orbitmvi.orbit.compose.collectSideEffect

class AddProductScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val vm = getViewModel<AddProductsViewModel>()
        var progressState by remember {
            mutableStateOf(false)
        }
        val context = LocalContext.current
        vm.collectSideEffect() {
            when (it) {
                is AddProductsContract.SideEffect.ShowToast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is AddProductsContract.SideEffect.ProgressState -> {
                    progressState = it.state
                }
            }
        }

        AddProductScreenContent(
            onEventDispatcher = vm::onEventDispatcher,
            isVisibleProgress = progressState
        )
    }
}


@Composable
@Preview(showBackground = true)
fun AddProductScreenPreview() {
    AddProductScreenContent(onEventDispatcher = {}, false)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreenContent(
    onEventDispatcher: (AddProductsContract.Event) -> Unit,
    isVisibleProgress: Boolean
) {
    val context = LocalContext.current
    var productName: String by remember { mutableStateOf("") }
    var productCount: String by remember { mutableStateOf("") }
    var productInitialPrice: String by remember { mutableStateOf("") }
    var productSellingPrice: String by remember { mutableStateOf("") }
    var productIsValid2: Boolean by remember { mutableStateOf(false) }
    var productComment: String by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFFFC107))
    ) {

        Image(
            painter = painterResource(id = R.drawable.boxes),
            contentDescription = "trade",
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(top = 36.dp, start = 36.dp, end = 36.dp)
                .fillMaxWidth()
                .height(170.dp)
        )

        Column(modifier = Modifier.align(Alignment.Center)) {
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
                value = productCount, onValueChange = {
                    productCount = it
                }, modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                label = { Text(text = "count") },

                singleLine = true
            )


            Spacer(modifier = Modifier.height(8.dp))

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
                value = productSellingPrice, onValueChange = {
                    productSellingPrice = it
                }, modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                label = { Text(text = "productSellingPrice") },

                singleLine = true
            )


            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = productComment, onValueChange = {
                    productComment = it
                }, modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "productComment") },

                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))


        }
        Button(
            onClick = {
                if (productName.length > 3 ) {
                    onEventDispatcher.invoke(
                        AddProductsContract.Event.AddProduct(
                            productID = "",
                            productName.trim(),
                            productCount.toInt(),
                            productInitialPrice.toInt(),
                            productSellingPrice.toInt(),
                            productIsValid2 ,
                            productComment,
                            )
                    )
                } else {
                    Toast.makeText(
                        context,
                        "ProductName ",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }, modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 70.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor =
                (if (productName.length > 3) Color(0xFF2196F3) else Color(
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
                Text(text = "Add Product", fontSize = 22.sp)
            }
        }

    }

}
