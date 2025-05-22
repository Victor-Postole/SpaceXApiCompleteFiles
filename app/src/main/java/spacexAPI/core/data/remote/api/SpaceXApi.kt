package spacexAPI.core.data.remote.api

import retrofit2.http.GET
import spacexAPI.core.data.remote.dto.CompanyInfoAPI
import spacexAPI.core.data.remote.dto.launches.LaunchesAPI


interface SpaceXApi {

    @GET("info")
    suspend fun getCompanyInfo(): CompanyInfoAPI?

    @GET("launches")
    suspend fun getCompanyAllLaunches(): List<LaunchesAPI>?

    companion object {
        const val BASE_URL = "https://api.spacexdata.com/v3/"
    }
}