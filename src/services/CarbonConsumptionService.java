package services;

import models.CarbonConsumption;
import models.User;
import utils.ConsoleUI;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class CarbonConsumptionService {

    public static void addCarbonConsumption(User user) {
        CarbonConsumption carbonConsumption = new CarbonConsumption();

        int quantity = ConsoleUI.readInt("\tEnter the Quantity : ");
        carbonConsumption.setQuantity(quantity);

        while (true) {
            System.out.print("\tEnter the Start Date (dd/mm/YYYY) : ");
            carbonConsumption.setStartDate(ConsoleUI.readLocalDate());

            System.out.print("\tEnter the End Date (dd/mm/YYYY) : ");
            carbonConsumption.setEndDate(ConsoleUI.readLocalDate());

            if (carbonConsumption.getEndDate().isBefore(carbonConsumption.getStartDate()))
                ConsoleUI.displayErrorMessage("Invalid Period! The end date should be after the start date!.");
            else if (!isValidPeriod(carbonConsumption, user))
                ConsoleUI.displayErrorMessage("Date range overlaps with an existing period!");
            else
                break;
        }

        user.addCarbonConsumption(carbonConsumption);
        ConsoleUI.displaySuccessMessage("The Consumption has been added successfully.");
    }

    public static void generateDailyReport(User user) {
        ArrayList<CarbonConsumption> cc = user.getCarbonConsumption();
        cc.sort(Comparator.comparing(CarbonConsumption::getStartDate)); // Sort by start date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        user.printLine();

        for (CarbonConsumption item : cc) {
            TreeMap<LocalDate, Double> dailyConsumptionMap = item.getDailyConsumptionMap();

            for (HashMap.Entry<LocalDate, Double> entry : dailyConsumptionMap.entrySet()) {
                System.out.println("\t" + entry.getKey().format(formatter) + " : " + String.format("%.2f", entry.getValue()));
            }
        }
    }

    public static void generateWeeklyReport(User user) {
        ArrayList<CarbonConsumption> cc = user.getCarbonConsumption();
        cc.sort(Comparator.comparing(CarbonConsumption::getStartDate));
        DateTimeFormatter formFormatter = DateTimeFormatter.ofPattern("d MMM");
        DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");

        user.printLine();

        TreeMap<LocalDate, Double> weeklyConsumptionMap = new TreeMap<>();
        for (CarbonConsumption item : cc) {
            TreeMap<LocalDate, Double> dailyConsumptionMap = item.getDailyConsumptionMap();

            for (LocalDate day : dailyConsumptionMap.keySet()) {
                // Find the Monday of the current week
                LocalDate weekStart = day.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

                // Weekly total
                weeklyConsumptionMap.put(weekStart,weeklyConsumptionMap.getOrDefault(weekStart, 0.0) + dailyConsumptionMap.get(day));
            }
        }
        // Print the report
        for (LocalDate week : weeklyConsumptionMap.keySet()) {
            LocalDate weekEnd = week.plusDays(6);
            System.out.println(
                "\t" + week.format(formFormatter) + " to " + weekEnd.format(toFormatter) + " : "
                + String.format("%.2f", weeklyConsumptionMap.get(week))
            );
        }
    }

    public static void generateMonthlyReport(User user) {
        ArrayList<CarbonConsumption> cc = user.getCarbonConsumption();
        cc.sort(Comparator.comparing(CarbonConsumption::getStartDate));
        DateTimeFormatter formFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");

        user.printLine();

        TreeMap<LocalDate, Double> monthlyConsumptionMap = new TreeMap<>();
        for (CarbonConsumption item : cc) {
            TreeMap<LocalDate, Double> dailyConsumptionMap = item.getDailyConsumptionMap();

            for (LocalDate day : dailyConsumptionMap.keySet()) {
                // Find first day of month
                LocalDate month = day.with(TemporalAdjusters.firstDayOfMonth());

                // Monthly total
                monthlyConsumptionMap.put(month,monthlyConsumptionMap.getOrDefault(month, 0.0) + dailyConsumptionMap.get(day));
            }
        }

        // print
        for (HashMap.Entry<LocalDate, Double> entry : monthlyConsumptionMap.entrySet()) {
            System.out.println(
                    "\t" + entry.getKey().format(formFormatter) + " : "
                            + String.format("%.2f", entry.getValue())
            );
        }
    }

    public static boolean isValidPeriod(CarbonConsumption carbonConsumption, User user) {
        Set<LocalDate> userEnteredDates = new HashSet<>();
        // Existing CarbonConsumption dates
        for (CarbonConsumption cc : user.getCarbonConsumption()) {
            userEnteredDates.addAll(cc.getDailyConsumptionMap().keySet());
        }
        // Dates of the created carbon consumption
        Set<LocalDate> newConsumptionDates = new HashSet<>(carbonConsumption.getDailyConsumptionMap().keySet());
        // Comparing
        newConsumptionDates.retainAll(userEnteredDates);
        return newConsumptionDates.isEmpty();
    }
}
