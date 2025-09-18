package br.com.acqio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.acqio.composables.ButtonDefault
import br.com.acqio.composables.OutlinedTextFieldDefault
import br.com.acqio.util.Strings
import br.com.acqio.viewmodels.HomeViewModel
import org.koin.java.KoinJavaComponent.getKoin

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getKoin().get()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(Strings.PASTE_OR_LOAD_CSV)

        OutlinedTextFieldDefault(
            text = uiState.inputText,
            onChange = { viewModel.updateInputText(it) }
        )

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ButtonDefault(
                onClick = { viewModel.loadCsv() },
                text = Strings.LOAD_CSV
            )

            ButtonDefault(
                onClick = { viewModel.generateCsv() },
                text = Strings.GENERATE_CSV
            )
        }

        if (uiState.message.isNotEmpty()) {
            Text(uiState.message)
        }

        OutlinedTextFieldDefault(
            text = uiState.resultCsv,
        )
    }
}

