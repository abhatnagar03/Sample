# Sample project

This is a sample project that presents modern, 2020 approach to Android application development using Kotlin and latest tech-stack.

The goal of the project is to demonstrate best practices,  and present modern Android application architecture that is modular, scalable, maintainable and testable. This application follow official Google recommendations.


## **Characteristics:**

100% Kotlin
Modern architecture (feature modules, Clean Architecture, Model-View-ViewModel, Model-View-Intent)
Android Jetpack
A single-activity architecture, using the Navigation component to manage fragment operations
Reactive UI
Testing
Dependency Injection
Material design

## **Tech-stack**
- Kotlin + Coroutines - perform background operations
- Kodein - dependency injection
- Retrofit - networking
- Jetpack
- Navigation - deal with whole in-app navigation
- LiveData - notify views about database change
- Lifecycle - perform action when lifecycle state changes
- ViewModel - store and manage UI-related data in a lifecycle conscious way
- Coil - image loading library with Kotlin idiomatic API
- Lottie - animation library
- Stetho - application debugging tool

## **Architecture**
- Clean Architecture (at module level)
- MVVM + MVI (presentation layer)
- Dynamic feature modules
- Android Architecture components (ViewModel, LiveData, Navigation, SafeArgs plugin)
- Unit Tests (JUnit)
- Mockito + Mockito-Kotlin
- Gradle
- Gradle Kotlin DSL
- SafeArgs olugin

## **How to get run the project**

1. Install  json-server refer -> (https://github.com/typicode/json-server#getting-started)
2. Create a file products.json which contains following data:
```
{
  "productResults": []
}
```

4. Run `json-server --watch product.json` 
5. Connect Android phone to system using USB and then run `chrome://inspect`
6. Enable port forwarding for port localhost:3000
7. Add Android phone wifi IP Address in [sudo file](https://stackoverflow.com/questions/11005540/localhost-running-on-mac-can-i-view-it-on-my-android-phone)
8. Open chrome browser in the phone and type `localshost:3000/productResults`
9. The request should be successful and json content should be shown (which is empty initially)
10. Run the app 

## **Some Insights**
1. Backend is created by using [json-server](https://github.com/typicode/json-server#getting-started) to host product.json on local server 
2. App supports Add, update, Delete and Load operations on Database
