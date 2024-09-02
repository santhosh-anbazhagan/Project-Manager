package com.alienspace.projectmanager.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alienspace.projectmanager.R


@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
@Composable
fun Home(modifier: Modifier = Modifier) {
    var currentDestination by rememberSaveable { mutableStateOf(Destination.Chats) }
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            for (destinations in Destination.entries) {
                val selected = currentDestination.route == destinations.route
                item(
                    selected = selected,
                    onClick = {
                        currentDestination = destinations
                    },
                    icon = {
                        Icon(
                            imageVector = destinations.imageVector,
                            contentDescription = stringResource(destinations.label),
                        )
                    },
                    label = {
                        Text(text = stringResource(destinations.label))
                    },
                    alwaysShowLabel = false
                    )
            }

        }) {
        HomeContent(screen = currentDestination)
    }
}

@Composable
private fun HomeContent(screen:Destination) {
    Scaffold(
        topBar = { HomeBar() }
    ) { innerPad ->
        Column(
            modifier = Modifier
                .padding(innerPad)
                .padding(
                    horizontal = 16.dp,
                )
        ) {
            Column {
                Text(
                    text = "Hello Aliens !",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Good Morning",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )

            }

            Text(
                text = "Hello From ${stringResource(screen.label)} Screen",
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            BadgedBox(modifier = Modifier.padding(8.dp), badge = {
                Badge {
                    Text(text = "1")
                }
            }) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = "Notifications"
                    )
                }
            }

        },
    )
}

private enum class Destination(
    val route: String,
    @StringRes val label: Int,
    val imageVector: ImageVector,
) {
    Timeline(
        route = "timeline",
        label = R.string.timeline,
        imageVector = Icons.Outlined.MailOutline,
    ),
    Chats(
        route = "chats",
        label = R.string.chats,
        imageVector = Icons.Outlined.AccountCircle,
    ),
    Settings(
        route = "settings",
        label = R.string.settings,
        imageVector = Icons.Outlined.Settings,
    ),
}