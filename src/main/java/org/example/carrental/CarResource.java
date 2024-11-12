<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
package org.example.carrental;
=======
package com.carRental.service;
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java

import com.opencsv.CSVReader;
import java.io.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
import jakarta.ws.rs.*;

import java.net.URL;
=======
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
import java.util.ArrayList;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.fasterxml.jackson.databind.JsonNode;
<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
=======
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import static org.json.XMLTokener.entity;

<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java

//Main Endpoint
=======
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
@Path("/cars")
public class CarResource
{
    private final ObjectMapper objectMapper = new ObjectMapper();
<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java

    //Making an Arraylist to store all the cars in csv
    public static ArrayList<Car> allCars = new ArrayList<>();
    public CarResource() {
        try {
            //Taking the csv from resource and processing it to put every line as a car obj in allCars
=======
    public static ArrayList<Car> allCars = new ArrayList<>();
    public CarResource() {
        try {
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
            InputStream in = getClass().getResourceAsStream("/data/allCars.csv");
            assert in != null;

            InputStreamReader reader = new InputStreamReader(in);
            CSVReader csvReader = new CSVReader(reader);

<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
            String[] nextLine;
            // Skip header line
            csvReader.readNext();

            while ((nextLine = csvReader.readNext()) != null) {

                // Parse each line and create Car object
                int stockID = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                String model = nextLine[2].isEmpty() ? "BASE" : nextLine[2]; // Set default value for model if empty
                String manufacturer = nextLine[3];
                String color = nextLine[4];
                int year = Integer.parseInt(nextLine[5]);
                boolean availability = Boolean.parseBoolean(nextLine[6]);
                float pricePerDay = Float.parseFloat(nextLine[7]);

                boolean isDuplicate = false;

                // Check if any car in allCars has the same stockID
                for (Car car : allCars) {
                    if (car.getStockID() == stockID) {
                        isDuplicate = true;
                        break;
                    }
                }

                // Add new Car if it's not a duplicate
                if (!isDuplicate) {
                    allCars.add(new Car(stockID, name, model, manufacturer, color, year, availability, pricePerDay));
                } else {
                    System.out.println("A car with the same stockID already exists. Skipping...");
                    // Continue running the code further
                }
            }
            System.out.println("Data imported successfully.");
=======
                String[] nextLine;
                // Skip header line
                csvReader.readNext();

                while ((nextLine = csvReader.readNext()) != null) {

                    // Parse each line and create Car object
                    int stockID = Integer.parseInt(nextLine[0]);
                    String name = nextLine[1];
                    String model = nextLine[2].isEmpty() ? "BASE" : nextLine[2]; // Set default value for model if empty
                    String manufacturer = nextLine[3];
                    String color = nextLine[4];
                    int year = Integer.parseInt(nextLine[5]);
                    boolean availability = Boolean.parseBoolean(nextLine[6]);
                    float pricePerDay = Float.parseFloat(nextLine[7]);

                    boolean isDuplicate = false;

                    // Check if any car in allCars has the same stockID
                    for (Car car : allCars) {
                        if (car.getStockID() == stockID) {
                            isDuplicate = true;
                            break;
                        }
                    }

                    // Add new Car if it's not a duplicate
                    if (!isDuplicate) {
                        allCars.add(new Car(stockID, name, model, manufacturer, color, year, availability, pricePerDay));
                    } else {
                        System.out.println("A car with the same stockID already exists. Skipping...");
                        // Continue running the code further
                    }
                }
                System.out.println("Data imported successfully.");
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
        } catch (Exception e) {
            System.out.println("Error CSV data: " + e.getMessage());
        }
    }

<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
    // This endpoint return all cars
=======
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
    @GET
    @Produces("application/json")
    @Path("/all")
    public Response getAllCars() { //Obtain testing results
        return buildResponse(allCars);
    }
<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java

    // This endpoint return all the available cars
    @GET
    @Produces("application/json")
    @Path("/available")
    public Response getAvailableCars() {

        ArrayList<Car> availableCars = new ArrayList<>(); //Making new Arraylist to return

        for(Car car : allCars) {
            if(car.isAvailable()) {
=======
    @GET
    @Path("/available")
    @Produces("application/json")
    public Response getAvailableCars() {

        ArrayList<Car> availableCars = new ArrayList<>();

        for(Car car : allCars)
        {
            if(car.isAvailable())
            {
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
                availableCars.add(car);
            }
        }

<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
        return buildResponse(availableCars);
    }

    // This endpoint books the cars as per stockId
    @GET
    @Path("/book/{id}")
    @Produces("text/plain")
    public Response book(@PathParam("id") String idS) {
        int id = Integer.parseInt(idS);
        for (Car car : allCars) {
            if (car.getStockID() == id) {
                car.book();
                allCars.get(id-1).setAvailability(false);
                writeAllCarsToCSV(allCars);
                return Response.status(200)
                        .entity("Car with Stock ID " + id + " has been booked")
                        .build();
            }
        }
        return Response.status(404)
                .entity("Car with Stock ID " + id + " not found")
                .build();
    }

    // This endpoint makes car available as per stockID
    @GET
    @Path("/makeAvailable/{id}")
    @Produces("text/plain")
    public Response makeAvailable(@PathParam("id") String idS) {
        int id = Integer.parseInt(idS);
        for (Car car : allCars) {
            if (car.getStockID() == id) {
                if(!car.isAvailable()) {
                    car.unBook();
                    allCars.get(id-1).setAvailability(true);
                    writeAllCarsToCSV(allCars);
                    return Response.status(200)
                            .entity("Car with Stock ID " + id + " has been booked")
                            .build();
                }
            }
        }
        return Response.status(404)
                .entity("Car Already Available")
                .build();
    }

    // This endpoint deletes the car as per stockId
    @GET
    @Path("/delete/{id}")
    @Produces("text/plain")
    public Response delete(@PathParam("id") String idS) {
        int id = Integer.parseInt(idS);

        allCars.remove(id-1);
        writeAllCarsToCSV(allCars);
        return Response.status(200)
                .entity("Deleted " + id)
                .build();

    }

    // This endpoint adds a new car to the ArrayList and the csv
=======
        return buildResponse(availableCars); //Build response object for accuracy
    }
    @GET
    @Path("/book{id}")
    @Produces("application/json")
    public Response book(@PathParam("id") int id) {
        for(Car car  : allCars)
        {
            if(car.getStockID() == id)
            {
                car.book();
            }
        }
        return buildResponse("Booked!!!");
    }
    @GET
    @Path("/priceChange{code}")
    @Produces("application/json")
    public Response priceChange(@PathParam("code") String code) {
        String[] decode = code.split("-");

        // Extract the two parts
        int id = Integer.parseInt(decode[0]);
        int price = Integer.parseInt(decode[1]);
        for(Car car  : allCars)
        {
            if(car.getStockID() == id)
            {
                car.setPricePerDay(price);
            }
        }
        return buildResponse("Price Changed");
    }
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
    @POST
    @Path("/addCar")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCar(String requestBody) {
        // Check if JSON data is empty or null
        if (requestBody == null || requestBody.trim().isEmpty()) {
            // Handle empty data
            return Response.status(400)
                    .entity("No JSON data received")
                    .build();
        }

        JSONObject jsonObject = new JSONObject(requestBody);

<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
=======
        // Convert JSON string to JSON object
        //JsonNode jsonNode = objectMapper.readTree(requestBody);


>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
        // Extract car details from JSON object
        String name = jsonObject.getString("name");
        String model = jsonObject.getString("model");
        String manufacturer = jsonObject.getString("manufacturer");
        String color = jsonObject.getString("color");
        int year = jsonObject.getInt("year");
        String isAvailable = jsonObject.getString("availability");
        double pricePerDay = jsonObject.getDouble("pricePerDay");

        // Check if model is empty
        if (model.isEmpty()) {
            model = "BASE";
        }

        boolean availability;

        if (isAvailable.equalsIgnoreCase("true")) {
            availability = true;
        } else if (isAvailable.equalsIgnoreCase("false")) {
            availability = false;
        } else {
            // Handle invalid availability values
            throw new IllegalArgumentException("Invalid availability value: " + isAvailable);
        }

        // Create Car object
        Car car = new Car(generateStockID(), name, model, manufacturer, color, year, availability, pricePerDay);

        // Add the new car to the list
        allCars.add(car);

<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
        // Add the new car to csv file
        writeAllCarsToCSV(allCars);

        return buildResponse(car);
    }

    //Building Response
=======
        appendCarToCSV(car);

        // Return success response
        return buildResponse(car);
    }

>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
    private Response buildResponse(Object entity) {
        try {
            return Response.status(200)
                    .header("Content-Type", "application/json")
<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
=======
//                    .header("Access-Control-Allow-Origin", "http://localhost:63342/FinalProject/carRentalClient/cars.html")
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
                    .entity(objectMapper.writeValueAsString(entity))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize response entity", e);
        }
    }


<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
    // This function takes the csv from target folder, then clears it and populates it with updated allCars list
    private void writeAllCarsToCSV(ArrayList<Car> allCars) {
        String filePath = getResourceFilePath("/data/allCars.csv");

        try {
            // Write the header to the CSV file
            BufferedWriter headerWriter = new BufferedWriter(new FileWriter(filePath, false));
            headerWriter.write("StockID,Name,Model,Manufacturer,Color,Year,Availability,PricePerDay\n");
            headerWriter.close();

            // Write all cars to the CSV file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            for (Car car : allCars) {
                writer.write(car.toCSV() + "\n");
            }
            writer.close();

            System.out.println("All cars written successfully.");
=======

    // Method to append a car's details to the CSV file
    private void appendCarToCSV(Car car) {
        String carToString = car.toCSV() + "\n";

        URL resourceURL = CarResource.class.getResource("/data/allCars.csv");
        String filePath = new File(resourceURL.getFile()).getAbsolutePath();

        try {
            // Append the new car data to the CSV file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(carToString);
            writer.close();

            System.out.println("New car added successfully.");
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

<<<<<<< Updated upstream:src/main/java/org/example/carrental/CarResource.java
    // Getting the path for the csv file in resource
    private String getResourceFilePath(String resourcePath) {
        URL resourceURL = CarResource.class.getResource(resourcePath);
        return new File(resourceURL.getFile()).getAbsolutePath();
    }

    //Generating Stock ID
=======
>>>>>>> Stashed changes:carRentalServer/src/main/java/com/carRental/service/CarResource.java
    private int generateStockID()
    {
        return allCars.size() + 1;
    }
}