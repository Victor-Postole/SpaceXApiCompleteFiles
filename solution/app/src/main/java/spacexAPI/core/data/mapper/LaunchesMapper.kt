package spacexAPI.core.data.mapper

import spacexAPI.core.data.remote.dto.launches.LaunchesAPI
import spacexAPI.core.data.remote.dto.launches.LinksDTO
import spacexAPI.core.data.remote.dto.launches.RocketDTO
import spacexAPI.core.domain.model.launchesModels.LaunchModel
import spacexAPI.core.domain.model.launchesModels.LinksModel
import spacexAPI.core.domain.model.launchesModels.RocketModel

fun RocketDTO.toDomain(): RocketModel = RocketModel(
    rocketId = this.rocket_id,
    rocketName = this.rocket_name,
    rocketType = this.rocket_type
)

fun LinksDTO.toDomain(): LinksModel = LinksModel(
    missionPatch = this.mission_patch,
    articleLink = this.article_link,
    videoLink = this.video_link,
    wikipedia = this.wikipedia
)

fun LaunchesAPI.toDomain(): LaunchModel = LaunchModel(
    flightNumber = this.flight_number,
    missionName = this.mission_name,
    launchYear = this.launch_year,
    launchDateUtc = this.launch_date_utc,
    rocket = this.rocket.toDomain(),
    launchSuccess = this.launch_success,
    details = this.details,
    links = this.links.toDomain()
)