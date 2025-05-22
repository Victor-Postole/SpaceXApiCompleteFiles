package spacexAPI.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import spacexAPI.core.data.mapper.toDomain
import spacexAPI.core.data.remote.api.SpaceXApi
import spacexAPI.core.domain.model.companyInfo.CompanyInfoModel
import spacexAPI.core.domain.model.launchesModels.LaunchModel
import spacexAPI.core.domain.repository.SpacexAPIRepository
import spacexAPI.util.Resource

class SpacexAPIRepositoryImpl(
    private val api: SpaceXApi
) : SpacexAPIRepository {

    override suspend fun getCompanyDetails(): Flow<Resource<CompanyInfoModel>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = api.getCompanyInfo()
            if (response != null) {
                emit(Resource.Success(response.toDomain()))
            } else {
                emit(Resource.Error("Company info is null"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.message ?: "Unknown error"}"))
        }
    }

    override suspend fun getAllLaunches(): Flow<Resource<List<LaunchModel>>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = api.getCompanyAllLaunches() // List<LaunchDTO>
            val launches = response?.map { it.toDomain() }
            emit(Resource.Success(launches))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }



}