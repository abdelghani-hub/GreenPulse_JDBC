package services;

import enumerations.ConsumptionType;
import enumerations.EnergyType;
import enumerations.FoodType;
import enumerations.TransportType;
import models.*;
import repositories.ConsumptionRepository;
import utils.ConsoleUI;

import java.time.LocalDate;
import java.util.*;

public class ConsumptionService {

    private ConsumptionRepository consumptionRepo;

    public ConsumptionService() {
        this.consumptionRepo = new ConsumptionRepository();
    }

    public void create() {
        // Get the user
        UserService userService = new UserService();
        User user = userService.getUser();
        if (user == null) {
            ConsoleUI.printError("User not found!");
            return;
        }

        // Consumption Type
        Map<String, String> consumptionTypeChoices = new HashMap<>();
        consumptionTypeChoices.put("1", ConsumptionType.FOOD.toString());
        consumptionTypeChoices.put("2", ConsumptionType.HOUSING.toString());
        consumptionTypeChoices.put("3", ConsumptionType.TRANSPORT.toString());
        String consumptionType = ConsoleUI.readChoice("\tEnter consumption type : ", consumptionTypeChoices);

        // Quantity
        Double quantity = ConsoleUI.readDouble("\tEnter the Quantity : ");

        // Start and End Dates
        LocalDate startDate;
        LocalDate endDate;
        while (true) {
            startDate = ConsoleUI.readLocalDate("\tEnter start date :");
            endDate = ConsoleUI.readLocalDate("\tEnter end date :");

            if (endDate.isBefore(startDate))
                ConsoleUI.printError("Invalid Period! The end date should be after the start date!.");
//            else if (!isAvailableDate(startDate, endDate, user.)) // TODO : Implement this method
//                ConsoleUI.printError("Date range overlaps with an existing period!");
            else
                break;
        }

        // Get other attributes depending on consumption type
        Consumption consumption = getConsumptionInstance(consumptionType);
        consumption.setQuantity(quantity);
        consumption.setStartDate(startDate);
        consumption.setEndDate(endDate);
        consumption.setUserId(user.getId());

        if (consumptionRepo.save(consumption).isPresent())
            ConsoleUI.printSuccess("Consumption hav been saved successfully.");
        else
            ConsoleUI.printError("Error whiting saving the consumption!");
    }

    public Consumption getConsumptionInstance(String type) {
        switch (type) {
            case "1": // FOOD : food type & weight
                Map<String, String> foodTypeChoices = new HashMap<>();
                foodTypeChoices.put("1", FoodType.MEAT.toString());
                foodTypeChoices.put("2", FoodType.VEGETABLE.toString());
                String selectedFoodType = ConsoleUI.readChoice("\tEnter Food Type : ", foodTypeChoices);
                Double weight = ConsoleUI.readDouble("\tEnter the weight : ");

                Food food = new Food();
                food.setFoodType(FoodType.valueOf(foodTypeChoices.get(selectedFoodType)));
                food.setWeight(weight);
                food.setConsumptionType(ConsumptionType.FOOD);
                return food;
            case "2": // HOUSING
                Map<String, String> energyTypeChoices = new HashMap<>();
                energyTypeChoices.put("1", EnergyType.ELECTRICITY.toString());
                energyTypeChoices.put("2", EnergyType.GAS.toString());
                String selectedEnergyType = ConsoleUI.readChoice("\tEnter Energy Type : ", energyTypeChoices);
                Double energyConsumption = ConsoleUI.readDouble("\tEnter Energy Consumption : ");

                Housing housing = new Housing();
                housing.setEnergyType(EnergyType.valueOf(energyTypeChoices.get(selectedEnergyType)));
                housing.setEnergyConsumption(energyConsumption);
                housing.setConsumptionType(ConsumptionType.HOUSING);
                return housing;
            case "3": // TRANSPORT
                Map<String, String> TransportTypeChoices = new HashMap<>();
                TransportTypeChoices.put("1", TransportType.CAR.toString());
                TransportTypeChoices.put("2", TransportType.TRAIN.toString());
                String selectedTransportType = ConsoleUI.readChoice("\tEnter Energy Type : ", TransportTypeChoices);
                Double distanceTravelled = ConsoleUI.readDouble("\tEnter Distance Travelled : ");

                Transport transport = new Transport();
                transport.setTransportType(TransportType.valueOf(TransportTypeChoices.get(selectedTransportType)));
                transport.setDistanceTravelled(distanceTravelled);
                transport.setConsumptionType(ConsumptionType.TRANSPORT);
                return transport;
        }
        return null;
    }
}