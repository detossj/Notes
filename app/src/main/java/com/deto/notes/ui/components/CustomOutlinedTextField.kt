package com.deto.notes.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


@Composable
fun CustomOutlinedTextField(value: String, onValueChange: (String) -> Unit, placeholder: String, fontSize: Int) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                fontSize = fontSize.sp,
                color = Color.Gray
            ) },
        textStyle = TextStyle(
            fontSize = fontSize.sp,
            color = Color.White
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        singleLine = false,
        maxLines = Int.MAX_VALUE
    )
}