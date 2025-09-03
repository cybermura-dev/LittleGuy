# LittleGuy

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
  - [Prerequisites](#prerequisites)
  - [Steps](#steps)
- [Usage](#usage)
  - [Trackers](#trackers)
  - [Articles](#articles)
  - [Profile](#profile)
- [Architecture](#architecture)
  - [Project Structure](#project-structure)
  - [Core Components](#core-components)
  - [UI Components](#ui-components)
  - [Data Models](#data-models)
- [Build Configuration](#build-configuration)
  - [Gradle Dependencies](#gradle-dependencies)
- [License](#license)

## Overview

**LittleGuy** is a mobile health tracking app that includes sleep and water intake trackers, exercise routines, games, and educational articles. It helps maintain a healthy lifestyle through personalized recommendations and activity monitoring.

## Features

- 📊 **Sleep Tracker:** Monitor the duration and quality of your sleep.
- 💧 **Water Tracker:** Track your daily water intake.
- 🏋️ **Exercise:** Workout programs for different age groups.
- 🎮 **Games:** Interactive activities for physical exercises.
- 📚 **Articles:** Educational content on health, nutrition, and development.
- 👤 **Profile:** Store personal data and track progress.

## Technologies

**LittleGuy** uses the following technologies:

- **Kotlin**: The primary programming language for the application.
- **Room**: Local database for data persistence.
- **ViewModel & LiveData**: To manage UI-related data in a lifecycle-aware manner.
- **Material Design**: UI components and guidelines to ensure a consistent and intuitive design.
- **RecyclerView**: Efficient view for displaying lists and cards.
- **Navigation Component**: For handling in-app navigation.

## Installation

### Prerequisites

- Android Studio (version 2023.2.1 or above)
- Android SDK (minSdk 26, targetSdk 35)
- Emulator or physical device running Android 8.0+ (API level 26 or higher)

### Steps

1. **Clone the Repository:**
     ```bash
     git clone https://github.com/cybermura-dev/LittleGuy.git
     cd LittleGuy
     ```

2. **Open in Android Studio:** Open the project in Android Studio.

3. **Sync the project with Gradle:**
  - Go to **File** > **Sync Project with Gradle Files**.

4. **Run the App:** Use the Android Studio toolbar to select a device and click Run to start the app.

## Usage

### Trackers

- **Sleep:** Set the sleep duration using a slider.
- **Water:** Add cups of water by clicking the ➕ button.
- **History:** View statistics for the last 7 days.

### Articles

- Choose from categories: Health, Nutrition, Development.
- Read articles with interactive formatting.

### Profile

- Edit personal details (name, age, weight, height).

### Project Structure

Here's an overview of the project's directory structure:

```bash
src/
├── main/
│   ├── AndroidManifest.xml     # Android Manifest file
│   ├── java/
│   │   └── ru/
│   │       └── takeshiko/
│   │           └── littleguy/
│   │               ├── LittleGuyApp.kt                # Main application class
│   │               ├── adapters/                       # Adapters for RecyclerViews
│   │               │   ├── ArticleAdapter.kt
│   │               │   ├── SleepRecordAdapter.kt
│   │               │   ├── WaterRecordAdapter.kt
│   │               │   └── WorkoutAdapter.kt
│   │               ├── data/                           # Data models and repositories
│   │               │   ├── AppDatabase.kt
│   │               │   ├── Article.kt
│   │               │   ├── SleepRecord.kt
│   │               │   ├── StepRecord.kt
│   │               │   ├── User.kt
│   │               │   ├── WaterRecord.kt
│   │               │   ├── WorkoutCategory.kt
│   │               │   ├── WorkoutItem.kt
│   │               │   ├── WorkoutSection.kt
│   │               │   ├── dao/                        # DAO classes for data access
│   │               │   │   ├── SleepRecordDao.kt
│   │               │   │   ├── StepRecordDao.kt
│   │               │   │   ├── UserDao.kt
│   │               │   │   └── WaterRecordDao.kt
│   │               │   └── repo/                       # Repositories for managing data logic
│   │               │       ├── SleepRepository.kt
│   │               │       ├── UserRepository.kt
│   │               │       └── WaterRepository.kt
│   │               ├── ui/                             # UI components (Activities, Fragments)
│   │               │   ├── ArticleDetailsActivity.kt
│   │               │   ├── FoodArticlesActivity.kt
│   │               │   ├── GamesActivity.kt
│   │               │   ├── GetStartedActivity.kt
│   │               │   ├── HealthArticlesActivity.kt
│   │               │   ├── MenuActivity.kt
│   │               │   ├── PersonalDataEditorActivity.kt
│   │               │   ├── PromotionArticlesActivity.kt
│   │               │   ├── SleepTrackerActivity.kt
│   │               │   ├── SplashActivity.kt
│   │               │   ├── TrackersActivity.kt
│   │               │   └── WorkoutActivity.kt
│   │               │   ├── animators/                  # Animators for UI transitions
│   │               │   │   └── ViewClickAnimator.kt
│   │               │   └── fragments/                   # Fragments used in UI
│   │               │       ├── BaseFragment.kt
│   │               │       └── menu/
│   │               │           ├── ArticleListFragment.kt
│   │               │           ├── HomeFragment.kt
│   │               │           ├── PhysicalListFragment.kt
│   │               │           └── ProfileFragment.kt
│   │               ├── utils/                          # Utility classes
│   │               │   ├── PreferencesManager.kt
│   │               │   └── SpaceItemDecoration.kt
│   │               └── viewmodels/                     # ViewModel classes for managing UI-related data
│   │                   ├── SleepViewModel.kt
│   │                   ├── SleepViewModelFactory.kt
│   │                   ├── UserViewModel.kt
│   │                   ├── UserViewModelFactory.kt
│   │                   ├── WaterViewModel.kt
│   │                   └── WaterViewModelFactory.kt
└── res/                           # Application resources (layout, values, etc.)
```

### Core Components

The core components of **LittleGuy** include:

- **Room Database** for local data storage (sleep, water intake, etc.).
- **ViewModel & LiveData** to manage UI-related data lifecycle-consciously.
- **Retrofit** to manage network requests for retrieving educational articles.
- **Navigation Component** for handling screen transitions.

### UI Components

The application UI components are built using:

- **Material Components** to follow the Material Design principles.
- **RecyclerView** for displaying lists and cards of data.
- **ConstraintLayout** to arrange the UI efficiently.

### Data Models

The core data models include:

- **SleepRecord**: Stores information related to sleep duration and quality.
- **WaterRecord**: Tracks daily water intake.
- **User**: Stores personal information (age, weight, height).
- **Article**: Represents educational content on health, nutrition, etc.
- **Workout**: Includes workout categories, items, and sections for exercises.


## Build Configuration

### Gradle Dependencies

The application uses the following dependencies:

- **AndroidX Core KTX** for Kotlin extensions to simplify Android API usage.
- **AndroidX AppCompat** for backward-compatible UI components and support for modern Android features.
- **Material Components** for building UI with Material Design.
- **AndroidX ConstraintLayout** for flexible and efficient layout management.
- **Room** for local database persistence (including runtime, compiler, and KTX extensions).
- **ViewModel KTX** for managing UI-related data lifecycle-consciously.
- **Retrofit** for making network requests.
- **Gson Converter** for Retrofit to handle JSON parsing and serialization.

```gradle
dependencies {

    // App dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    // Room database dependencies
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // View model dependencies
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
}
```

```toml
[versions]
agp = "8.7.3"
constraintlayout = "2.2.0"
coreKtx = "1.15.0"
appcompat = "1.7.0"
kotlin = "2.0.21"
lifecycleViewmodelKtx = "2.8.7"
material = "1.12.0"
retrofit = "2.9.0"
roomRuntime = "2.6.1"

[libraries]
androidx-appcompat = { module = "androidx.appcompat:appcompat", version.ref = "appcompat" }
androidx-constraintlayout = { module = "androidx.constraintlayout:constraintlayout", version.ref = "constraintlayout" }
androidx-core-ktx = { module = "androidx.core:core-ktx", version.ref = "coreKtx" }
androidx-lifecycle-viewmodel-ktx = { module = "androidx.lifecycle:lifecycle-viewmodel-ktx", version.ref = "lifecycleViewmodelKtx" }
androidx-room-compiler = { module = "androidx.room:room-compiler", version.ref = "roomRuntime" }
androidx-room-ktx = { module = "androidx.room:room-ktx", version.ref = "roomRuntime" }
androidx-room-runtime = { module = "androidx.room:room-runtime", version.ref = "roomRuntime" }
converter-gson = { module = "com.squareup.retrofit2:converter-gson", version.ref = "retrofit" }
material = { module = "com.google.android.material:material", version.ref = "material" }
retrofit = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Copyright (c) 2025 cybermura
