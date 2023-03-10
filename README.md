# PokemonDM

The App uses a set of Android Jetpack libraries plus Retrofit to dispay data from REST API. The App
uses Kotlin.

### Prerequisites

The project has all required dependencies in the gradle files. Add the Project to Android Studio or
Intelij and build.All the required dependencies will be downloaded and installed.

## Architecture

The project uses MVVM architecture pattern.

## Libraries

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel/) - Manage UI
  related data in a lifecycle conscious way and act as a channel between use cases and ui
* [ViewBinding](https://developer.android.com/topic/libraries/data-binding) - support library that
  allows binding of UI components in layouts to data sources,binds character details and search
  results to UI
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
  - Android Jetpack's Navigation component helps in implementing navigation between fragments
* [Dagger Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) - For Dependency
  Injection.
* [Retrofit](https://square.github.io/retrofit/) - To access the Rest Api
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata?hl=es-419) - To access data sequentially
* [Firebase](https://firebase.google.com) 
- Use firebaseAuth to be able to login, with email and password, you can also log in through google account, it has a section to register your email and password.
- I also used FirebaseStore to store user data.

 
