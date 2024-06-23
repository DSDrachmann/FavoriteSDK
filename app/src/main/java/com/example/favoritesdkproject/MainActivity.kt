package com.example.favoritesdkproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.Dandd.favorite.internal.database.FavoriteDatabaseRepository
import com.Dandd.favorite.internal.database.FavoriteRoomDatabase
import com.Dandd.favorite.internal.database.favoriteDatabaseProvider
import com.Dandd.favorite.internal.database.model.FavoriteEntity
import com.example.favoritesdkproject.ui.theme.FavoriteSDKProjectTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var database: FavoriteRoomDatabase
    private lateinit var favoriteRepository: FavoriteDatabaseRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /***
         *
         * SUPER IMPORTANT!!! The develop branch has the module attached in the settings.gradle file!!!
         * REMEMBER to //include(":app") before pushing to any branch that is only module functionality (so all branches except for appProject branch
         * ALSO, after the push, make a PR into the app-project branch so its up to date!!
         *
         * IF SITTING IN A BRANCH DERIVED FROM DEVELOP AND WANTING TO TEST DIRECTLY, SIMPLY:
         * RE-ADD include(":app")
         * BUILD -> CLEAN PROJECT
         * BUILD -> REBUILD PROJECT
         *
         * (the 3 above steps is also neccesary if wanting to build and execute on a new computer that has imported the project)
         *
         */

        // Initialize Room database and repository
        database = favoriteDatabaseProvider.getInstance(this)
        favoriteRepository = FavoriteDatabaseRepository(database)

        val currentDBPath = getDatabasePath("favorite_clock_database").absolutePath

        setContent {
            FavoriteSDKProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        lifecycleScope.launch {
            val model = FavoriteEntity(
                accountName = "brain",
                entityId = "3",
                entityType = "casino"
            )

            favoriteRepository.removeAllFavorites(inputAccountName = model.accountName, inputEntityType = model.entityType)

            val favoriteItem1 = favoriteRepository.getFavorites(inputAccountName = "brain", inputEntityType = "casino")

            favoriteRepository.removeFavoriteEntity(model)

            val favoriteItem2 = favoriteRepository.getFavorites(inputAccountName = "brain", inputEntityType = "casino")

            favoriteRepository.removeAllFavorites(inputAccountName = model.accountName, inputEntityType = model.entityType)

            val modelNew = FavoriteEntity(
                accountName = "brain",
                entityId = "4",
                entityType = "casino"
            )

            favoriteRepository.insertFavoriteEntity(modelNew)

            val favoriteItem3 = favoriteRepository.getFavorites(inputAccountName = "brain", inputEntityType = "casino")
            for (favoriteEntity in favoriteItem3) {
                println("FavoriteItem: ${favoriteEntity.entityId}")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FavoriteSDKProjectTheme {
        Greeting("Android")
    }
}