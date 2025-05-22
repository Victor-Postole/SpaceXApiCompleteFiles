package spacexAPI.core.di


import spacexAPI.core.presentation.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { MainScreenViewModel(get()) }

}