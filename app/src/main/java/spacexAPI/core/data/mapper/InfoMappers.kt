package spacexAPI.core.data.mapper

import spacexAPI.core.data.remote.dto.CompanyInfoAPI
import spacexAPI.core.data.remote.dto.companyInfo.Headquarters
import spacexAPI.core.data.remote.dto.companyInfo.Links
import spacexAPI.core.domain.model.companyInfo.CompanyInfoModel
import spacexAPI.core.domain.model.companyInfo.HeadquartersModel
import spacexAPI.core.domain.model.companyInfo.LinksModel


fun CompanyInfoAPI.toDomain(): CompanyInfoModel {
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

fun Headquarters.toDomain(): HeadquartersModel {
    return HeadquartersModel(
        address = address,
        city = city,
        state = state
    )
}

fun Links.toDomain(): LinksModel {
    return LinksModel(
        website = website,
        flickr = flickr,
        twitter = twitter,
        elon_twitter = elon_twitter
    )
}