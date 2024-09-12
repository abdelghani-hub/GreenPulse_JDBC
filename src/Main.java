import models.User;
import services.ConsumptionService;
import services.UserService;
import utils.ConsoleUI;
import utils.DateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final UserService userService = new UserService();
    private static final ConsumptionService consumptionService = new ConsumptionService();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            ConsoleUI.displayMenu();
            String choice = ConsoleUI.scanner.nextLine();

            switch (choice) {
                case "1":
                    userService.addUser();
                    break;
                case "2":
                    userService.edit();
                    break;
                case "3":
                    userService.delete();
                    break;
                case "4":
                    showUser();
                    break;
                case "5":
                    listAllUsers();
                    break;
                case "6":
                    consumptionService.create();
                    break;
                case "7":
                    showActiveUsers();
                    break;
                case "8":
                    getImpactAverage();
                    break;
                case "9":
                    showInactiveUsers();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting the application...");
                    break;
                default:
                    ConsoleUI.printError("Invalid choice. Please try again.");
            }
        }
    }

    // Show User infos
    public static void showUser() {
        User user = userService.showUser();
        if (user != null) consumptionService.showUserConsumptions(user);
    }

    // List all users
    public static void listAllUsers() {
        List<User> users = userService.listAllUsers();
        if (users.isEmpty()) {
            ConsoleUI.printError("No users available.");
            return;
        }
        users.forEach(user -> {
            System.out.println(user);
            consumptionService.showUserConsumptions(user);
        });
    }

    // Show active users by min amount
    public static void showActiveUsers() {
        Double minAmount = ConsoleUI.readDouble("Enter the minimum amount : ");
        List<User> users = userService.listAllUsers().stream()
                .filter(user -> consumptionService.getConsumptionsTotal(user) >= minAmount)
                .collect(Collectors.toList());
        if (users.isEmpty()) {
            ConsoleUI.printError("No active users found.");
        } else {
            users.forEach(user -> {
                System.out.println(user);
                consumptionService.showUserConsumptions(user);
            });
        }
    }

    // Get impact average for a user in a specified period
    public static void getImpactAverage() {
        User user = userService.getUser();
        if (user == null) {
            ConsoleUI.printError("User not found!");
            return;
        }
        LocalDate startDate = ConsoleUI.readLocalDate("Enter start date : ");
        LocalDate endDate = ConsoleUI.readLocalDate("Enter end date : ");

        // validate period
        if (endDate.isBefore(startDate)) {
            ConsoleUI.printError("Invalid Period! The end date should be after the start date!.");
            return;
        }

        // Show impact average
        consumptionService.showAveragesByPeriod(user, startDate, endDate);
    }

    // Show inactive users
    public static void showInactiveUsers() {

        LocalDate startDate = ConsoleUI.readLocalDate("Enter start date : ");
        LocalDate endDate = ConsoleUI.readLocalDate("Enter end date : ");
        // validate period
        if (endDate.isBefore(startDate)) {
            ConsoleUI.printError("Invalid Period! The end date should be after the start date!.");
            return;
        }

        System.out.println("Inactive users " + startDate + " - " + endDate + " : ");
        userService.listAllUsers()
                .stream()
                .filter(
                        u -> consumptionService.getUserConsumptions(u)
                                .stream()
                                .allMatch(
                                        c -> DateUtil.isNoCommonDate(startDate, endDate, DateUtil.getDatesBetween(c.getStartDate(), c.getEndDate()))
                                )
                )
                .forEach(userRes -> {
                    System.out.println(userRes);
                    consumptionService.showUserConsumptions(userRes);
                });
    }
}
