package services;

import models.*;
import repositories.UserRepository;
import utils.ConsoleUI;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepo;

    // Construct
    public UserService() {
        this.userRepo = new UserRepository();
    }

    // Method to add a new user
    public void addUser() {
        String name;
        while (true) {
            System.out.print("\nEnter name : ");
            name = ConsoleUI.scanner.nextLine();
            if (name.length() < 1)
                ConsoleUI.printError("Name must not be empty!");
            else
                break;
        }

        int age = ConsoleUI.readInt("Enter age  : ");

        User user = new User(name, age);
        Optional<User> userOptional = userRepo.save(user);
        if (userOptional.isPresent())
            ConsoleUI.printSuccess("User added successfully.");
        else
            ConsoleUI.printError("Failed !");
    }

    // Method to retrieve a user by their unique ID
    public User getUser() {
        System.out.print("\nEnter user id : ");
        String id = ConsoleUI.scanner.nextLine();
        Optional<User> userFound = userRepo.find(id);
        return userFound.orElse(null);
    }

    // Method to update an existing user
    public void update(User user) {
        System.out.print("Enter new name (leave blank to keep current): ");
        String name = ConsoleUI.scanner.nextLine().trim();
        if (!name.isEmpty()) {
            user.setName(name);
        }

        System.out.print("Enter new age (leave blank to keep current): ");
        String ageInput = ConsoleUI.scanner.nextLine();
        if (!ageInput.isEmpty()) {
            try {
                int age = Integer.parseInt(ageInput);
                user.setAge(age);
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Invalid age format. Please enter a valid number.");
                return;
            }
        } else if (name.isEmpty())
            return; // No change to submit

        Optional<User> optionalUpdatedUser = userRepo.update(user);
        if (optionalUpdatedUser.isPresent()) {
            System.out.print(optionalUpdatedUser.get());
            ConsoleUI.printSuccess("User updated successfully!");
            return;
        }
        ConsoleUI.printError("Server Error!");
    }

    public void edit() {
        User user = getUser();
        if (user != null) {
            System.out.println(user);
            update(user);
        } else
            ConsoleUI.printError("User not found !");
    }

    // Method to delete a user by their unique ID
    public void remove(String id) {
        Optional<User> user = userRepo.find(id);
        if (user.isPresent()) {
            userRepo.remove(user.get());
            ConsoleUI.printSuccess("User deleted successfully.");
        } else
            ConsoleUI.printError("Server Error!");
    }

    //
    public void delete() {
        User user = getUser();
        if (user == null) ConsoleUI.printError("User not found !");
        else {
            System.out.println(user);
            ConsoleUI.printWarning("Are you sur you want to delete this user (Y/N) : ");
            String conf = ConsoleUI.scanner.nextLine();
            if (conf.equalsIgnoreCase("y")) {
                remove(user.getId());
            } else {
                ConsoleUI.printSuccess("Operation has been canceled successfully.");
            }
        }
    }

    // Show single user
    public User showUser() {
        User user = getUser();
        if (user != null){
            System.out.println(user);
            return user;
        }
        ConsoleUI.printError("User not found !");
        return null;
    }

    // List all users
    public List<User> listAllUsers() {
        return userRepo.findAll();
    }
}
