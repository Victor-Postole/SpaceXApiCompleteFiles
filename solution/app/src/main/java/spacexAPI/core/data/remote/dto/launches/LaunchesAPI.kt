package spacexAPI.core.data.remote.dto.launches

data class LaunchesAPI(
    val flight_number: Int,
    val mission_name: String,
    val launch_year: String,
    val launch_date_utc: String,
    val rocket: RocketDTO,
    val launch_success: Boolean?,
    val details: String?,
    val links: LinksDTO
)

// RocketDTO.kt
data class RocketDTO(
    val rocket_id: String,
    val rocket_name: String,
    val rocket_type: String
)

// LinksDTO.kt
data class LinksDTO(
    val mission_patch: String?,
    val article_link: String?,
    val video_link: String?,
    val wikipedia: String?
)
