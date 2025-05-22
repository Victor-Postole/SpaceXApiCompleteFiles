package spacexAPI.core.domain.repository

import android.content.Context

interface PermissionsRepository {
    fun isInternetPermissionGranted(applicationContext: Context) : Boolean
}