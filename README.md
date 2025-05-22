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


![Screenshot 2025-05-23 at 00 33 31](https://github.com/user-attachments/assets/49c1142a-6ccb-400d-a67e-0ab782a7e72c)
![Screenshot 2025-05-23 at 00 33 44](https://github.com/user-attachments/assets/e53fd249-b900-47a8-870f-77bce71f8dc1)
![Screenshot 2025-05-23 at 00 34 04](https://github.com/user-attachments/assets/f8635015-feb0-4d85-8d70-8bec6f1cabc6)
