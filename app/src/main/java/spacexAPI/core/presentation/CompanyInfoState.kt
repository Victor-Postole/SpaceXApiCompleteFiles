package spacexAPI.core.presentation

import spacexAPI.core.domain.model.launchesModels.LaunchModel


data class CompanyInfoState(
    val companyName: String? = null,
    val founderName: String? = null,
    val message: String? = null,
    val foundedYear: Int? = null,
    val employeesCount: Int? = null,
    val launchSitesCount: Int? = null,
    val valuation: Long? = null,
    val launches: List<LaunchModel> = emptyList(),
    val isLoading: Boolean = false,
)