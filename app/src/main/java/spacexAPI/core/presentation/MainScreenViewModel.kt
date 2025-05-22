package spacexAPI.core.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import spacexAPI.core.domain.model.launchesModels.LaunchModel
import spacexAPI.core.domain.repository.SpacexAPIRepository
import spacexAPI.util.Resource

class MainScreenViewModel(
    private val spacexAPIRepository: SpacexAPIRepository,
) : ViewModel() {

    var selectedYear by mutableStateOf<String?>(null)
    var successFilter by mutableStateOf("All")
    var sortAsc by mutableStateOf(true)
    val allLaunchYears: List<String> get() = originalLaunches.mapNotNull { it.launchYear }.distinct().sorted()

    private val _state = mutableStateOf(CompanyInfoState())
    val state: State<CompanyInfoState> = _state

    private var originalLaunches: List<LaunchModel> = emptyList()

    init {

        viewModelScope.launch {
            getCompanyDetails()
            getAllLaunches()
        }
    }

    private suspend fun getCompanyDetails() {
            spacexAPIRepository.getCompanyDetails().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    }

                    is Resource.Success -> {
                        val company = result.data
                        _state.value = _state.value.copy(
                            companyName = company?.name,
                            founderName = company?.founder,
                            foundedYear = company?.founded,
                            employeesCount = company?.employees,
                            launchSitesCount = company?.launch_sites,
                            valuation = company?.valuation,
                            isLoading = false,
                            message = null
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            message = result.message ?: "Unexpected error",
                            isLoading = false
                        )
                    }
                }
            }

    }

    private suspend fun getAllLaunches() {
        spacexAPIRepository.getAllLaunches().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = result.isLoading)
                }

                is Resource.Success -> {
                    val launches = result.data ?: emptyList()
                    originalLaunches = launches
                    _state.value = _state.value.copy(
                        launches = launches,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        message = result.message,
                        isLoading = false
                    )
                }
            }
        }
    }


    fun applyLaunchFilters(years: List<String>, success: String, asc: Boolean) {
        val filtered = originalLaunches.filter { launch ->
            val matchesYear = years.isEmpty() || years.contains(launch.launchYear)
            val matchesSuccess = when (success) {
                "Success" -> launch.launchSuccess == true
                "Fail" -> launch.launchSuccess == false
                else -> true
            }
            matchesYear && matchesSuccess
        }

        val sorted = if (asc) filtered.sortedBy { it.launchDateUtc }
        else filtered.sortedByDescending { it.launchDateUtc }

        _state.value = _state.value.copy(launches = sorted)
    }
}
