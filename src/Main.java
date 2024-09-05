import models.User;
import services.CarbonConsumptionService;
import services.UserService;
import utils.ConsoleUI;

public class Main {
    private static final UserService userService = new UserService();

    public static void main(String[] args) {

        // De-Serialization

        boolean running = true;

        while (running) {
            ConsoleUI.displayMenu();
            String choice = ConsoleUI.scanner.nextLine();

            switch (choice) {
                case "1":
                    createUser();
                    break;
                case "2":
                    updateUser();
                    break;
                case "3":
                    deleteUser();
                    break;
                case "4":
                    viewUser();
                    break;
                case "5":
                    userService.listAllUsers();
                    break;
                case "6":
                    addCarbonConsumption();
                    break;
                case "7":
                    generateCarbonReport();
                    break;
                case "8":
                    running = false;
                    System.out.println("Exiting the application...");
                    break;
                default:
                    ConsoleUI.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }

    private static void createUser() {
        System.out.print("\nEnter name : ");
        String name = ConsoleUI.scanner.nextLine();

        int age = ConsoleUI.readInt("Enter age  : ");

        User user = new User(name, age);
        userService.addUser(user);
    }

    private static void updateUser() {
        int id = ConsoleUI.readInt("\nEnter user id : ");
        User user = userService.getUser(id);

        if (user != null) {
            userService.updateUser(user);
        } else {
            ConsoleUI.displayErrorMessage("User not found.");
        }
    }

    private static void deleteUser() {
        int id = ConsoleUI.readInt("Enter user id : ");
        if (userService.getUser(id) != null) {
            ConsoleUI.displayWarningMessage("Are you sur you want to delete this user (Y/N) : ");
            String conf = ConsoleUI.scanner.nextLine();
            if (conf.equalsIgnoreCase("y")) {
                boolean isDeleted = userService.deleteUser(id);
                if (isDeleted) ConsoleUI.displaySuccessMessage("User deleted successfully.");
                else ConsoleUI.displayErrorMessage("Server Error!");
            } else {
                ConsoleUI.displaySuccessMessage("Operation has been canceled successfully.");
            }
        } else {
            ConsoleUI.displayErrorMessage("User not found!");
        }
    }

    private static void viewUser() {
        int id = ConsoleUI.readInt("\nEnter user id : ");
        User user = userService.getUser(id);

        if (user != null) {
            userService.showUser(user);
        } else {
            ConsoleUI.displayErrorMessage("User not found!");
        }
    }

    // Add Carbon Consumption
    public static void addCarbonConsumption() {
        int userID = ConsoleUI.readInt("\nEnter the user id : ");
        User user = userService.getUser(userID);

        if (user != null) {
            CarbonConsumptionService.addCarbonConsumption(user);
        } else {
            ConsoleUI.displayErrorMessage("User not found!");
        }
    }

    // Generate Carbon Consumption Report
    public static void generateCarbonReport() {
        int userID = ConsoleUI.readInt("Enter the user id : ");
        User user = userService.getUser(userID);
        if (user != null) {
            ConsoleUI.displayReportMenu();
            String reportChoice = ConsoleUI.scanner.nextLine();
            switch (reportChoice) {
                case "1":
                    CarbonConsumptionService.generateDailyReport(user);
                    break;
                case "2":
                    CarbonConsumptionService.generateWeeklyReport(user);
                    break;
                case "3":
                    CarbonConsumptionService.generateMonthlyReport(user);
                    break;
                default:
                    ConsoleUI.displayErrorMessage("Invalid choice. Please try again.");
            }
        } else {
            ConsoleUI.displayErrorMessage("User not found!");
        }
    }
}
