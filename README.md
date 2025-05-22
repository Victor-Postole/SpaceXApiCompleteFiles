# SpaceXApi

🚀 Project Description
This project is a lightweight Android application that demonstrates how to consume the SpaceX API using Clean Architecture, SOLID principles, and Jetpack Compose.

The app retrieves and displays real-time SpaceX launch data — including mission details, rocket information, and launch success status — using a Composable UI backed by coroutines and Flow for reactive state handling.

✨ Features
📡 Fetches SpaceX company info and a list of all launches from the remote API

🔄 Supports filtering launches by:

Launch year

Launch success (Success / Failure / All)

Sort order (ASC / DESC)

✅ Clean separation of concerns with:

domain, data, and presentation layers

ViewModel managing state via State<T>

🎨 Built with Jetpack Compose for modern declarative UI

🧠 Technologies Used
Kotlin

Jetpack Compose

Coroutines & Flow

Koin for Dependency Injection

SOLID + Clean Architecture

📈 Potential Improvements:
Introduce the MVI pattern to handle UI events such as screen refreshes or forced updates.

Add a Room database for offline capabilities and to control when API calls are triggered (e.g., on screen refresh).

Improve the repository logic in SpaceXAPIRepositoryImpl by deciding whether to use local cache or API, and move this decision-making outside the ViewModel.
![image](https://github.com/user-attachments/assets/a63938b5-ed68-4da1-94eb-55d69e3fd056)
![image](https://github.com/user-attachments/assets/ad7c1542-0c54-47b0-997f-cd838f5e1aae)
![image](https://github.com/user-attachments/assets/f239ea21-a837-4e17-843d-3f162c5332cf)


