package spacexAPI.core.data.remote.api

import retrofit2.http.GET
import spacexAPI.core.data.remote.dto.CompanyInfoDTO
import spacexAPI.core.data.remote.dto.launches.LaunchesDTO


interface SpaceXApi {

    @GET("info")
    suspend fun getCompanyInfo(): CompanyInfoDTO?

    @GET("launches")
    suspend fun getCompanyAllLaunches(): List<LaunchesDTO>?

    companion object {
        const val BASE_URL = "https://api.spacexdata.com/v3/"
    }
}