package spacexAPI.core.data.repository

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import spacexAPI.core.domain.repository.PermissionsRepository

class PermissionRepositoryImpl : PermissionsRepository {


    private val INTERNET_PERMISSION = arrayOf(
        android.Manifest.permission.INTERNET
    )

    override fun isInternetPermissionGranted(applicationContext: Context): Boolean {
        return INTERNET_PERMISSION.all { permission ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

}