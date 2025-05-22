package spacexAPI.core.presentation.uiComponents

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mindera.rocketscience.R
import spacexAPI.core.domain.model.launchesModels.LaunchModel
import spacexAPI.core.domain.usecase.LaunchDateFormatterUseCase
import org.koin.androidx.compose.get

@Composable
fun LaunchCard(
    launch: LaunchModel,
    formatter: LaunchDateFormatterUseCase = get()
) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { showDialog = true },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {

            Image(
                painter = rememberAsyncImagePainter(launch.links.missionPatch),
                contentDescription = "Mission Patch",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 12.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text("Mission: ${launch.missionName}", fontWeight = FontWeight.Bold)

                val formattedDate = formatter.formatDate(launch.launchDateUtc)

                Text("Date/time: $formattedDate")

                Text("Rocket: ${launch.rocket.rocketName} / ${launch.rocket.rocketType}")

                val daysDiff = formatter.calculateDaysFromNow(launch.launchDateUtc)
                val label = if (daysDiff >= 0) "from" else "since"

                Text("Days: ${kotlin.math.abs(daysDiff)} $label now")
            }
            Icon(
                painter = painterResource(id = if (launch.launchSuccess == true) R.drawable.ic_check else R.drawable.ic_close ),
                contentDescription =  if (launch.launchSuccess == true) "Success" else "Failure",
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Open external link") },
            text = {
                Text("Choose where to go for more info about this mission.")
            },
            confirmButton = {


                TextButton(onClick = {
                    openUrl(context, launch.links.articleLink)
                    showDialog = false
                }) {
                    Text("Article")
                }

            },
            dismissButton = {
                Row {
                    TextButton(onClick = {
                        openUrl(context, launch.links.wikipedia)
                        showDialog = false
                    }) {
                        Text("Wikipedia")
                    }

                    Spacer(Modifier.width(8.dp))

                    TextButton(onClick = {
                        openUrl(context, launch.links.videoLink)
                        showDialog = false
                    }) {
                        Text("Video")
                    }
                }
            }
        )
    }
}




fun openUrl(context: Context, url: String?) {
    if (!url.isNullOrBlank()) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
