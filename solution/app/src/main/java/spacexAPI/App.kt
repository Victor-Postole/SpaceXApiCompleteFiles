package spacexAPI

import android.app.Application
import com.ag_apps.spending_tracker.core.di.coreModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import spacexAPI.core.di.permissionModule
import spacexAPI.core.di.viewModelModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                permissionModule,
                coreModule,
                viewModelModule
            )
        }
    }
}