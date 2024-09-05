package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CarbonConsumption {
    private final int id;
    private int quantity;
    private LocalDate startDate;
    private LocalDate endDate;
    private static final AtomicInteger idCounter = new AtomicInteger();

    private int generateUniqueID() {
        return idCounter.incrementAndGet();
    }

    public CarbonConsumption() {
        this.id = generateUniqueID();
    }
    public CarbonConsumption(int quantity, LocalDate startDate, LocalDate endDate) {
        this.id = generateUniqueID();
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    // Setters
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // Map for each day of the Consumption period
    public TreeMap<LocalDate, Double> getDailyConsumptionMap() {
        TreeMap<LocalDate, Double> dailyConsumptionMap = new TreeMap<>();
        LocalDate currentDay = startDate;

        while (!currentDay.isAfter(endDate)) {
            dailyConsumptionMap.put(currentDay, this.calculateDailyAverage());
            currentDay = currentDay.plusDays(1);
        }
        return dailyConsumptionMap;
    }

    // Average carbon consumption per day
    public double calculateDailyAverage() {
        long days = ChronoUnit.DAYS.between(this.startDate, this.endDate) + 1;
        return (double) this.quantity / days;
    }
}
