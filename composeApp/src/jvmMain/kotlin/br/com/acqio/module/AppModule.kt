package br.com.acqio.module

import br.com.acqio.viewmodels.HomeViewModel
import br.com.acqio.viewmodels.HomeViewModelImpl
import org.koin.dsl.module

val appModule = module {
    single<HomeViewModel> { HomeViewModelImpl() }
}