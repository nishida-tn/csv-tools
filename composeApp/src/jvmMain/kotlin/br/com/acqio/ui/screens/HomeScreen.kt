package br.com.acqio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

        OutlinedTextField(
            value = uiState.inputText,
            onValueChange = { viewModel.updateInputText(it) },
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = {
                viewModel.loadCsv()
            }) {
                Text(Strings.LOAD_CSV)
            }

            Button(onClick = {
                viewModel.generateCsv()
            }) {
                Text(Strings.GENERATE_CSV)
            }
        }

        if (uiState.message.isNotEmpty()) {
            Text(uiState.message)
        }
    }
}

