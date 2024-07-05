# Android mvvm sample project

# Table of contents

- [Android mvvm sample project](#android-mvvm-sample-project)
- [Table of contents](#table-of-contents)
- [What is this project?](#what-is-this-project)
- [What is mvvm?](#what-is-mvvm)
  - [Mvvm architecture](#mvvm-architecture)
  - [Components of mvvm](#components-of-mvvm)
  - [Key principles and benefits of mvvm](#key-principles-and-benefits-of-mvvm)
- [Libraries used](#libraries-used)
  - [Viewmodel (part of android architecture components)](#viewmodel-part-of-android-architecture-components)
    - [Key features](#key-features)
  - [Kotlin flow (part of kotlin language)](#kotlin-flow-part-of-kotlin-language)
    - [Key features](#key-features-1)
  - [Compose (ui app development toolkit)](#compose-ui-app-development-toolkit)
    - [Key features](#key-features-2)
    - [Compatibility](#compatibility)
  - [Retrofit 2 (remote data source)](#retrofit-2-remote-data-source)
    - [Key features](#key-features-3)
    - [Integration with android](#integration-with-android)
  - [Room ((local data source)](#room-local-data-source)
    - [Key features](#key-features-4)
    - [Integration with android:](#integration-with-android-1)
  - [Hilt (dependency injection for android)](#hilt-dependency-injection-for-android)
    - [Key features](#key-features-5)
- [Testing](#testing)
  - [Local (unit) testing](#local-unit-testing)
  - [Instrumented testing](#instrumented-testing)
- [Linters](#linters)
- [Github actions](#github-actions)
- [App preview](#app-preview)

# What is this project?

This project is a sample android application built using the model-view-viewmodel (mvvm) architectural pattern.
It serves as a demonstration of how mvvm can be implemented in an android app to achieve separation of concerns, maintainability, and testability.
Also, the application features a bottom navigation with 3 tabs.

# What is mvvm?

## Mvvm architecture

Mvvm, which stands for **model-view-viewmodel**, is an architectural pattern widely used in software development, particularly in building user interfaces (uis).
It was specifically tailored for modern ui development.

![image](https://github.com/pasararandrey/mvvm-sample/assets/56974924/235bb2c1-ac2f-4a37-a35b-c0e3461a379c)

## Components of mvvm

**Model**

-   represents the data and business logic of the application.

-   it encapsulates the data, state, and behavior of the application's domain.

-   models can include data retrieval from databases, network requests, or any other data source.

**View**

-   represents the ui components visible to the user.

-   it displays the data to the user and captures user interactions.

-   views are passive components and should not contain any business logic.

**Viewmodel**

-   acts as an intermediary between the view and the model.

-   exposes data from the model to the view through observable data streams.

-   provides methods and commands for handling user interactions.

-   viewmodel instances are lifecycle-aware, meaning they can survive configuration changes, such as screen rotations, without losing their data.

-   viewmodel does not directly reference the view; instead, it exposes data using observable properties or livedata, allowing the view to observe changes in the data and update itself accordingly.

## Key principles and benefits of mvvm

-   **separation of concerns**: mvvm separates the concerns of data presentation (view), data manipulation and logic (viewmodel), and data storage and retrieval (model).
    This separation makes the codebase easier to maintain, test, and understand.

-   **testability**: mvvm promotes testability by decoupling the ui logic from the view. Unit testing of viewmodel becomes straightforward as it does not rely on android framework components directly.

-   **reusability**: viewmodel encapsulates the ui-related data and logic, making it easier to reuse across different views. This promotes code reusability and reduces duplication.

-   **maintainability**: with clear separation of concerns and modularization, mvvm makes it easier to maintain and extend the codebase over time.
    Changes in one layer typically do not require modifications in other layers, reducing the risk of unintended side effects.

-   **support for reactive programming**: mvvm is well-suited for reactive programming paradigms, where changes in data trigger updates in the ui.
    Libraries like livedata or rxjava can be used in viewmodel to observe changes in data and update the ui reactively.

# Libraries used

## [Viewmodel (part of android architecture components)](https://developer.android.com/topic/libraries/architecture/viewmodel)

Viewmodel plays a crucial role in implementing the mvvm architectural pattern in android applications.
It serves as a communication hub between the view (ui) and the underlying data sources or business logic (model).

### Key features

-   **managing ui-related data**: viewmodel holds and manages the data that is relevant to the ui components.
    This data can be retrieved from repositories, databases, or network requests.

-   **surviving configuration changes**: viewmodel instances are scoped to the lifecycle of the associated ui component (typically an activity or fragment) and survive configuration changes, such as screen rotations.
    This ensures that the ui data remains intact across configuration changes and prevents unnecessary data reloads.

-   **exposing data to the view**: viewmodel exposes the required data to the view layer through observable properties or livedata objects.
    This allows the view to observe changes in the data and update itself accordingly without directly accessing the underlying data sources.

-   **handling user interactions**: viewmodel contains methods and commands for handling user interactions, such as button clicks or form submissions.
    It encapsulates the ui logic, making it easier to test and maintain.

## [Kotlin flow (part of kotlin language)](https://kotlinlang.org/docs/flow.html)

In mvvm (model-view-viewmodel) architecture, both kotlin flows and livedata can be used to handle asynchronous data streams between the viewmodel and the view (ui) layer.
However, the choice between them depends on various factors, including the specific requirements of the application and the development team's preferences.
Let's explore why kotlin flows might be chosen over livedata in certain scenarios

### Key features

-   **asynchronous operations**: kotlin flows offer more flexibility in handling asynchronous operations compared to livedata.
    While livedata is inherently tied to the android lifecycle and is designed for one-time data observations, flows are designed to handle asynchronous data streams that emit multiple values over time.
    This makes flows more suitable for handling long-running asynchronous tasks, such as network requests or database queries, in viewmodel classes.

-   **coroutines integration**: kotlin flows seamlessly integrate with kotlin coroutines, allowing developers to leverage the power of coroutines for asynchronous programming.
    Coroutines provide a lightweight and efficient way to perform concurrent operations, making it easier to write asynchronous code in viewmodel classes using flows.
    Livedata, on the other hand, does not have built-in support for coroutines and requires additional boilerplate code to work with coroutines.

-   **back pressure handling**: flows provide built-in support for back pressure handling, allowing developers to control the flow of data between producers and consumers.
    This is particularly useful in scenarios where data producers may produce data faster than consumers can consume it, preventing issues like memory leaks or crashes due to buffer overflow.
    Livedata, on the other hand, does not support back pressure handling out of the box.

-   **error handling**: flows offer more advanced error handling capabilities compared to livedata.
    Developers can use operators like `catch` and `oneach` to handle errors gracefully within the flow pipeline, making it easier to propagate errors from asynchronous operations to the ui layer for appropriate user feedback.
    While livedata can also handle errors, it requires additional error handling logic in the observer callbacks, which can lead to more verbose code.

-   **seamless transformation**: flows provide a wide range of operators for transforming, filtering, and combining data streams, allowing developers to perform complex data manipulation operations directly within the flow pipeline.
    This promotes a more functional programming style and leads to cleaner and more concise code.
    Livedata, on the other hand, has limited transformation capabilities and often requires additional livedata objects or mediatorlivedata for complex data transformations.

## [Compose (ui app development toolkit)](https://developer.android.com/develop/ui/compose)

Compose is a modern ui toolkit for building native android uis.
It's designed to make it easier and more intuitive to create user interfaces with kotlin code. compose follows a declarative programming model, where ui components aredescribed as functions that transform data into ui elements.

### Key features

-   **declarative ui**: with compose, you describe your ui using composable functions.
    These functions are lightweight and can be easily composed together to build complex uis.
    This declarative approach makes ui code easier to read, write, and maintain.

-   **single source of truth**: compose encourages the use of a single source of truth for ui state.
    This means that your ui components reflect the current state of your data, making it easier to keep your ui in sync with your app's underlying data model.

-   **ui testing**: compose makes ui testing easier by providing a testable api for interacting with ui components.
    You can write ui tests using the same kotlin code you use to build your ui, making it easier to write and maintain ui tests.

-   **jetpack integration**: compose is fully integrated with jetpack libraries, such as viewmodel, livedata, room, and navigation.
    This allows you to seamlessly integrate compose into your existing jetpack-based app architecture.

-   **material design**: compose provides built-in support for material design, making it easy to create modern and visually appealing uis that follow google's design guidelines.

-   **interactive design tools**: compose offers interactive design tools that allow you to preview your ui in real-time as you write code.
    This makes it easier to iterate on your ui designs and see the changes immediately.

-   **animations and transitions**: compose provides powerful animation and transition apis that allow you to create smooth and fluid user experiences.
    You can easily define animations and transitions to add polish to your uis.

### Compatibility

Compose is compatible with **android api level 21 (lollipop) and higher**, allowing you to use it in both new and
existing android projects.

## [Retrofit 2 (remote data source)](https://square.github.io/retrofit/)

Retrofit is a popular type-safe http client for android applications.
It simplifies network requests to restful apis by providing a declarative api, type-safe http methods, serialization/deserialization support, and asynchronous/synchronous request execution.

### Key features

-   **declarative api**: define api endpoints and request parameters using annotations and interfaces.

-   **type safety**: generates http request implementations at compile-time, ensuring type safety and reducing runtime errors.

-   **serialization/deserialization**: seamlessly integrates with json serialization libraries like gson or moshi.

-   **asynchronous and synchronous requests**: supports both asynchronous and synchronous execution of network requests.

-   **error handling**: offers robust error handling mechanisms for handling http errors and network timeouts.

### Integration with android

Retrofit integrates well with android architecture components like livedata and viewmodel, making it suitable for building reactive and lifecycle-aware apps.
It also works seamlessly with dependency injection frameworks like dagger or koin.

## [Room ((local data source)](https://developer.android.com/training/data-storage/room/)

Room is an android architecture components library that provides an abstraction layer over sqlite, the native relational database engine for android.
It simplifies database interactions and reduces boilerplate code, making it easier to work with databases in android apps.

### Key features

-   **object-relational mapping (orm)**: room allows developers to define annotated data objects (entities) that represent tables in the app's database.
    These entities encapsulate the schema of the database and map directly to rows in the corresponding tables.

-   **compile-time sql validation**: room performs compile-time verification of sql queries defined in dao (data access object) interfaces.
    This ensures syntactical correctness and reduces the risk of runtime errors related to sql syntax.

-   **built-in support for livedata/rxjava/flow**: room provides built-in support for livedata and rxjava, allowing developers to observe database changes reactively.
    This enables the ui to update automatically when the underlying data changes without the need for manual refreshes.

-   **database migrations**: room simplifies the process of migrating the database schema between different versions of the app.
    It supports schema versioning and provides utilities for defining migration scripts to modify the database schema without losing existing data.

-   **thread safety**: room ensures thread safety by performing database operations on background threads by default.
    It offers asynchronous query execution and allows developers to specify custom thread management strategies if needed.

### Integration with android:

Room integrates seamlessly with other components of the android ecosystem.
It also works well with kotlin coroutines for asynchronous programming and dependency injection frameworks like dagger or koin for managing dependencies efficiently.

## [Hilt (dependency injection for android)](https://developer.android.com/training/dependency-injection/hilt-android)

Hilt is a dependency injection library for android that is built on top of dagger 2.
It simplifies the implementation of dependency injection in android apps by providing a set of predefined components and annotations tailored for android development.

### Key features

-   **integration with android components**: hilt seamlessly integrates with android framework components such as activities, fragments, services, and viewmodel from the android architecture components.
    This allows for easy injection of dependencies into these components without manual configuration.

-   **simplified setup**: hilt reduces the amount of boilerplate code required for setting up dependency injection in android apps.
    Developers can annotate android classes with hilt annotations to indicate how dependencies should be provided and injected.

-   **scoping and lifecycle management**: hilt offers built-in support for scoping and lifecycle management, ensuring that dependencies are created and destroyed appropriately based on the lifecycle of android components.
    This helps prevent memory leaks and improves resource management.

-   **integration with viewmodel**: hilt provides special support for injecting dependencies into viewmodel instances.
    It offers viewmodel component scoped to each viewmodel, ensuring that viewmodel instances receive their dependencies correctly scoped to their lifecycle.

-   **testing support**: hilt simplifies the testing of android components by providing mechanisms for injecting mock dependencies during testing.
    This makes it easier to write unit tests and integration tests for android apps that use dependency injection.

# [Testing](https://developer.android.com/training/testing/fundamentals)

Testing is crucial in android development as it helps ensure the reliability and functionality of the app across various devices and scenarios.
By implementing thorough testing practices, bugs can be caught and fixed early, leading to a more stable and user-friendly application.
It also helps maintain a high level of code quality and ensure that new features or changes do not break existing functionality.

## [Local (unit) testing](https://developer.android.com/training/testing/local-tests)

Local testing, or unit testing, focuses on testing individual components of the application in isolation, typically without the need for a physical device or emulator.
These tests run on the jvm and are usually faster than instrumented tests, making them ideal for testing business logic.
In this project, a [FakeRepository](/app/src/test/java/com/example/mvvmsample/data/books/repository/FakeBookRepository.kt) is used to mock network requests, ensuring that tests are not dependent on network availability and are faster to execute.
[The viewmodels of several screens](/app/src/test/java/com/example/mvvmsample/ui/screen/) have been thoroughly tested, verifying that they handle data and state changes correctly.

## [Instrumented testing](https://developer.android.com/training/testing/instrumented-tests)

Instrumented testing involves running tests on an actual device or emulator, providing a more realistic environment for testing the app’s behavior, including interactions with the android framework and system components.
These tests are essential for verifying the correct functioning of the app’s ui and other integration points.

In this project, [Hilt](https://developer.android.com/training/dependency-injection/hilt-testing) is used for dependency injection to simplify the process of swapping out implementations during tests.
Specifically, implementations for [room](/app/src/androidTest/java/com/example/mvvmsample/di/TestDatabaseModule.kt), [service](/app/src/androidTest/java/com/example/mvvmsample/di/TestNetworkModule.kt), and [pager](/app/src/androidTest/java/com/example/mvvmsample/di/TestPagerModule.kt) are replaced.
This allows the app to be tested in a controlled environment without relying on external services or databases.
Thorough instrumented tests have been conducted on the screens for [books](/app/src/androidTest/java/com/example/mvvmsample/screen/books/BooksScreenTest.kt), [booksdetails](/app/src/androidTest/java/com/example/mvvmsample/screen/bookdetails/BookDetailsScreenTest.kt), and [favorite](/app/src/androidTest/java/com/example/mvvmsample/screen/favorite/FavoriteScreenTest.kt).
Additionally, an example of integration testing for the books graph is included, ensuring that complex interactions and navigation flows work as expected.

Furthermore, the implementation of BooksDao has been separately tested to ensure data is correctly handled and stored.
To avoid making actual network requests during tests, a [FakeBooksService](/app/src/androidTest/java/com/example/mvvmsample/fake/FakeBooksService.kt) is used, which simulates network responses, ensuring tests are reliable and do not fail due to network issues.

# Linters

In addition to the libraries mentioned above, the project also utilizes the following tools:

-   [**ktlint**](https://github.com/pinterest/ktlint) : ktlint is a kotlin linter that checks kotlin code for coding standards and style violations.
    It helps maintain consistent code formatting across the project.

-   [**detekt**](https://github.com/detekt/detekt): detekt is a static code analysis tool for kotlin that detects issues related to code complexity, style, potential bugs, and anti-patterns.

These tools ensure code quality, adherence to coding standards, and overall maintainability of the project.

# [Github actions](https://docs.github.com/en/actions)

Github actions is a continuous integration and continuous delivery (ci/cd) platform that allows you to automate your build, test, and deployment pipeline.
You can create workflows that build and test every pull request to your repository, or deploy merged pull requests to production.

The project implements the following github actions:

-   [**ktlint**](.github/workflows/ktlint-check.yml): checks the code formatting on each push.

-   [**detekt**](.github/workflows/detekt-check.yml): static code analysis at every push.

-   [**unit tests**](.github/workflows/unit-tests.yml): runs unit tests on every pull request or push to the master branch.

-   [**instrumented tests**](.github/workflows/instrumented-tests.yml): runs instrumented tests on every pull request or push to the master branch.

# App preview

Android adaptive icons adjust their shapes and colors according to the user's theme.

Basic app icon:

![image](https://github.com/pasararandrey/mvvm-sample/assets/56974924/e4b0bfed-e21d-4a21-baee-fcd1311eebea)

Example of variations based on the system's theme:

<img src="https://github.com/pasararandrey/mvvm-sample/assets/56974924/14f89635-31d2-4824-a839-49eb722e1cf8" alt="icon example" height = "250"/>
<img src="https://github.com/pasararandrey/mvvm-sample/assets/56974924/841fe2aa-6a21-4cd1-b8eb-6b6e256c8fd5" alt="icon example" height = "250"/>

When user enters the app he able to see splash screen.

![splashscreen_preview](https://github.com/pasararandrey/mvvm-sample/assets/56974924/9e1b3c58-bb2b-48a5-a0a5-b4e04e54fc69)

The bottom navigation consists of three tabs.

-   **home**: tab name in the middle of the screen.

![image](https://github.com/pasararandrey/mvvm-sample/assets/56974924/54e87355-e49e-4ef1-95c6-4daa29eb4cf8)

-   **favorite**: list of user's favorite books.

![favorite_list_1](https://github.com/pasararandrey/mvvm-sample/assets/56974924/17528216-2bed-40c2-8a20-0714ad471505)

-   **books**: list of books that loaded from remote api or from local database.

![book_list_1](https://github.com/pasararandrey/mvvm-sample/assets/56974924/a99f6144-d9ac-453c-b093-769bc15813cc) ![book_list_2](https://github.com/pasararandrey/mvvm-sample/assets/56974924/939eec0d-9545-4729-8fd5-5c19595ff668)

Also there are others screens:

-   **book details**: represents details of the book.

![book_details](https://github.com/pasararandrey/mvvm-sample/assets/56974924/ee9c88d9-a78a-4630-ad71-28ca3ff2a2a1)
