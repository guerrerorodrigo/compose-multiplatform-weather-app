Weather app using Kotlin Multiplatform and Compose Multiplatform.

## Setup
1. Go to the [Weather Api](https://www.weatherapi.com/). 
2. Register and get an API key.
3. In `local.properties`, add the API key as `apiKey=YOUR_API_KEY`.

## Video
### Android
[Screen_recording_20230801_123215.webm](https://github.com/guerrerorodrigo/compose-multiplatform-weather-app/assets/77965057/16ea7959-42f0-4903-ae2c-710ebd2793dd)

### iOS
[Simulator Screen Recording - device-IPhone13Pro - 2023-08-01 at 12.43.40.webm](https://github.com/guerrerorodrigo/compose-multiplatform-weather-app/assets/77965057/2fa41b2a-addc-4a3a-b755-a640447ec44a)

## Libraries
- [Kamel](https://github.com/Kamel-Media/Kamel) - Used to load remote images.
- [Compose navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html) - Used for compose navigation.
- [lifecycle viewmodel](https://developer.android.com/reference/androidx/lifecycle/ViewModel) - Used to provide ViewModel implementations.
- [Room](https://developer.android.com/kotlin/multiplatform/room) - Used for local database.
- [DataStore](https://developer.android.com/jetpack/androidx/releases/datastore) - Used to save preferences.
- [moko-permissions](https://github.com/icerockdev/moko-permissions) - Handle user permissions in both platforms.
- [ktor](https://ktor.io/) - Used for networking
- [koin](https://insert-koin.io/) - For dependency injection
- [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) - Date-time related operations
- [BuildKonfig](https://github.com/yshrsmz/BuildKonfig) - Read values from gradle files 
