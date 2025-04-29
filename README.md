
# 📱Native Android Frontend for Ktor JWT Client

## Overview
This is a native Android application built with Jetpack Compose that connects to a Ktor backend [here](https://github.com/jante-adebowale/ktor-jwt-auth) using JWT (JSON Web Token) authentication.
It demonstrates a full login, auto-refresh token handling, and auto-logout flow for secure user sessions.

## ✨Features

- 🔐 JWT Authentication (Access + Refresh Tokens)
- 🔄 Auto Refresh Expired Access Tokens
- 🚪 Auto Logout When Refresh Token Expires
- 📦 Secure Token Storage
- ⚡  Clean Architecture (Separation of Concerns)
- 🖌️ Built Fully with Jetpack Compose
- 🔗 Retrofit + OkHttp Integration
- 🧩 Koin for Dependency Injection

## Architecture
**AndroidJwtAuthClient** is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

### 📸 Screenshots (Light theme)
![Screenshot showing Login, Registration Screen and Home screen](https://github.com/jante-adebowale/AndroidJwtAuthClient/blob/master/screenshots/light.png?raw=true "Screenshot showing Login, Registration Screen and Home screen")
### 📸 Screenshots (Dark theme)
![Screenshot showing Login, Registration Screen and Home screen](https://github.com/jante-adebowale/AndroidJwtAuthClient/blob/master/screenshots/dark.jpeg?raw=true "Screenshot showing Login, Registration Screen and Home screen")

## 🧰 Tech Stack

- Kotlin
- Retrofit
- Coroutines + Flow
- Type Safe Navigation
- Koin for Dependency Injection
- Jetpack DataStore (for token storage)
- OkHttp Interceptor (for token refreshing)
- Jetpack Compose (optional UI layer)
- Material 3 (Light & Dark)

## 🔐 Auth Flow

1. Register/Login with Ktor backend.
2. Save access + refresh tokens securely.
3. Automatically add `Authorization: Bearer <token>` headers.
4. Detect 401, use refresh token to get a new access token.

## 🛡️ Token Storage
- Access token & Refresh token are stored securely using Jetpack Security's EncryptedSharedPreferences.

**Connect with me on:**
* [Portfolio](https://www.janteadebowale.com)
* [Youtube](https://www.youtube.com/@jante-adebowale)
* [LinkedIn](https://www.linkedin.com/in/jante-adebowale)
* [Github](https://github.com/jante-adebowale)


