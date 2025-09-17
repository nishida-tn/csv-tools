package br.com.acqio.viewmodels

import br.com.acqio.ui.state.HomeUIState
import kotlinx.coroutines.flow.StateFlow

interface HomeViewModel {
    val uiState: StateFlow<HomeUIState>
    fun loadCsv()

    fun updateInputText(value: String)
    fun updateMessage(value: String)
    fun generateCsv()
}