package spacexAPI.core.data.repository

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import spacexAPI.core.domain.repository.PermissionsRepository

class PermissionRepositoryImpl : PermissionsRepository {


    override fun isInternetPermissionGranted(applicationContext: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            applicationContext,
            android.Manifest.permission.INTERNET
        ) == PackageManager.PERMISSION_GRANTED
    }

}