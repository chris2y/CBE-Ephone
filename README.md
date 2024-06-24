# CBE SMS Parser App

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

## Screenshots

## Screenshots

<img src="https://github.com/chris2y/CBE-Ephone/assets/105220772/ef6a79d4-549c-4f7c-803e-bb7000180247" alt="Screenshot 1" width="300"/>

<img src="https://github.com/chris2y/CBE-Ephone/assets/105220772/9b5c8c03-ff46-42e3-82f4-c321336ec244" alt="Screenshot 3" width="300"/>

<img src="https://github.com/chris2y/CBE-Ephone/assets/105220772/8c506328-a08f-4981-99f6-a8821992d1d1" alt="Screenshot 2" width="300"/>

## Description

CBE SMS Parser App is an Android application designed to parse SMS messages from the Commercial Bank of Ethiopia (CBE). It provides users with detailed transaction history and account balance trends directly from SMS notifications. The app automatically extracts transaction details and visualizes account balance changes over time, offering real-time updates as new SMS messages are received.

## Features

- **SMS Parsing**: Automatically parses SMS messages from CBE for transaction details.
- **Transaction History**: Displays parsed transactions in a list view.
- **Account Balance Trends**: Shows account balance trends using a line chart.
- **Real-Time Updates**: Updates transaction history and balance trends instantly upon receiving new SMS messages.

## Components

1. **ParsedSmsDataClass**: Data model for parsed SMS information.
2. **SmsParser**: Extracts transaction details from SMS body text.
3. **SmsRepository**: Retrieves SMS messages from the device's inbox.
4. **BalanceChartFragment**: Displays a line chart of account balance over time.
5. **ParsedSmsAdapter**: RecyclerView adapter for the transaction list.
6. **ParsedSmsFragment**: Fragment showing the list of parsed SMS messages.
7. **MainActivity**: Handles permissions, navigation, and SMS content observation.
8. **MainViewModel**: Manages parsed SMS data for UI components.

## Installation

To run this project locally, follow these steps:

1. **Clone the repository:**

    ```bash
    git clone https://github.com/chris2y/cbe-sms-parser-app.git
    ```

2. **Open the project in Android Studio:**

    - Launch Android Studio
    - Select `Open an existing Android Studio project`
    - Navigate to the cloned directory and select it

3. **Build and run the project:**

    - Click on the `Run` button in Android Studio to build and run the project on an emulator or a physical device

## Permissions

The app requires the following permission:
- `READ_SMS`: To access and parse SMS messages

## Libraries Used

- AndroidX
- Lifecycle components
- Navigation component
- MPAndroidChart for charting

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern and incorporates modern Android development practices:
- LiveData for data observation
  
## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or feedback, please feel free to contact:

- **Email:** fissehachristian@gmail.com
- **GitHub:** [chris2y](https://github.com/chris2y)

---

This README provides a comprehensive overview of the CBE SMS Parser App, including its features, installation instructions, architecture, and guidelines for contribution. Adjust the contact details and specific sections as needed to match your project's requirements.
