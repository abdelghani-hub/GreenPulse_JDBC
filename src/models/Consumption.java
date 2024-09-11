package models;

import enumerations.ConsumptionType;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Consumption {
    protected String id;
    protected Double quantity;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected String userId;
    protected ConsumptionType consumptionType;

    public abstract Double calculateImpact();

    private static String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

    public Consumption() {
        this.id = generateUniqueID();
    }
    public Consumption(Double quantity, LocalDate startDate, LocalDate endDate) {
        this.id = generateUniqueID();
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getUserId() {
        return userId;
    }

    public ConsumptionType getConsumptionType() {
        return consumptionType;
    }

    // Setters
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setConsumptionType(ConsumptionType consumptionType) {
        this.consumptionType = consumptionType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", userId='" + userId + '\'' +
                ", consumptionType=" + consumptionType + ", ";
    }
}