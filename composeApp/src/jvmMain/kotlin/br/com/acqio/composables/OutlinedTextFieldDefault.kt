package br.com.acqio.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedTextFieldDefault(
    modifier: Modifier = Modifier,
    text: String,
    onChange: (String) -> Unit = {},
    enabled: Boolean = true,
) {
    OutlinedTextField(
        value = text,
        enabled = enabled,
        onValueChange = { onChange(it) },
        modifier = modifier.fillMaxWidth().height(200.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
        )
    )
}