
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindera.rocketscience.R
import org.koin.androidx.compose.koinViewModel
import spacexAPI.core.presentation.MainScreenViewModel
import spacexAPI.core.domain.usecase.CompanyInfoFormatter
import spacexAPI.core.presentation.uiComponents.LaunchCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val state by viewModel.state
    var showSortDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ){

                        Spacer(
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            textAlign = TextAlign.Center,
                            text = "SpaceX"
                        )

                        Spacer(
                            modifier = Modifier.weight(1f)
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.ic_outline_filter),
                            contentDescription = "Filter",
                            modifier = Modifier
                                .height(50.dp)
                                .clickable { showSortDialog = true }
                        )

                    }

                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }

                state.message != null -> {
                    Text(
                        text = "Error: ${state.message}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                else -> {
                    val sentence = CompanyInfoFormatter.fullSentence(
                        name = state.companyName,
                        founder = state.founderName,
                        year = state.foundedYear,
                        employees = state.employeesCount,
                        launchSites = state.launchSitesCount,
                        valuation = state.valuation
                    )

                    Text(
                        text = sentence,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp)
                    )

                    LazyColumn {
                        items(state.launches) { launch ->
                            LaunchCard(launch = launch)
                        }
                    }
                }
            }
        }
    }

    if (showSortDialog) {
        var selectedYear by remember { mutableStateOf<String?>(null) }
        val allYears = state.launches.mapNotNull { it.launchYear }.distinct().sorted()

        var successFilter by remember { mutableStateOf("All") }
        var sortAsc by remember { mutableStateOf(true) }

        AlertDialog(
            onDismissRequest = {
                selectedYear = null
                successFilter = "All"
                sortAsc = true
                showSortDialog = false
            },
            title = { Text("Filter launches") },
            text = {
                Column {
                    Text("Launch Year:")
                    DropdownSelector(
                        options = allYears,
                        selected = selectedYear,
                        onSelected = { selectedYear = it }
                    )

                    Spacer(Modifier.height(12.dp))

                    Text("Launch Success:")
                    DropdownSelector(
                        options = listOf("All", "Success", "Fail"),
                        selected = successFilter,
                        onSelected = { successFilter = it }
                    )

                    Spacer(Modifier.height(12.dp))

                    Text("Sort by date:")

                    DropdownSelector(
                        options = listOf("ASC", "DESC"),
                        selected = if (sortAsc) "ASC" else "DESC",
                        onSelected = { sortAsc = it == "ASC" }
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.applyLaunchFilters(
                        years = selectedYear?.let { listOf(it) } ?: emptyList(),
                        success = successFilter,
                        asc = sortAsc
                    )
                    showSortDialog = false
                }) {
                    Text("Apply")
                }
            },
            dismissButton = {

                TextButton(onClick = {
                    selectedYear = null
                    successFilter = "All"
                    sortAsc = true
                    showSortDialog = false
                }) {
                    Text("Cancel")
                }

            }
        )
    }
}

@Composable
fun DropdownSelector(
    options: List<String>,
    selected: String?,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selected ?: "Select")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

