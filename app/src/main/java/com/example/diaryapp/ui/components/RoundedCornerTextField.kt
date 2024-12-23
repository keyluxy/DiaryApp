package com.example.diaryapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaryapp.ui.theme.Purple40

@Composable
fun RoundedCornerTextField(
    maxLines: Int = 1,
    singleLine: Boolean = true,
    text: String,
    label: String,
    placeholder: String? = null,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null
) {
    Column {
        TextField(
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Purple40, RoundedCornerShape(20.dp)),

            label = {
                Text(text = label, color = Color.Gray)
            },

            placeholder = {
                if (placeholder != null) {
                    Text(text = placeholder, color = Color.Gray)
                }
            },

            singleLine = singleLine,
            maxLines = maxLines
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}