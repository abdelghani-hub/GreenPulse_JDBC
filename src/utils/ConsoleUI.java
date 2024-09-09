package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String ORANGE = "\u001B[38;5;214m";
    public static final Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        System.out.print(
                "\n |=================================|" +
                        "\n |  " + ORANGE + "Carbon Consumption Management" + RESET + "  |" +
                        "\n |=================================|" +
                        "\n | 1. Create User                  |" +
                        "\n | 2. Update User                  |" +
                        "\n | 3. Delete User                  |" +
                        "\n | 4. Show User                    |" +
                        "\n | 5. Show All users               |" +
                        "\n | 6. Add Carbon Consumption       |" +
                        "\n | 7. Generate Carbon report       |" +
                        "\n |" + BLUE + " 8. Exit" + RESET + "                         |" +
                        "\n |_________________________________|" +
                        "\n  Enter your choice : "
        );
    }

    public static void displayReportMenu() {
        System.out.print(
                "\n\t |----------------------|" +
                "\n\t | 1. Daily Report      |" +
                "\n\t | 2. Weekly Report     |" +
                "\n\t | 3. Monthly Report    |" +
                "\n\t |______________________|" +
                "\n\t\tEnter report choice : "
        );
    }

    public static void printError(String message) {
        System.err.println("Error: " + message);
    }

    public static void printSuccess(String message) {
        System.out.println(GREEN + "\nSuccess: " + message + RESET);
    }

    public static void printWarning(String message) {
        System.out.print(YELLOW + "\nWarning: " + message + RESET);
    }

    // Read Local Date
    public static LocalDate readLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;
        boolean valid = false;

        while (!valid) {
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input, formatter);
                valid = true;
            } catch (DateTimeParseException e) {
                ConsoleUI.printError("Invalid format. Please try again ( dd/mm/YYYY ) : ");
            }
        }
        return date;
    }

    // Method for handling the Input Mismatch Exception
    public static int readInt(String prompt) {
        int result = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                result = scanner.nextInt();
                validInput = true;
            } else {
                printError("Invalid input. Please enter a valid integer.");
            }
            scanner.nextLine();
        }

        return result;
    }
    // Method for handling the Input Mismatch Exception
    public static double readDouble(String prompt) {
        double result = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                result = scanner.nextDouble();
                validInput = true;
            } else {
                printError("Invalid input. Please enter a valid Double.");
            }
            scanner.nextLine();
        }
        return result;
    }

    public static String readChoice(String prompt, Map<String, String> choices) {
        StringBuilder choicesSTR = new StringBuilder("\t|------------------|\n");
        for (Map.Entry<String, String> choice : choices.entrySet()) {
            choicesSTR.append(choice.getKey())
                    .append(". ")
                    .append(choice.getValue())
                    .append("\n|__________________|\n")
                    .append(prompt);
        }
        while (true) {
            System.out.print(choicesSTR);
            String choice = scanner.nextLine().trim();
            if (!choices.containsValue(choice))
                printError("invalid choice!");
            else
                return choice;
        }
    }
}
