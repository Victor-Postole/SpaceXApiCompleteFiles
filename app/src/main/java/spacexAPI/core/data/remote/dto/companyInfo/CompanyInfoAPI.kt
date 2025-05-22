package spacexAPI.core.data.remote.dto

import spacexAPI.core.data.remote.dto.companyInfo.Headquarters
import spacexAPI.core.data.remote.dto.companyInfo.Links

data class CompanyInfoAPI(
    val name: String,
    val founder: String,
    val founded: Int,
    val employees: Int,
    val launch_sites: Int,
    val test_sites: Int,
    val ceo: String,
    val cto: String,
    val coo: String,
    val cto_propulsion: String,
    val valuation: Long,
    val summary: String,
    val headquarters: Headquarters,
    val links: Links,
    val vehicles: Int
)