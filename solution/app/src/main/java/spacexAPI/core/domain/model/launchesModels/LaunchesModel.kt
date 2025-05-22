package spacexAPI.core.domain.model.launchesModels


data class LaunchModel(
    val flightNumber: Int,
    val missionName: String,
    val launchYear: String,
    val launchDateUtc: String,
    val rocket: RocketModel,
    val launchSuccess: Boolean?,
    val details: String?,
    val links: LinksModel
)

data class RocketModel(
    val rocketId: String,
    val rocketName: String,
    val rocketType: String
)

data class LinksModel(
    val missionPatch: String?,
    val articleLink: String?,
    val videoLink: String?,
    val wikipedia: String?
)
