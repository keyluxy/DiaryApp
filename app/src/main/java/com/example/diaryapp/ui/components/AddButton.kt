package com.example.diaryapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.diaryapp.ui.theme.Purple40

@Composable
fun AddButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier.fillMaxWidth(0.5f),
        colors = ButtonDefaults.buttonColors(containerColor = Purple40)
    ) {
        Text(text = text)
    }
}