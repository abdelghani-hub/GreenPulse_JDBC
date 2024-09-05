import models.User;
import services.CarbonConsumptionService;
import services.UserService;
import utils.ConsoleUI;

public class Main {
    private static final UserService userService = new UserService();

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

    private static void updateUser() {
        User user = userService.getUser();
        userService.updateUser(user);
    }

    private static void deleteUser() {
        User user = userService.getUser();
        ConsoleUI.displayWarningMessage("Are you sur you want to delete this user (Y/N) : ");
        String conf = ConsoleUI.scanner.nextLine();
        if (conf.equalsIgnoreCase("y")) {
            userService.deleteUser(user.getId());
        } else {
            ConsoleUI.displaySuccessMessage("Operation has been canceled successfully.");
        }
    }

    private static void viewUser() {
        User user = userService.getUser();
        userService.showUser(user);
    }

    // Add Carbon Consumption
    public static void addCarbonConsumption() {
        User user = userService.getUser();
        CarbonConsumptionService.addCarbonConsumption(user);
    }

    // Generate Carbon Consumption Report
    public static void generateCarbonReport() {
        User user = userService.getUser();
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
    }
}
