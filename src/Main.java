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
                case "0":
                    userService.addUser();
                    break;
                case "1":
                    userService.edit();
                    break;
                case "2":
                    userService.delete();
                    break;
                case "3":
                    showUser();
                    break;
                case "4":
                    listAllUsers();
                    break;
                case "5":
                    consumptionService.create();
                    break;
                case "6":
                    showActiveUsers();
                    break;
                case "7":
                    getImpactAverage();
                    break;
                case "8":
                    showInactiveUsers();
                    break;
                case "9":
                    sortUsersByConsumption();
                    break;
                case "#":
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

    // Sort Users By Consumption
    public static void sortUsersByConsumption() {
        List<User> users = userService.listAllUsers();
        if (users.isEmpty()) {
            ConsoleUI.printError("No users available.");
            return;
        }
        users.sort((u1, u2) -> {
            Double u1Total = consumptionService.getConsumptionsTotal(u1);
            Double u2Total = consumptionService.getConsumptionsTotal(u2);
            return u2Total.compareTo(u1Total);
        });
        users.forEach(user -> {
            System.out.println(user);
            System.out.println("\tConsumptions : " + ConsoleUI.BLUE + String.format("%.2f", consumptionService.getConsumptionsTotal(user)) + ConsoleUI.RESET + " CO2eq");
        });
    }
}
