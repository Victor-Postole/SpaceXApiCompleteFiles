package spacexAPI.core.data.mapper

import spacexAPI.core.data.remote.dto.CompanyInfoDTO
import spacexAPI.core.data.remote.dto.companyinfo.HeadquartersDTO
import spacexAPI.core.data.remote.dto.companyinfo.CompanyLinksDTO
import spacexAPI.core.domain.model.companyinfo.CompanyInfoModel
import spacexAPI.core.domain.model.companyinfo.HeadquartersModel
import spacexAPI.core.domain.model.companyinfo.LinksModel


fun CompanyInfoDTO.toDomain(): CompanyInfoModel {
    return CompanyInfoModel(
        ceo = ceo,
        coo = coo,
        cto = cto,
        cto_propulsion = cto_propulsion,
        employees = employees,
        founded = founded,
        founder = founder,
        headquarters = headquarters.toDomain(),
        launch_sites = launch_sites,
        links = links.toDomain(),
        name = name,
        summary = summary,
        test_sites = test_sites,
        valuation = valuation,
        vehicles = vehicles
    )
}

fun HeadquartersDTO.toDomain(): HeadquartersModel {
    return HeadquartersModel(
        address = address,
        city = city,
        state = state
    )
}

fun CompanyLinksDTO.toDomain(): LinksModel {
    return LinksModel(
        website = website,
        flickr = flickr,
        twitter = twitter,
        elon_twitter = elon_twitter
    )
}