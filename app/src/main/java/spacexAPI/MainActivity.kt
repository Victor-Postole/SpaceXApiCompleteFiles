package spacexAPI

import MainScreen
import android.Manifest
import android.Manifest.permission.INTERNET
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import org.koin.android.ext.android.inject
import spacexAPI.core.domain.repository.PermissionsRepository


class MainActivity : ComponentActivity() {


    private val permissionsRepository: PermissionsRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (!permissionsRepository.isInternetPermissionGranted(this)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.INTERNET),
                100
            )
        }

        setContent {
            MainScreen()
        }
    }
}






















