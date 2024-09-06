package models;

import enumerations.ConsumptionType;
import enumerations.TransportType;

import java.time.LocalDate;

public class Transport extends Consumption {

    private final double CAR_IMPACT = 0.5;
    private final double TRAIN_IMPACT = 0.1;
    private double distanceTravelled;
    private TransportType type;

    public Transport(double distanceTravelled, String type){}

    public Transport(int quantity, LocalDate startDate, LocalDate endDate, double distanceTravelled, TransportType type) {
        super(quantity, startDate, endDate);
        this.distanceTravelled = distanceTravelled;
        this.type = type;
        this.setConsumptionType(ConsumptionType.TRANSPORT);
    }

    // Getters
    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public TransportType getType() {
        return type;
    }

    // Setter
    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    // calculate impact
    @Override
    Double calculateImpact() {
        return this.quantity * this.distanceTravelled * (this.type.equals(TransportType.CAR) ? CAR_IMPACT : TRAIN_IMPACT);
    }
}
