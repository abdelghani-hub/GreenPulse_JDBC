package models;

import enumerations.ConsumptionType;
import enumerations.FoodType;

import java.time.LocalDate;

public class Food extends Consumption {

    private Double MEAT_IMPACT = 5.0;
    private Double VEGETABLE_IMPACT = 0.5;
    private FoodType type;
    private Double weight;

    public Food(){}

    public Food(int quantity, LocalDate startDate, LocalDate endDate, FoodType type, Double weight) {
        super(quantity, startDate, endDate);
        this.type = type;
        this.weight = weight;
        this.consumptionType = ConsumptionType.FOOD;
    }

    // Getters
    public FoodType getType() {
        return type;
    }

    public Double getWeight() {
        return weight;
    }

    // Setters
    public void setType(FoodType type) {
        this.type = type;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // calculate impact
    @Override
    Double calculateImpact() {
        return this.quantity * this.weight * ((this.type.equals(FoodType.MEAT) ? MEAT_IMPACT : VEGETABLE_IMPACT));
    }
}
