package models;

import utils.ConsoleUI;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private String name;
    private int age;

    private final int id;
    private static final AtomicInteger idCounter = new AtomicInteger();


    private final ArrayList<CarbonConsumption> carbonConsumption;

    // Constructor
    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = generateUniqueID();
        this.carbonConsumption = new ArrayList<>();
    }

    // Id Generator
    private static int generateUniqueID() {
        return idCounter.incrementAndGet();
    }

    // Getters and Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public ArrayList<CarbonConsumption> getCarbonConsumption() {
        return carbonConsumption;
    }

    // Add a Carbon consumption to the list
    public void addCarbonConsumption(CarbonConsumption consumption) {
        this.carbonConsumption.add(consumption);
    }

    // Show User infos
    @Override
    public String toString() {
        StringBuilder carbonConsumptionSTR = new StringBuilder();
        for(CarbonConsumption c: carbonConsumption){
            carbonConsumptionSTR.append("\t#").append(c.getId())
                                .append(": ").append(ConsoleUI.BLUE).append(c.getQuantity()).append(ConsoleUI.RESET)
                                .append(" from ").append(c.getStartDate())
                                .append(" to ").append(c.getEndDate())
                                .append("\n");
        }
        return "---------------------------------\n" +
                "id          : " + id + "\n" +
                "name        : " + name + "\n" +
                "age         : " + age + "\n" +
                "Consumption : " + ConsoleUI.YELLOW + getConsumptionTotal() + ConsoleUI.RESET + "\n" +
                carbonConsumptionSTR;
    }

    public void printLine(){
        System.out.println(
                ConsoleUI.YELLOW + "\n #" + id + ConsoleUI.RESET + " " + name + ", " + age + " yo : "
                 + ConsoleUI.BLUE + getConsumptionTotal() + ConsoleUI.RESET
        );
    }

    // Consumption Total
    public double getConsumptionTotal() {
        return carbonConsumption.stream()
                .mapToDouble(CarbonConsumption::getQuantity)
                .reduce(0, Double::sum);
    }
}