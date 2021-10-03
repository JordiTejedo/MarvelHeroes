package com.jorditejedo.marvelheroes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.jorditejedo.marvelheroes.db.entity.Character
import com.jorditejedo.marvelheroes.repository.CharacterRepository
import com.jorditejedo.marvelheroes.utils.NetworkResourceStatus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var characterRepository : CharacterRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        characterRepository.getAllCharactersFromServerAndStoreInLocalDB().observe(this) { characterList ->
            when (characterList.status) {
                NetworkResourceStatus.LOADING -> {

                }
                NetworkResourceStatus.SUCCESS -> {

                }
                NetworkResourceStatus.ERROR -> {

                }
            }
        }

        setContent {
            val navController = rememberNavController()
            MainNavigationHost(navController)
        }
    }
}

@Composable
fun MainNavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.ListScreen.route
    ) {
        composable(
            route = Destination.ListScreen.route
        ) {
            ListScreen(navController)
        }
        composable(
            route = Destination.DetailsScreen.route,
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.IntType
                }
            )
        ) {
            val characterId = it.arguments?.getInt("characterId")
            DetailsScreen(characterId!!)
        }
    }
}