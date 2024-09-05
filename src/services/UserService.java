package services;

import models.CarbonConsumption;
import models.User;
import utils.ConsoleUI;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class UserService {
    // A HashMap to store users with their uniqueID as the key
    protected HashMap<Integer, User> users;
    private static final Scanner scanner = new Scanner(System.in);

    // Construct
    public UserService() {
        this.users = new HashMap<>();
        seeder();
    }

    // Method to add a new user
    public void addUser() {
        String name;
        while (true) {
            System.out.print("\nEnter name : ");
            name = ConsoleUI.scanner.nextLine();
            if (name.length() < 3)
                ConsoleUI.displayErrorMessage("Name must not be empty!");
            else
                break;
        }

        int age = ConsoleUI.readInt("Enter age  : ");

        User user = new User(name, age);
        if (users.containsKey(user.getId())) {
            ConsoleUI.displayErrorMessage("ID already exists, Try an other one.");
        } else {
            users.put(user.getId(), user);
            ConsoleUI.displaySuccessMessage("User added successfully.");
        }
    }

    // Method to retrieve a user by their unique ID
    public User getUser() {
        while (true) {
            int id = ConsoleUI.readInt("\nEnter user id : ");
            User user = users.get(id);
            if (user != null)
                return user;
            else
                ConsoleUI.displayErrorMessage("User not found !");
        }
    }

    // Method to update an existing user
    public void updateUser(User user) {
        System.out.print("Enter new name (leave blank to keep current) : ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            user.setName(name);
        }

        System.out.print("Enter new age (leave blank to keep current) : ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) {
            int age = Integer.parseInt(ageInput);
            user.setAge(age);
        }

        ConsoleUI.displaySuccessMessage("User updated successfully!");
    }

    // Method to delete a user by their unique ID
    public void deleteUser(int id) {
        if (users.containsKey(id)) {
            users.remove(id);
            ConsoleUI.displaySuccessMessage("User deleted successfully.");
        } else
            ConsoleUI.displayErrorMessage("Server Error!");
    }

    // Show single user
    public void showUser(User user) {
        System.out.print(user);
    }

    // List all users
    public void listAllUsers() {
        if (users.isEmpty()) {
            ConsoleUI.displayErrorMessage("No users available.");
        } else {
            for (User user : users.values()) {
                showUser(user);
            }
        }
    }

    // Seeder
    private void seeder() {
        User user1 = new User("Alice", 30);
        user1.addCarbonConsumption(new CarbonConsumption(100, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)));
        user1.addCarbonConsumption(new CarbonConsumption(150, LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 15)));
        users.put(user1.getId(), user1);

        User user2 = new User("Bob", 25);
        user2.addCarbonConsumption(new CarbonConsumption(200, LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 5)));
        user2.addCarbonConsumption(new CarbonConsumption(50, LocalDate.of(2024, 2, 10), LocalDate.of(2024, 2, 12)));
        users.put(user2.getId(), user2);

        User user3 = new User("Charlie", 35);
        user3.addCarbonConsumption(new CarbonConsumption(80, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 3)));
        user3.addCarbonConsumption(new CarbonConsumption(120, LocalDate.of(2024, 3, 5), LocalDate.of(2024, 3, 7)));
        users.put(user3.getId(), user3);

        User user4 = new User("David", 28);
        user4.addCarbonConsumption(new CarbonConsumption(140, LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 5)));
        user4.addCarbonConsumption(new CarbonConsumption(90, LocalDate.of(2024, 4, 10), LocalDate.of(2024, 4, 12)));
        users.put(user4.getId(), user4);

        User user5 = new User("Eve", 40);
        user5.addCarbonConsumption(new CarbonConsumption(200, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 5)));
        user5.addCarbonConsumption(new CarbonConsumption(300, LocalDate.of(2024, 5, 6), LocalDate.of(2024, 5, 10)));
        users.put(user5.getId(), user5);
    }
}
