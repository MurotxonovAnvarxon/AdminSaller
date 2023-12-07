package com.example.adminsaller.presentor.homeScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.adminsaller.R
import com.example.adminsaller.data.model.SellerData
import com.example.adminsaller.utils.components.DeleteDialog
import com.example.adminsaller.utils.components.EditDialog
import com.example.adminsaller.utils.components.SellerItem

class HomeScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: HomeContract.ViewModel = getViewModel<HomeViewModel>()
        MainScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun MainScreenContent(
    uiState: State<HomeContract.UIState>,
    onEventDispatcher: (HomeContract.Intent) -> Unit
) {
    val id = remember { mutableStateOf("") }
    val sellerPass = remember { mutableStateOf("") }
    val sellerName = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val showEditDialog = remember { mutableStateOf(false) }
    if (showDialog.value) DeleteDialog(
        onClickDelete = {
            showDialog.value = false
            onEventDispatcher.invoke(
                HomeContract.Intent.DeleteSeller(
                    SellerData(
                        id.value,
                        sellerName.value,
                        sellerPass.value
                    )
                )
            )
        },
        onClickCancel = { showDialog.value = false }
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (uiState.value.isLoading) {
            CircularProgressIndicator(
                color = Color(0xFF03A9F4),
                strokeWidth = 2.dp,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color(0xFFFFC107))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Seller List",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.weight(1f))


            Image(painter = painterResource(id = R.drawable.boxes),
                contentDescription = "boxes",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        onEventDispatcher.invoke(
                            HomeContract.Intent.MoveToProductsScreen
                        )
                    })

        }


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.value.sellerList) {
                SellerItem(
                    model = it,
                    onClick = {
                        onEventDispatcher.invoke(
                            HomeContract.Intent.MoveToAddScreen
                        )
                    },
                    onClickDelete = { data ->
                        sellerName.value = data.sellerName
                        id.value = data.id
                        sellerPass.value = data.password
                        showDialog.value = true
                    },
                    onEdit = { data ->

                        sellerName.value = data.sellerName
                        id.value = data.id
                        sellerPass.value = data.password
                        showEditDialog.value = true
                    }
                )
            }
        }
        Button(
            onClick = {
                onEventDispatcher(HomeContract.Intent.MoveToAddScreen)
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC107)
            ), shape = RoundedCornerShape(100),
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier
                    .size(40.dp),
                tint = Color.Black
            )
        }
    }
    if (showEditDialog.value) EditDialog(
        SellerData(id.value, sellerName.value, sellerPass.value),
        onClickEdit = {
            onEventDispatcher.invoke(
                HomeContract.Intent.EditSeller(
                    id.value, it.sellerName, it.password
                )
            )
            showEditDialog.value = false

        },
        onClickCancel = { showEditDialog.value = false },
    )

}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun MainScreenPreview() {
    MainScreenContent(uiState = mutableStateOf(HomeContract.UIState()), onEventDispatcher = {})
}