package com.darek.kbembed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.darek.kbcore.KBCore
import com.darek.kbembed.ui.screens.drafts.DraftsScreen
import com.darek.kbembed.ui.screens.drafts.DraftsScreenViewModel
import com.darek.kbembed.ui.screens.home.HomeScreen
import com.darek.kbembed.ui.screens.home.HomeScreenViewModel
import com.darek.kbembed.ui.screens.login.LoginScreen
import com.darek.kbembed.ui.screens.login.LoginScreenViewModel
import com.darek.kbembed.ui.screens.posts.PostsScreen
import com.darek.kbembed.ui.screens.posts.PostsScreenViewModel
import com.darek.kbembed.utils.ComposeViewModel.getViewModel

@Composable
fun MainNavHost(
    dataSource: KBCore,
    navigator: Navigator
) {

    val navHostController: NavHostController = rememberNavController()

    val homeScreenViewModel = dataSource.getViewModel(HomeScreenViewModel::class)
    val draftsScreenViewModel = dataSource.getViewModel(DraftsScreenViewModel::class)
    val postsScreenViewModel = dataSource.getViewModel(PostsScreenViewModel::class)
    val loginScreenViewModel = dataSource.getViewModel(LoginScreenViewModel::class)

    NavHost(
        navController = navHostController,
        startDestination = Destination.HOME.route
    ) {
        composable(Destination.HOME.route) {
            val homeScreenState by remember(homeScreenViewModel) { homeScreenViewModel.homeScreenState }.collectAsState()
            HomeScreen(
                homeScreenState = homeScreenState,
                onNotLoggedIn = { navigator.navigateHome() },
                onNavigatePosts = { navigator.navigateToPosts() },
                onNavigateDrafts = { navigator.navigateToDrafts() },
                onLogout = {
                    homeScreenViewModel.logout()
                    navigator.navigateToLogin()
                }
            )
        }
        composable(Destination.DRAFTS.route) {
            val draftsScreenState by remember(draftsScreenViewModel) { draftsScreenViewModel.draftsScreenState }.collectAsState()
            DraftsScreen(
                draftsScreenState = draftsScreenState,
                onGoBack = { navigator.navigateHome() }
            )
        }
        composable(Destination.POSTS.route) {
            val postScreenState by remember(postsScreenViewModel) { postsScreenViewModel.postsScreenState }.collectAsState()
            PostsScreen(
                postScreenState = postScreenState,
                onGoBack = { navigator.navigateHome() }
            )
        }
        composable(Destination.LOGIN.route) {
            val loginScreenState by remember(loginScreenViewModel) { loginScreenViewModel.loginScreenState }.collectAsState()
            LoginScreen(
                loginScreenState = loginScreenState,
                onAlreadyLoggedIn = { navigator.navigateHome() },
                onPasswordSubmit = loginScreenViewModel::providePassword
            )
        }
    }

    val currentDestination by remember(navigator) { navigator.currentDestination }.collectAsState()
    LaunchedEffect(currentDestination){
        navHostController.navigate(currentDestination)
    }

}