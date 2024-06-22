# Favorite SDK

Android SDK for easily integrating with a room database on android applications

## table of contents

- [pre-knowledge](#pre-knowledge)
- - [FavoriteEntity description](#favoriteentity-class)
- - [Kotlin definition](#kotlin-definition)
- [Importing](#importing)
- [Initializing](#initializing)
- [Using in UI compose](#using-in-ui-compose)
- [Using outside UI compose](#using-outside-ui-compose)
- [Methods](#methods)

## Pre-knowledge

The SDK uses room which is a jetpack android library and saves favorites on the phone.

the basic structure of the favorite table is as follows:

## FavoriteEntity Class

The `FavoriteEntity` class represents a favorite entity with the following properties:

- **entityId**: A unique identifier for the entity.
- **accountName**: The name of the account associated with the entity.
- **entityType**: The type or category of the entity.

### Kotlin Definition

```kotlin
data class FavoriteEntity(
    val entityId: String,
    val accountName: String,
    val entityType: String
)
```

## Importing

To import it into your android project there is several options:

### using maven
Add this
```maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
and this
```maven
	<dependency>
	    <groupId>com.github.DSDrachmann</groupId>
	    <artifactId>FavoriteSDK</artifactId>
	    <version>0.0.1</version>
	</dependency>
```

### using gradle
This goes into your root project build.gradle file
```groovy
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
add this as well
```groovy
	dependencies {
	        implementation 'com.github.DSDrachmann:FavoriteSDK:0.0.1'
	}
```

### using gradle on the new kts
Add this to your settings.gradle.kts file:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
    ...
        maven("https://jitpack.io")
    }
}
```
and add this to your app build.gradle.kts file

```groovy
implementation ("com.github.DSDrachmann:FavoriteSDK:0.0.1")
```

## Initializing

To initialize it, you have to define these two variables and call these two methods:

These two are class variables
```kotlin
    private lateinit var database: FavoriteRoomDatabase
    private lateinit var favoriteRepository: FavoriteDatabaseRepository
```
and this is intiated inside an init, method, wherever.
```kotlin
        database = favoriteDatabaseProvider.getInstance(this)
        favoriteRepository = FavoriteDatabaseRepository(database)
```

## using in UI Compose
Inside a Compose function, you have to use remember state and launchedEffect to avoid data leakage and
to guarantee that the UI gets reflected when changes comes to the favorite list:

```kotlin
val favoriteItems = remember { mutableStateOf<List<FavoriteEntity>>(emptyList()) }
                    LaunchedEffect(Unit) {
                        favoriteRepository.insertFavoriteEntity(model)

                        val favoriteItem = favoriteRepository.getFavorites(
                            inputAccountName = "testAccountName",
                            inputEntityType = "Game"
                        )
                        favoriteItems.value = favoriteItem
                    }

favoriteItems.value.forEach { favoriteEntity ->
    Text(text = "FavoriteItem: ${favoriteEntity.entityId}")
}
```

## using outside UI Compose
To use it outside the UI you can do this instead:

```kotlin
        lifecycleScope.launch {
            val model = FavoriteEntity(
                accountName = "testAccountName",
                entityId = "3",
                entityType = "Game"
            )
            favoriteRepository.insertFavoriteEntity(model)

            val favoriteItem = favoriteRepository.getFavorites(inputAccountName = "brain", inputEntityType = "casino")
            for (favoriteEntity in favoriteItem) {
                println("FavoriteItem: ${favoriteEntity.entityId}")
            }
        }
```

## Methods
- `insertFavoriteEntity(item: FavoriteEntity)` Call this with an instantiated FavoriteEntity object to insert that object into the local storage  
- `removeFavoriteEntity(item: FavoriteEntity)` Call this to remove a row from the object.  
- `getFavorites(inputAccountName: String, inputEntityType: String): List<FavoriteEntity>` Call this to retrieve all favorites and do with them as you like.  
- `removeAllFavorites(inputAccountName: String, inputEntityType: String)` Call this to remove all favorites from a user on a entityType.  

