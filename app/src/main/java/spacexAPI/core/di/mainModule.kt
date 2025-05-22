package com.ag_apps.spending_tracker.core.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import spacexAPI.core.data.remote.api.SpaceXApi
import spacexAPI.core.domain.repository.PermissionsRepository
import spacexAPI.core.domain.repository.SpacexAPIRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import spacexAPI.core.data.repository.PermissionRepositoryImpl
import spacexAPI.core.data.repository.SpacexAPIRepositoryImpl
import spacexAPI.core.domain.usecase.LaunchDateFormatterUseCase

val coreModule = module {
    
    single{
        Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(SpaceXApi.BASE_URL)
            .build()
            .create(SpaceXApi::class.java)
    }


    single { LaunchDateFormatterUseCase() }

    singleOf(::SpacexAPIRepositoryImpl).bind<SpacexAPIRepository>()
    singleOf(::PermissionRepositoryImpl).bind<PermissionsRepository>()
}

