package models;

import utils.ConsoleUI;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String name;
    private int age;
    private String id;

    private final ArrayList<Consumption> consumptions;

    // Constructors

    public User(){
        this.name = "";
        this.age = 0;
        this.id = "";
        this.consumptions = new ArrayList<>();
    }
    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = generateUniqueID();
        this.consumptions = new ArrayList<>();
    }

    // Id Generator
    private static String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Consumption> getConsumptions() {
        return consumptions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Consumption> getCarbonConsumption() {
        return consumptions;
    }

    // Add a Carbon consumption to the list
    public void addCarbonConsumption(Consumption consumption) {
        this.consumptions.add(consumption);
    }

    // Show User infos
    @Override
    public String toString() {
        StringBuilder carbonConsumptionSTR = new StringBuilder();
        for(Consumption c: consumptions){
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
                "Consumption : " + ConsoleUI.YELLOW + getConsumptionTotal() + ConsoleUI.RESET + " CO2eq\n" +
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
        return consumptions.stream()
                .mapToDouble(Consumption::getQuantity)
                .reduce(0, Double::sum);
    }
}