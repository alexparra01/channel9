# Channel9 Android Test

## Table of Content

- [Setup](#project-setup)

- [Architectures](#architectures)

- [Libraries](#libraries)

- [Test Coverage](#test-coverage)

- [Any Additional Features](#any-additional-features)

## Project Setup

### Android Studio

Make sure that you have Android Studio Arctic Fox 2020.3.1 + and sdk version 33 

## Architectures

#### Clean Architecture
This application incorporates clean architecture. The layout is divided in three parts:

- Data layout
- Domain layout
- Presentation layout

##### - Data layout
This layout is where the external dependencies are located like remote data source which is in charge of calling external
endpoints
##### - Domain layout
This layout contains business logic. It holds models, repository contract and use cases
##### - Presentation layout
This layout contains activities, screens and view models

[Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html "Clean Architecture")

#### MVVM Architecture

The presentation layout implements MVVM approach and uses `androidx.lifecycle.ViewModel`

[MVVM Architecture](https://en.wikipedia.org/wiki/Model-view-viewmodel "Model View ViewModel")

## Libraries

- Retrofit: This library is mostly to make http calls
- Moshi: Json parser
- Dagger Hilt: Dependency injection
- Androidx life cycle libraries for coroutines
- Jetpack compose
- Glide for loading pictures asynchronously

For unit testing:
- Mockito
For ui testing:
- jetpack compose test libraries

## Test Coverage
##### - Unit Test

This project is located in android test `src/test/java/com/test/channel9` and contains the following tests:

- NewsArticlesRepositoryTest -> this unit tests covers the external rest api calls before the response is transformed to flow streams in the use case class.
- NewsArticlesUseCase -> this test class has the following unit tests class:

  - fetchNewsArticlesTest -> This test mocks the api call and check for the transformation to FLow State
  - organiseNewsArticlesDescendingByTimeStampTest -> this test method verifies that the list of articles has been sorted by timestamp
  - retrieveThumbnailImageFromListTest -> This test method checks if the image thumbnail is retrieved from the list of images


##### - Ui Test
This project is located in android test `src/androidTest/java/com/test/channel9` and contains the following tests:

- ArticleDetailsScreenTest -> this ui test checks for the information loaded in ArticleDetails and verifies that the information is loaded in the webview and also navigates back to the MainScreen
- MainScreenTest -> this ui test checks that the MainScreen shows the list of articles
- AlertdialogPopUpTest -> this test is isolated for testing and only tests the dismiss button action

## Any Additional Features

- Jetpack Compose -> This project implements Jetpack Compose and Material Design 3 
