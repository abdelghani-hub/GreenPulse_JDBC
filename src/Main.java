import models.User;
import services.ConsumptionService;
import services.UserService;
import utils.ConsoleUI;

import java.util.List;

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

    public static void showUser(){
        User user = userService.showUser();
        if (user != null) consumptionService.showUserConsumptions(user);
    }
    public static void listAllUsers(){
        List<User> users = userService.listAllUsers();
        users.forEach(user -> {
            System.out.println(user);
            consumptionService.showUserConsumptions(user);
        });
    }


}
