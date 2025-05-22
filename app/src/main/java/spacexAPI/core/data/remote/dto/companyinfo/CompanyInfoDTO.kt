package spacexAPI.core.data.remote.dto

import spacexAPI.core.data.remote.dto.companyinfo.HeadquartersDTO
import spacexAPI.core.data.remote.dto.companyinfo.CompanyLinksDTO

data class CompanyInfoDTO(
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
    val headquarters: HeadquartersDTO,
    val links: CompanyLinksDTO,
    val vehicles: Int
)