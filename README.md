<p align="center">
 <img src="https://github.com/herdal06/HekMovie/blob/master/app/src/main/res/drawable/hekmovie.png?raw=true"  alt="drawing" />
</p>
<p align="center">  
🎬 HekMovie is a sample Android project using TMDB API based on MVVM architecture
<br/>
<p align="center">For API Documentation: <a href="https://developers.themoviedb.org/3">Click here</a></p>
</p>

<p align="center">
<a href='https://play.google.com/store/apps/details?id=com.herdal.moviehouse'><img width="200px" alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png'/></a>
</p>

### App Screenshots

| home screen | movie detail | actor/actress detail |
|:-:|:-:|:-:|
| <img src="https://github.com/herdal06/HekMovie/blob/master/arts/1.png?raw=true" alt="drawing" width="250"/> | <img src="https://github.com/herdal06/HekMovie/blob/master/arts/5.png?raw=true" alt="drawing" width="250"/> | <img src="https://github.com/herdal06/HekMovie/blob/master/arts/3.png?raw=true" alt="drawing" width="250"/> 
| bottom of the home screen | genres | popular people |
| <img src="https://github.com/herdal06/HekMovie/blob/master/arts/4.png?raw=true" alt="drawing" width="250"/> | <img src="https://github.com/herdal06/HekMovie/blob/master/arts/2.png?raw=true" alt="drawing" width="250"/> | <img src="https://github.com/herdal06/HekMovie/blob/master/arts/6.png?raw=true" alt="drawing" width="250"/> 

## App Gifs

| movie detail | home screen |
|:-:|:-:|
| <img src="https://github.com/herdal06/HekMovie/blob/master/arts/2.gif?raw=true"  width="250"/> | <img src="https://github.com/herdal06/HekMovie/blob/master/arts/1.gif?raw=true"  width="250"/> |

## How to build on your environment
Add your [The Movie DB](https://www.themoviedb.org/)'s API key in local.properties file.
```xml
API_KEY=add_your_api_key
```
and run the app.

## Tech stack
* ✅ MVVM with Clean Architecture
* ✅ [Kotlin Flow][33] - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
* ✅ [LiveData][31] - is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
* ✅ [Coroutines][51] - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
* ✅ [Navigation Component][24] - Handle everything needed for in-app navigation. asynchronous tasks for optimal execution.
* ✅ [Safe-Args][25] - For passing data between destinations
* ✅ [Paging3][85] - The Paging library helps you load and display pages of data from a larger dataset from local storage or over network.
* ✅ [Dagger-Hilt][93] - A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
* ✅ [ViewModel][17] - Easily schedule asynchronous tasks for optimal execution.
* ✅ [Retrofit][90]- Retrofit is a REST client for Java/ Kotlin and Android by Square inc under Apache 2.0 license. Its a simple network library that is used for network transactions. By using this library we can seamlessly capture JSON response from web service/web API.
* ✅ [OkHttp][23] - Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
* ✅ [Moshi][95] - A modern JSON library for Kotlin and Java.
* ✅ [View Binding][11] - a feature that allows you to more easily write code that interacts with views.
* ✅ [Data Binding][86] - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
* ✅ [Lifecycle][22] - As a user navigates through, out of, and back to your app, the Activity instances in your app transition through different states in their lifecycle.
* ✅ [Glide][27] - for image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
* ✅ [Timber][9] - This is a logger with a small, extensible API which provides utility on top of Android's normal Log class.

[11]: https://developer.android.com/topic/libraries/view-binding
[92]: https://coil-kt.github.io/coil/
[93]: https://developer.android.com/training/dependency-injection/hilt-android
[51]: https://developer.android.com/kotlin/coroutines
[90]: https://square.github.io/retrofit/
[33]: https://developer.android.com/kotlin/flow
[22]: https://developer.android.com/guide/components/activities/activity-lifecycle
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=Cj0KCQiA4uCcBhDdARIsAH5jyUlE1HL0TNxXu5b4pw6DEMOlRccWdVnqiRcLji7OHsDN6trNOKa-sdgaAr6rEALw_wcB&gclsrc=aw.ds
[23]: https://square.github.io/okhttp/
[24]: https://developer.android.com/guide/navigation/navigation-getting-started
[25]: https://developer.android.com/guide/navigation/navigation-pass-data
[31]: https://developer.android.com/topic/libraries/architecture/livedata
[27]: https://github.com/bumptech/glide
[85]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[86]: https://developer.android.com/topic/libraries/data-binding
[95]: https://github.com/square/moshi
[9]: https://github.com/JakeWharton/timber

## TODOS:
1. User authentication
2. Search movies and people
3. Add movie to watch later list
###### If you feel like support me about this features, I will gladly accept.
