# Your Financial

## User Story
- List Transaction
    - User can see list of transaction whether it is expanse or revenue
    - User can add transactions and choose the type of transaction: expense or revenue
    - Each transaction has a status : Lunas / Belum Lunas
    - Users can edit their transaction
    - Users can delete their transaction
- Overview
    - Users can see their current balance after all of the transactions

## Architechture Pattern
<img src="readme_assets/mvvm_repository_pattern.png">

## Tech Stack
- [Recyclerview](https://developer.android.com/jetpack/androidx/releases/recyclerview)
- [Glide](https://github.com/bumptech/glide)
- [Lottie Animation](https://github.com/airbnb/lottie-android)
- [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Room](https://developer.android.com/topic/libraries/architecture/room.html)
- [Livecycle & Livedata](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)
- [Koin](https://insert-koin.io/docs/quickstart/android)

## Screenshot
|<img src=readme_assets/img_splash_screen.jpg align="center" height="400" width="200" ></a> |<img src=readme_assets/img_empty_list_transaction.jpg  align="center" height="400" width="200" ></a>|<img src=readme_assets/img_add_transaction.jpg  align="center" height="400" width="200" ></a>|
|:-----------:|:--------:|:--------:|
| Splash Screen | Transaction - Empty List | Transaction - Add Transaction |

|<img src=readme_assets/img_transaction_list_unexpanded.jpg align="center" height="400" width="200" ></a> |<img src=readme_assets/img_transaction_list_expanded.jpg  align="center" height="400" width="200" ></a>|<img src=readme_assets/img_update_or_delete_transaction.jpg  align="center" height="400" width="200" ></a>|
|:-----------:|:--------:|:--------:|
| Transaction - Transaction List (unexpanded) | Transaction - Transaction List (expanded) | Transaction - Update or Delete Transaction |