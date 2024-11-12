package com.carRental.service;

import com.opencsv.CSVReader;
import java.io.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import static org.json.XMLTokener.entity;

@Path("/cars")
public class CarResource
{
    private final ObjectMapper objectMapper = new ObjectMapper();
    public static ArrayList<Car> allCars = new ArrayList<>();
    public CarResource() {
        try {
            InputStream in = getClass().getResourceAsStream("/data/allCars.csv");
            assert in != null;

            InputStreamReader reader = new InputStreamReader(in);
            CSVReader csvReader = new CSVReader(reader);

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
        } catch (Exception e) {
            System.out.println("Error CSV data: " + e.getMessage());
        }
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public Response getAllCars() { //Obtain testing results
        return buildResponse(allCars);
    }
    @GET
    @Path("/available")
    @Produces("application/json")
    public Response getAvailableCars() {

        ArrayList<Car> availableCars = new ArrayList<>();

        for(Car car : allCars)
        {
            if(car.isAvailable())
            {
                availableCars.add(car);
            }
        }

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

        // Convert JSON string to JSON object
        //JsonNode jsonNode = objectMapper.readTree(requestBody);


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

        appendCarToCSV(car);

        // Return success response
        return buildResponse(car);
    }

    private Response buildResponse(Object entity) {
        try {
            return Response.status(200)
                    .header("Content-Type", "application/json")
//                    .header("Access-Control-Allow-Origin", "http://localhost:63342/FinalProject/carRentalClient/cars.html")
                    .entity(objectMapper.writeValueAsString(entity))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize response entity", e);
        }
    }



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
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private int generateStockID()
    {
        return allCars.size() + 1;
    }
}