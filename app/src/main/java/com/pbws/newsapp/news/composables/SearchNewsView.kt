package com.pbws.newsapp.news.composables

import OrangeColor
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.newsapp.R

@Composable
fun SearchNewsView(
    searchValue:String,
    onSearchValueChanged:(value:String)->Unit,
    onSearchIconClick:(searchValue:String)->Unit) {
    OutlinedTextField(
        textStyle = TextStyle(color = OrangeColor, fontSize = 16.sp),
        shape = RoundedCornerShape(32.dp),
        colors = OutlinedTextFieldDefaults
            .colors(
                disabledContainerColor = Color.White,
                focusedContainerColor = Color.White ,
                errorContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                errorTextColor = Color.Black,
                unfocusedSupportingTextColor = Color.Black,
                focusedSupportingTextColor = Color.Black,
                errorSupportingTextColor = Color.Black,
                disabledSupportingTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                errorBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            ),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(32.dp)),
        value =searchValue ,
        placeholder = {
            Text(
                text = "Search News...",
                color = Color.LightGray
            )
        },
        onValueChange ={onSearchValueChanged(it)},
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search ,
                contentDescription ="Search Icon",
                tint = Color.LightGray
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearchIconClick(searchValue)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_ic),
                    contentDescription = "Search Now Icon",
                    tint = OrangeColor
                )
            }
        }

    )
}