package br.com.acqio.viewmodels

import androidx.lifecycle.ViewModel
import br.com.acqio.extentions.toFormatterString
import br.com.acqio.ui.state.HomeUIState
import br.com.acqio.util.Strings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.io.File
import java.time.LocalDateTime
import javax.swing.JFileChooser

class HomeViewModelImpl : ViewModel(), HomeViewModel {
    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    override val uiState: StateFlow<HomeUIState> get() = _uiState

    override fun loadCsv() {
        val chooser = JFileChooser()
        val result = chooser.showOpenDialog(null)
        if (result == JFileChooser.APPROVE_OPTION) {
            val file = chooser.selectedFile
            if (file.extension.lowercase() == "csv") {
                _uiState.update {
                    it.copy(
                        inputText = file.readText(),
                        message = "CSV carregado: ${file.absolutePath}"
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        message = Strings.SELECT_FILE_CSV
                    )
                }
            }
        }
    }

    override fun updateInputText(value: String) {
        _uiState.update {
            it.copy(
                inputText = value
            )
        }
    }

    override fun updateMessage(value: String) {
        _uiState.update {
            it.copy(
                message = value
            )
        }
    }

    override fun generateCsv() {
        val numbers = parseSerialNumbers(_uiState.value.inputText)

        if (numbers.isNotEmpty()) {
            try {
                val chooser = JFileChooser().apply {
                    dialogTitle = Strings.SAVE_CSV
                    fileSelectionMode = JFileChooser.FILES_ONLY

                    val localDateTime = LocalDateTime.now()
                    selectedFile = File("${localDateTime.toFormatterString()}_output.csv")
                }

                val result = chooser.showSaveDialog(null)
                if (result == JFileChooser.APPROVE_OPTION) {
                    var file = chooser.selectedFile

                    if (!file.name.lowercase().endsWith(".csv")) {
                        file = File(file.parentFile, "${file.name}.csv")
                    }

                    file.printWriter().use { out ->
                        out.println("identifier,name")
                        numbers.forEach { number ->
                            out.println("$number,$number")
                        }
                    }
                    _uiState.update {
                        it.copy(
                            inputText = file.readText(),
                            message = "Arquivo gerado: ${file.absolutePath}"
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            message = Strings.CANCELED_BY_USER
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        message = "Erro ao gerar arquivo: ${e.message}"
                    )
                }
            }
        } else {
            _uiState.update {
                it.copy(
                    message = Strings.EMPTY_LIST
                )
            }
        }
    }
}

private fun parseSerialNumbers(input: String): List<String> {
    val lines = input.lines().filter { it.isNotBlank() }

    return if (lines.firstOrNull()?.contains("identifier", ignoreCase = true) == true) {
        lines.drop(1)
            .mapNotNull { line ->
                line.split(";").getOrNull(1)?.trim()
            }
    } else {
        lines.mapNotNull { line ->
            val parts = line.split(',', ';', '\t', ' ').map { it.trim() }.filter { it.isNotEmpty() }
            parts.firstOrNull { it.all { c -> c.isDigit() } }
        }
    }
        .filter { it.isNotEmpty() }
        .distinct()
        .sorted()
}