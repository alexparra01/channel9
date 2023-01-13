# Channel9 Android Test

## Table of Content

- [Setup](#project-setup)

- [Architectures](#architectures)

## Project Setup

### Android Studio

Make sure that you have Android Studio Arctic Fox 2020.3.1 + and sdk version 33 

### Architectures

#### Clean Architecture
This application has implemented clean architecture so the package layout is divided by three:

- Data layout
- Domain layout
- Presentation layout

##### Data layout
This layout contains the external dependencies like [NetWorkDataSource]
##### Domain layout
This layout contains business logic
##### Presentation layout
In this layout contains all the ui screens that in this case are made using Jetpack Compose

[Clean Architecture] (https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

#### MVVM Architecture

the presentation layout implements MVVM approach and using `androidx.lifecycle.ViewModel`

[MVVM Architecture] (https://cdn.ttgtmedia.com/rms/onlineimages/whatis-model_view_viewmodel.png)
