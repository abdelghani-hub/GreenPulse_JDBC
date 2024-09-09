import config.DBConnection;
import models.User;
import services.UserService;
import utils.ConsoleUI;

import java.sql.Connection;
import java.sql.SQLException;

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
                    userService.edit();
                    break;
                case "3":
                    userService.delete();
                    break;
                case "4":
                    userService.showUser();
                    break;
                case "5":
                    userService.listAllUsers();
                    break;
                case "6":
                    //addCarbonConsumption();
                    break;
                case "7":
//                    generateCarbonReport();
                    break;
                case "8":
                    running = false;
                    System.out.println("Exiting the application...");
                    break;
                default:
                    ConsoleUI.printError("Invalid choice. Please try again.");
            }
        }
    }



    // Add Carbon Consumption
    /*public static void addCarbonConsumption() {
        User user = userService.getUser();
//        CarbonConsumptionService.create(user);
    }*/

    // Generate Carbon Consumption Report
/*    public static void generateCarbonReport() {
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
    }*/
}
