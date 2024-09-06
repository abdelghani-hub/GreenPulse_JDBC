package models;

import enumerations.ConsumptionType;
import enumerations.EnergyType;

import java.time.LocalDate;

public class Housing extends Consumption {

    private final Double GAS_IMPACT = 2.0;
    private final Double ELECTRICITY_IMPACT = 1.5;  

    private EnergyType energyType;
    private double energyConsumption;

    public Housing(){}

    public Housing(int quantity, LocalDate startDate, LocalDate endDate, EnergyType energyType, Double energyConsumption) {
        super(quantity, startDate, endDate);
        this.energyType = energyType;
        this.energyConsumption = energyConsumption;
        this.consumptionType = ConsumptionType.HOUSING;
    }

    // Getters
    public EnergyType getEnergyType() {
        return energyType;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    // Setters
    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    // calculate impact
    @Override
    Double calculateImpact() {
        return this.quantity * this.energyConsumption * (this.energyType.equals(EnergyType.GAS) ? GAS_IMPACT : ELECTRICITY_IMPACT);
    }
}
