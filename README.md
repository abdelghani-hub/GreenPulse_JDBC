# Carbon Consumption Management

This project is a simple console application developed to practice and learn the basics of Java. It includes functionalities related to user management, carbon consumption tracking, and reporting.

## Features

- **User Management**: Add, update, and delete users.
- **Carbon Consumption Tracking**: Users can log their carbon consumption over a specific period.
- **Reporting**:
    - **Daily Report**: Generates daily carbon consumption reports for each user.
    - **Weekly Report**: Provides a weekly summary of carbon consumption.
    - **Monthly Report**: Calculates and displays monthly carbon consumption.

## Technologies Used

- **Java**: The primary programming language for this project.
- **Java Collections**: Utilized for managing user data and consumption records.
- **Java Time API**: For handling dates and periods.

## Project Structure

- `models`: Contains the `User` and `CarbonConsumption` classes.
- `services`: Contains the `CarbonConsumptionService` class, which handles the main logic of the application.
- `utils`: Contains utility classes like `ConsoleUI` for handling console input/output.

## How to Run

1. Clone the repository.
2. Open the project in your preferred IDE.
3. Run the `Main` class to start the application.

## Usage

- **Add a User**: Input user details and create a new user.
- **Add Carbon Consumption**: Log carbon consumption for a user over a specific period.
- **Generate Reports**: View daily, weekly, or monthly reports to track carbon consumption.

## Note

This project is intended for learning purposes only. It was created to practice Java basics and may not follow best practices for production code.
