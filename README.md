# Android MVVM Sample Project

## 1. What is this project?

This project is a sample Android application built using the Model-View-ViewModel (MVVM) architectural pattern. It
serves as a demonstration of how MVVM can be implemented in an Android app to achieve separation of concerns,
maintainability, and testability. Also, the application features a bottom navigation with 3 tabs.

## 2. What is MVVM?

### MVVM Architecture

MVVM, which stands for **Model-View-ViewModel**, is an architectural pattern widely used in software development,
particularly in building user interfaces (UIs). It was specifically tailored for modern UI development.

![image](https://github.com/PasararAndrey/mvvm-sample/assets/56974924/235bb2c1-ac2f-4a37-a35b-c0e3461a379c)

### Components of MVVM

1. **Model**:
    - Represents the data and business logic of the application.
    - It encapsulates the data, state, and behavior of the application's domain.
    - Models can include data retrieval from databases, network requests, or any other data source.

2. **View**:
    - Represents the UI components visible to the user.
    - It displays the data to the user and captures user interactions.
    - Views are passive components and should not contain any business logic.

3. **ViewModel**:
    - Acts as an intermediary between the View and the Model.
    - Exposes data from the Model to the View through observable data streams.
    - Provides methods and commands for handling user interactions.
    - ViewModel instances are lifecycle-aware, meaning they can survive configuration changes, such as screen rotations,
      without losing their data.
    - ViewModel does not directly reference the View; instead, it exposes data using observable properties or LiveData,
      allowing the View to observe changes in the data and update itself accordingly.

### Key Principles and Benefits of MVVM

- **Separation of Concerns**: MVVM separates the concerns of data presentation (View), data manipulation and logic (
  ViewModel), and data storage and retrieval (Model). This separation makes the codebase easier to maintain, test, and
  understand.

- **Testability**: MVVM promotes testability by decoupling the UI logic from the View. Unit testing of ViewModel becomes
  straightforward as it does not rely on Android framework components directly.

- **Reusability**: ViewModel encapsulates the UI-related data and logic, making it easier to reuse across different
  Views. This promotes code reusability and reduces duplication.

- **Maintainability**: With clear separation of concerns and modularization, MVVM makes it easier to maintain and extend
  the codebase over time. Changes in one layer typically do not require modifications in other layers, reducing the risk
  of unintended side effects.

- **Support for Reactive Programming**: MVVM is well-suited for reactive programming paradigms, where changes in data
  trigger updates in the UI. Libraries like LiveData or RxJava can be used in ViewModel to observe changes in data and
  update the UI reactively.

## 3. Libraries Used

The following libraries are used in this project:

### [ViewModel (part of Android Architecture Components)](https://developer.android.com/topic/libraries/architecture/viewmodel)

ViewModel plays a crucial role in implementing the MVVM architectural pattern in Android applications. It serves as a
communication hub between the View (UI) and the underlying data sources or business logic (Model). The primary
responsibilities of ViewModel include:

#### Key Features:

- **Managing UI-related data**: ViewModel holds and manages the data that is relevant to the UI components. This data
  can be retrieved from repositories, databases, or network requests.

- **Surviving configuration changes**: ViewModel instances are scoped to the lifecycle of the associated UI component (
  typically an activity or fragment) and survive configuration changes, such as screen rotations. This ensures that the
  UI data remains intact across configuration changes and prevents unnecessary data reloads.

- **Exposing data to the View**: ViewModel exposes the required data to the View layer through observable properties or
  LiveData objects. This allows the View to observe changes in the data and update itself accordingly without directly
  accessing the underlying data sources.

- **Handling user interactions**: ViewModel contains methods and commands for handling user interactions, such as button
  clicks or form submissions. It encapsulates the UI logic, making it easier to test and maintain.

### [Kotlin Flow (part of Kotlin language)](https://kotlinlang.org/docs/flow.html)

In MVVM (Model-View-ViewModel) architecture, both Kotlin Flows and LiveData can be used to handle asynchronous data
streams between the ViewModel and the View (UI) layer. However, the choice between them depends on various factors,
including the specific requirements of the application and the development team's preferences. Let's explore why Kotlin
Flows might be chosen over LiveData in certain scenarios:

#### Key Features:

- **Asynchronous Operations**: Kotlin Flows offer more flexibility in handling asynchronous operations compared to
  LiveData. While LiveData is inherently tied to the Android lifecycle and is designed for one-time data observations,
  Flows are designed to handle asynchronous data streams that emit multiple values over time. This makes Flows more
  suitable for handling long-running asynchronous tasks, such as network requests or database queries, in ViewModel
  classes.

- **Coroutines Integration**: Kotlin Flows seamlessly integrate with Kotlin Coroutines, allowing developers to leverage
  the power of coroutines for asynchronous programming. Coroutines provide a lightweight and efficient way to perform
  concurrent operations, making it easier to write asynchronous code in ViewModel classes using Flows. LiveData, on the
  other hand, does not have built-in support for coroutines and requires additional boilerplate code to work with
  coroutines.

- **Back Pressure Handling**: Flows provide built-in support for back pressure handling, allowing developers to control
  the flow of data between producers and consumers. This is particularly useful in scenarios where data producers may
  produce data faster than consumers can consume it, preventing issues like memory leaks or crashes due to buffer
  overflow. LiveData, on the other hand, does not support back pressure handling out of the box.

- **Error Handling**: Flows offer more advanced error handling capabilities compared to LiveData. Developers can use
  operators like `catch` and `onEach` to handle errors gracefully within the flow pipeline, making it easier to
  propagate errors from asynchronous operations to the UI layer for appropriate user feedback. While LiveData can also
  handle errors, it requires additional error handling logic in the observer callbacks, which can lead to more verbose
  code.

- **Seamless Transformation**: Flows provide a wide range of operators for transforming, filtering, and combining data
  streams, allowing developers to perform complex data manipulation operations directly within the flow pipeline. This
  promotes a more functional programming style and leads to cleaner and more concise code. LiveData, on the other hand,
  has limited transformation capabilities and often requires additional LiveData objects or MediatorLiveData for complex
  data transformations.

### [Compose (UI App Development Toolkit)](https://developer.android.com/develop/ui/compose)

Compose is a modern UI toolkit for building native Android UIs. It's designed to make it easier and more intuitive to
create user interfaces with Kotlin code. Compose follows a declarative programming model, where UI components are
described as functions that transform data into UI elements.

#### Key Features:

- **Declarative UI**: With Compose, you describe your UI using composable functions. These functions are lightweight and
  can be easily composed together to build complex UIs. This declarative approach makes UI code easier to read, write,
  and maintain.

- **Single Source of Truth**: Compose encourages the use of a single source of truth for UI state. This means that your
  UI components reflect the current state of your data, making it easier to keep your UI in sync with your app's
  underlying data model.

- **UI Testing**: Compose makes UI testing easier by providing a testable API for interacting with UI components. You
  can write UI tests using the same Kotlin code you use to build your UI, making it easier to write and maintain UI
  tests.

- **Jetpack Integration**: Compose is fully integrated with Jetpack libraries, such as ViewModel, LiveData, Room, and
  Navigation. This allows you to seamlessly integrate Compose into your existing Jetpack-based app architecture.

- **Material Design**: Compose provides built-in support for Material Design, making it easy to create modern and
  visually appealing UIs that follow Google's design guidelines.

- **Interactive Design Tools**: Compose offers interactive design tools that allow you to preview your UI in real-time
  as you write code. This makes it easier to iterate on your UI designs and see the changes immediately.

- **Animations and Transitions**: Compose provides powerful animation and transition APIs that allow you to create
  smooth and fluid user experiences. You can easily define animations and transitions to add polish to your UIs.

#### Compatibility:

Compose is compatible with **Android API level 21 (Lollipop) and higher**, allowing you to use it in both new and
existing Android projects.

### [Retrofit 2 (Remote Data Source)](https://square.github.io/retrofit/)

Retrofit is a popular type-safe HTTP client for Android applications. It simplifies network requests to RESTful APIs by
providing a declarative API, type-safe HTTP methods, serialization/deserialization support, and asynchronous/synchronous
request execution.

#### Key Features:

- **Declarative API**: Define API endpoints and request parameters using annotations and interfaces.

- **Type Safety**: Generates HTTP request implementations at compile-time, ensuring type safety and reducing runtime
  errors.

- **Serialization/Deserialization**: Seamlessly integrates with JSON serialization libraries like Gson or Moshi.

- **Asynchronous and Synchronous Requests**: Supports both asynchronous and synchronous execution of network requests.

- **Error Handling**: Offers robust error handling mechanisms for handling HTTP errors and network timeouts.

#### Integration with Android:

Retrofit integrates well with Android Architecture Components like LiveData and ViewModel, making it suitable for
building reactive and lifecycle-aware apps. It also works seamlessly with dependency injection frameworks like Dagger or
Koin.

### [Room ((Local Data Source)](https://developer.android.com/training/data-storage/room/)

Room is an Android Architecture Components library that provides an abstraction layer over SQLite, the native relational
database engine for Android. It simplifies database interactions and reduces boilerplate code, making it easier to work
with databases in Android apps.

#### Key Features:

- **Object-Relational Mapping (ORM)**: Room allows developers to define annotated data objects (entities) that represent
  tables in the app's database. These entities encapsulate the schema of the database and map directly to rows in the
  corresponding tables.

- **Compile-Time SQL Validation**: Room performs compile-time verification of SQL queries defined in DAO (Data Access
  Object) interfaces. This ensures syntactical correctness and reduces the risk of runtime errors related to SQL syntax.

- **Built-in Support for LiveData/RxJava/Flow**: Room provides built-in support for LiveData and RxJava, allowing
  developers to observe database changes reactively. This enables the UI to update automatically when the underlying
  data changes without the need for manual refreshes.

- **Database Migrations**: Room simplifies the process of migrating the database schema between different versions of
  the app. It supports schema versioning and provides utilities for defining migration scripts to modify the database
  schema without losing existing data.

- **Thread Safety**: Room ensures thread safety by performing database operations on background threads by default. It
  offers asynchronous query execution and allows developers to specify custom thread management strategies if needed.

#### Integration with Android:

Room integrates seamlessly with other components of the Android ecosystem. It also works well with Kotlin Coroutines for
asynchronous programming and dependency injection frameworks like Dagger or Koin for managing dependencies efficiently.

### [Hilt (Dependency Injection for Android)](https://developer.android.com/training/dependency-injection/hilt-android)

Hilt is a dependency injection library for Android that is built on top of Dagger 2. It simplifies the implementation of
dependency injection in Android apps by providing a set of predefined components and annotations tailored for Android
development.

#### Key Features:

- **Integration with Android Components**: Hilt seamlessly integrates with Android framework components such as
  activities, fragments, services, and ViewModel from the Android Architecture Components. This allows for easy
  injection of dependencies into these components without manual configuration.

- **Simplified Setup**: Hilt reduces the amount of boilerplate code required for setting up dependency injection in
  Android apps. Developers can annotate Android classes with Hilt annotations to indicate how dependencies should be
  provided and injected.

- **Scoping and Lifecycle Management**: Hilt offers built-in support for scoping and lifecycle management, ensuring that
  dependencies are created and destroyed appropriately based on the lifecycle of Android components. This helps prevent
  memory leaks and improves resource management.

- **Integration with ViewModel**: Hilt provides special support for injecting dependencies into ViewModel instances. It
  offers ViewModel component scoped to each ViewModel, ensuring that ViewModel instances receive their dependencies
  correctly scoped to their lifecycle.

- **Testing Support**: Hilt simplifies the testing of Android components by providing mechanisms for injecting mock
  dependencies during testing. This makes it easier to write unit tests and integration tests for Android apps that use
  dependency injection.

## 4. Other Tools

In addition to the libraries mentioned above, the project also utilizes the following tools:

- [**ktlint**](https://github.com/pinterest/ktlint) : ktlint is a Kotlin linter that checks Kotlin code for coding
  standards and style violations. It helps maintain consistent code formatting across the project.
- [**detekt**](https://github.com/detekt/detekt): detekt is a static code analysis tool for Kotlin that detects issues
  related to code complexity, style, potential bugs, and anti-patterns.

These tools ensure code quality, adherence to coding standards, and overall maintainability of the project.

## 5. App Preview

The bottom navigation consists of three tabs. 

- **Home**: tab name in the middle of the screen.

![image](https://github.com/PasararAndrey/mvvm-sample/assets/56974924/54e87355-e49e-4ef1-95c6-4daa29eb4cf8)

- **Settings**: tab name in the middle of the screen.

![image](https://github.com/PasararAndrey/mvvm-sample/assets/56974924/322b5e4d-5957-4cd7-ba83-33e6107a8d49)

- **Settings**: tab name in the middle of the screen. An example of how to use the viewmodel. A randomly generated number in the viewmodel is delivered to the screen using StateFlow.

![image](https://github.com/PasararAndrey/mvvm-sample/assets/56974924/827b6ee1-b19e-458e-99b8-8a8255e86b07)


