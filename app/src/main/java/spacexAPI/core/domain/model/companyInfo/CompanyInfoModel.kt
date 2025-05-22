package spacexAPI.core.domain.model.companyInfo


data class CompanyInfoModel(
    val ceo: String,
    val coo: String,
    val cto: String,
    val cto_propulsion: String,
    val employees: Int,
    val founded: Int,
    val founder: String,
    val headquarters: HeadquartersModel,
    val launch_sites: Int,
    val links: LinksModel,
    val name: String,
    val summary: String,
    val test_sites: Int,
    val valuation: Long,
    val vehicles: Int
)