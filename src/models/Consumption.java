package models;

import enumerations.ConsumptionType;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Consumption {
    protected final int id;
    protected int quantity;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected ConsumptionType consumptionType;

    abstract Double calculateImpact();
    private static final AtomicInteger idCounter = new AtomicInteger();

    private int generateUniqueID() {
        return idCounter.incrementAndGet();
    }

    public Consumption() {
        this.id = generateUniqueID();
    }
    public Consumption(int quantity, LocalDate startDate, LocalDate endDate) {
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

    public ConsumptionType getConsumptionType() {
        return consumptionType;
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

    public void setConsumptionType(ConsumptionType consumptionType) {
        this.consumptionType = consumptionType;
    }
}