package spacexAPI.core.di

import org.koin.dsl.module
import spacexAPI.core.data.repository.PermissionRepositoryImpl
import spacexAPI.core.domain.repository.PermissionsRepository

val permissionModule = module {
    single<PermissionsRepository> { PermissionRepositoryImpl() }
}