/*
package services;

import models.CarbonConsumption;
import models.User;
import utils.ConsoleUI;
import utils.DateUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CarbonConsumptionService {

    public void create(User user) {
        int quantity = ConsoleUI.readInt("\tEnter the Quantity : ");
        LocalDate startDate;
        LocalDate endDate;
        while (true) {
            System.out.print("\tEnter the Start Date (dd/mm/YYYY) : ");
            startDate = ConsoleUI.readLocalDate();

            System.out.print("\tEnter the End Date (dd/mm/YYYY) : ");
            endDate = ConsoleUI.readLocalDate();

            if (endDate.isBefore(startDate))
                ConsoleUI.displayErrorMessage("Invalid Period! The end date should be after the start date!.");
            else if (!DateUtil.isNoCommonDate(startDate, endDate, )) // TODO : days keys
                ConsoleUI.displayErrorMessage("Date range overlaps with an existing period!");
            else
                break;
        }
        String choice = ConsoleUI.getConsumptionTypeChoice();
        switch (choice) {
            case "1" :
                TransportService transSer = new TransportService();
                transSer.create(quantity, startDate, endDate);
        }
//        user.addCarbonConsumption(carbonConsumption);
        ConsoleUI.displaySuccessMessage("The Consumption has been added successfully.");
    }

//    public static void generateDailyReport(User user) {
//        ArrayList<CarbonConsumption> cc = user.getCarbonConsumption();
//        cc.sort(Comparator.comparing(CarbonConsumption::getStartDate)); // Sort by start date
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
//        user.printLine();
//
//        for (CarbonConsumption item : cc) {
//            TreeMap<LocalDate, Double> dailyConsumptionMap = item.getDailyConsumptionMap();
//
//            for (HashMap.Entry<LocalDate, Double> entry : dailyConsumptionMap.entrySet()) {
//                System.out.println("\t" + entry.getKey().format(formatter) + " : " + String.format("%.2f", entry.getValue()));
//            }
//        }
//    }
//
//    public static void generateWeeklyReport(User user) {
//        ArrayList<CarbonConsumption> cc = user.getCarbonConsumption();
//        cc.sort(Comparator.comparing(CarbonConsumption::getStartDate));
//        DateTimeFormatter formFormatter = DateTimeFormatter.ofPattern("d MMM");
//        DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
//
//        user.printLine();
//
//        TreeMap<LocalDate, Double> weeklyConsumptionMap = new TreeMap<>();
//        for (CarbonConsumption item : cc) {
//            TreeMap<LocalDate, Double> dailyConsumptionMap = item.getDailyConsumptionMap();
//
//            for (LocalDate day : dailyConsumptionMap.keySet()) {
//                // Find the Monday of the current week
//                LocalDate weekStart = day.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//
//                // Weekly total
//                weeklyConsumptionMap.put(weekStart,weeklyConsumptionMap.getOrDefault(weekStart, 0.0) + dailyConsumptionMap.get(day));
//            }
//        }
//        // Print the report
//        for (LocalDate week : weeklyConsumptionMap.keySet()) {
//            LocalDate weekEnd = week.plusDays(6);
//            System.out.println(
//                "\t" + week.format(formFormatter) + " to " + weekEnd.format(toFormatter) + " : "
//                + String.format("%.2f", weeklyConsumptionMap.get(week))
//            );
//        }
//    }
//
//    public static void generateMonthlyReport(User user) {
//        ArrayList<CarbonConsumption> cc = user.getCarbonConsumption();
//        cc.sort(Comparator.comparing(CarbonConsumption::getStartDate));
//        DateTimeFormatter formFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
//
//        user.printLine();
//
//        TreeMap<LocalDate, Double> monthlyConsumptionMap = new TreeMap<>();
//        for (CarbonConsumption item : cc) {
//            TreeMap<LocalDate, Double> dailyConsumptionMap = item.getDailyConsumptionMap();
//
//            for (LocalDate day : dailyConsumptionMap.keySet()) {
//                // Find first day of month
//                LocalDate month = day.with(TemporalAdjusters.firstDayOfMonth());
//
//                // Monthly total
//                monthlyConsumptionMap.put(month,monthlyConsumptionMap.getOrDefault(month, 0.0) + dailyConsumptionMap.get(day));
//            }
//        }
//
//        // print
//        for (HashMap.Entry<LocalDate, Double> entry : monthlyConsumptionMap.entrySet()) {
//            System.out.println(
//                    "\t" + entry.getKey().format(formFormatter) + " : "
//                            + String.format("%.2f", entry.getValue())
//            );
//        }
//    }

    // Map for each day - 'day' as key and 'average' as value
    public TreeMap<LocalDate, Double> getDailyConsumptionMap(CarbonConsumption cc) {
        TreeMap<LocalDate, Double> dailyConsumptionMap = new TreeMap<>();
        LocalDate currentDay = cc.getStartDate();

        double dailyAVG = calculateDailyAverage(cc);
        while (!currentDay.isAfter(cc.getEndDate())) {
            dailyConsumptionMap.put(currentDay, dailyAVG);
            currentDay = currentDay.plusDays(1);
        }
        return dailyConsumptionMap;
    }

    // Average carbon consumption per day
    public double calculateDailyAverage(CarbonConsumption cc) {
        long days = ChronoUnit.DAYS.between(cc.getStartDate(), cc.getEndDate()) + 1;
        return (double) cc.getQuantity() / days;
    }
}
*/
