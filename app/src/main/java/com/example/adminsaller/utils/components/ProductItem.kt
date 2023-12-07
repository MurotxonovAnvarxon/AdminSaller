package com.example.adminsaller.utils.components

import android.view.Display.Mode
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminsaller.R
import com.example.adminsaller.data.model.ProductsData
import com.example.adminsaller.data.model.SellerData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductItem(
    model: ProductsData,
    onClickDelete: (ProductsData) -> Unit,
    onClick: () -> Unit = {},
    onEdit: (ProductsData) -> Unit = {}
) {
    
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 15.dp)
            .fillMaxWidth()
            .height(174.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0x33D1D1D1))
                .combinedClickable(onClick = { onClick() }, onLongClick = { onClickDelete(model) })
        ) {
            Column {


                Image(
                    painter = painterResource(id = R.drawable.boxes),
                    contentDescription = "",
                    modifier = Modifier
                        .size(64.dp)
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                Image(painter = painterResource(id = R.drawable.edit),
                    contentDescription = "edit",
                    modifier = Modifier
                        .padding(start = 40.dp)
                        .weight(1f)
                        .size(40.dp)
                        .clickable {
                            onEdit(model)
                        })

            }
            Spacer(modifier = Modifier.size(15.dp))


            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
            ) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        text = "productName: ${model.productName}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "count: ${model.productCount}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "productInitialPrice: ${model.productInitialPrice}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "productSellingPrice: ${model.productSellingPrice}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "productIsValid: ${model.productIsValid}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    ProductItem(model = ProductsData("", "", 1,1,1,true,""), onClickDelete = { }) {

    }
}

