import services.MainService;
import utils.ConsoleUI;

public class Main {
    public static void main(String[] args) {

        final MainService mainService = new MainService();

        boolean running = true;

        while (running) {
            ConsoleUI.displayMenu();
            String choice = ConsoleUI.scanner.nextLine();

            switch (choice) {
                case "0":
                    mainService.userCRUD("create");
                    break;
                case "1":
                    mainService.userCRUD("edit");
                    break;
                case "2":
                    mainService.userCRUD("delete");
                    break;
                case "3":
                    mainService.showUser();
                    break;
                case "4":
                    mainService.listAllUsers();
                    break;
                case "5":
                    mainService.addConsumption();
                    break;
                case "6":
                    mainService.showActiveUsers();
                    break;
                case "7":
                    mainService.getImpactAverage();
                    break;
                case "8":
                    mainService.showInactiveUsers();
                    break;
                case "9":
                    mainService.sortUsersByConsumption();
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
}
