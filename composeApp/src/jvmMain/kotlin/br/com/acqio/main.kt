package br.com.acqio

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import br.com.acqio.viewmodels.HomeViewModel
import br.com.acqio.viewmodels.HomeViewModelImpl
import convertcsv.composeapp.generated.resources.Res
import convertcsv.composeapp.generated.resources.icon
import org.jetbrains.compose.resources.painterResource
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun main() = application {
    val state = rememberWindowState(position = WindowPosition.Aligned(Alignment.Center))

    startKoin {
        modules(
            module {
                single<HomeViewModel> { HomeViewModelImpl() }
            }
        )
    }

    Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "CSV TOOLS",
        icon = painterResource(Res.drawable.icon)
    ) {
        App()
    }
}