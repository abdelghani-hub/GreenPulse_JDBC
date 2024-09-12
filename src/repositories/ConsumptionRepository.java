package repositories;

import enumerations.ConsumptionType;
import enumerations.EnergyType;
import enumerations.FoodType;
import enumerations.TransportType;
import interfaces.RepositoryInterface;
import models.*;

import config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ConsumptionRepository implements RepositoryInterface<Consumption> {
    @Override
    public Optional<Consumption> save(Consumption consumption) {
        // Preparing the query
        String additionalAttributes = "";
        String tableName = "";
        switch (consumption.getConsumptionType()) {
            case FOOD:
                additionalAttributes = ", food_type, weight";
                tableName = "food";
                break;
            case HOUSING:
                additionalAttributes = ", energy_type, energy_consumption";
                tableName = "housing";
                break;
            case TRANSPORT:
                additionalAttributes = ", transport_type, distance_travelled";
                tableName = "transport";
                break;
        }
        String query = "INSERT INTO " + tableName + " (id, quantity, start_date, end_date, consumption_type, user_id" + additionalAttributes + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, consumption.getId());
            statement.setDouble(2, consumption.getQuantity());
            statement.setDate(3, Date.valueOf(consumption.getStartDate()));
            statement.setDate(4, Date.valueOf(consumption.getEndDate()));
            statement.setString(6, consumption.getUserId());

            // set other attributes
            switch (consumption.getConsumptionType()) {
                case FOOD:
                    statement.setInt(5, 1);
                    statement.setString(7, ((Food) consumption).getFoodType().toString());
                    statement.setDouble(8, ((Food) consumption).getWeight());
                    break;
                case HOUSING:
                    statement.setInt(5, 2);
                    statement.setString(7, ((Housing) consumption).getEnergyType().toString());
                    statement.setDouble(8, ((Housing) consumption).getEnergyConsumption());
                    break;
                case TRANSPORT:
                    statement.setInt(5, 3);
                    statement.setString(7, ((Transport) consumption).getTransportType().toString());
                    statement.setDouble(8, ((Transport) consumption).getDistanceTravelled());
                    break;
            }

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.of(consumption);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Consumption> update(Consumption object) {
        return Optional.empty();
    }

    @Override
    public Optional<Consumption> find(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Consumption> remove(Consumption object) {
        return Optional.empty();
    }

    @Override
    public List<Consumption> findAll() {return null;}

    public List<Consumption> findByType(ConsumptionType type, String userId) {
        List<Consumption> consumptions = new ArrayList<>();
        String query = "SELECT * FROM " + type.toString().toLowerCase();
        if(userId != null) {
            query += " WHERE user_id = '" + userId + "'";
        }

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Consumption consumption;

                switch (type) {
                    case FOOD:
                        Food food = new Food();
                        food.setFoodType(FoodType.valueOf(resultSet.getString("food_type")));
                        food.setWeight(resultSet.getDouble("weight"));
                        food.setConsumptionType(ConsumptionType.FOOD);
                        consumption = food;
                        break;
                    case HOUSING:
                        Housing housing = new Housing();
                        housing.setEnergyType(EnergyType.valueOf(resultSet.getString("energy_type")));
                        housing.setEnergyConsumption(resultSet.getDouble("energy_consumption"));
                        housing.setConsumptionType(ConsumptionType.HOUSING);
                        consumption = housing;
                        break;
                    case TRANSPORT:
                        Transport transport = new Transport();
                        transport.setTransportType(TransportType.valueOf(resultSet.getString("transport_type")));
                        transport.setDistanceTravelled(resultSet.getDouble("distance_travelled"));
                        transport.setConsumptionType(ConsumptionType.TRANSPORT);
                        consumption = transport;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown consumption type: " + type);
                }

                // common attributes
                consumption.setId(resultSet.getString("id"));
                consumption.setQuantity(resultSet.getDouble("quantity"));
                consumption.setStartDate(resultSet.getDate("start_date").toLocalDate());
                consumption.setEndDate(resultSet.getDate("end_date").toLocalDate());
                consumption.setUserId(resultSet.getString("user_id"));

                consumptions.add(consumption);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consumptions;
    }
}