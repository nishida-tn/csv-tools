package br.com.acqio.composables

import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonDefault(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tip: String? = null,
) {
    TooltipArea(
        tooltip = {
            Text(tip ?: "")
        },
        delayMillis = 300,
        tooltipPlacement = TooltipPlaceent.CursorPoint(offset = 16.dp)
    ) {
        Button(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            onClick = {
                onClick()
            }
        ) {
            Text(text)
        }
    }
}
