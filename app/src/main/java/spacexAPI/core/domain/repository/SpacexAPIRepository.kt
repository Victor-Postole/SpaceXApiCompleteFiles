package spacexAPI.core.domain.repository

import kotlinx.coroutines.flow.Flow
import spacexAPI.core.domain.model.companyInfo.CompanyInfoModel
import spacexAPI.core.domain.model.launchesModels.LaunchModel
import spacexAPI.util.Resource

interface SpacexAPIRepository {
    suspend fun getCompanyDetails() : Flow<Resource<CompanyInfoModel>>

    suspend fun getAllLaunches(): Flow<Resource<List<LaunchModel>>>
}