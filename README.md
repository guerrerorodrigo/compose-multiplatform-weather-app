You need to get an API key from [Weather Api](https://www.weatherapi.com/). Register there, get the API key, and follow the next steps:
1. Create a `NetworkConstants` file in `com.rodrigoguerrero.myweather.data.remote.config` `package`.
2. Add the following:
```
object NetworkConstants {
    const val WEATHER_API_KEY = "YOUR_API_KEY"
}
```
3. Replace `YOUR_API_KEY` with the actual value of the `weatherapi` API key.


Libraries used:
- [Kamel](https://github.com/Kamel-Media/Kamel) - Used to load remote images.
- [Voyager](https://github.com/adrielcafe/voyager) - Used for compose navigation.
- [moko-resources](https://github.com/icerockdev/moko-resources) - Used to have string resources.
- [moko-mvvm](https://github.com/icerockdev/moko-mvvm) - Used to provide ViewModel implementations.
- [SqlDelight](https://github.com/cashapp/sqldelight) - Used for local database.
- [DataStore](https://developer.android.com/jetpack/androidx/releases/datastore) - Used to save preferences.
- [moko-permissions](https://github.com/icerockdev/moko-permissions) - Handle user permissions in both platforms.
- [ktor](https://ktor.io/) - Used for networking
- [koin](https://insert-koin.io/) - For dependency injection
- [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) - Date-time related operations
