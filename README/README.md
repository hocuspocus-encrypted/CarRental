## Car Rental Site

## Overview
This project is built to serve as a way for users to rent exotic cars in a streamlined way.
This website also has admin features which allows someone with admin credentials to be able to add and delete cars from the selection.

### Car Rental Features

For this Application, the following features were implemented:
- Users can browse the inventory of cars and book one available.
- The website has modern elements of UI and UX with a navigation bar at the top and an on-hover effect for each car.
- An aesthetic home page that has a video showcasing the cars from the catalog
- Admin can log in with their password, "admin", to add and delete a car from the catalog
- A CSV file was used as a database to store information about each car.
- The server automatically generates Stock ID.
- CORSEnabler was added to deal with CORS-related issues. 
## Dependencies
- Java Development Kit (JDK) 21 or higher
- Jakarta WebSocket API
- com.fasterxml.jackson.core
- org.json
- com.opencsv
- jakarta.enterprise
- jakarta.ws.rs
- jakarta.servlet
- org.junit.jupiter
- org.jsoup
## Steps to Run the Car Rental Website
- Clone the repository:\
  The easiest way to clone the entire project is to obtain the URL from GitHub and add a new project from version control using this URL (IntelliJ Feature). You can also clone the repository using Git Bash. For this, open Git Bash in your projects folder, then type the following:
  `git clone https://github.com/OntarioTech-CS-program/w24-csci2020u-final-project-amin-tejpal-chowdhury-saibuvis.git'
  Once you clone the project, load the Maven settings when the prompt appears in IntelliJ.
- Set up Glassfish Configuration:
  Select Current File near the top right of IntelliJ IDE. Press the `+` button and add a `GlassFish local server`. Ensure the fields (`URL` and `Server Domain`) in the Configurations pop-up match below:\
  ![image info](./config.png)
  <br>
  Navigate to the `Deployment` tab, and choose the artifact to be deployed as seen below.
  <br>
  ![image info](./dep.png)
  Now you are all set to run the `GlassFish Server`.
- After you run the server, the `carRentalClient` artifact is deployed which runs the `HTML` file in a browser (Microsoft Edge is recommended).\
  
  If you are not able to view the `HTML` file by default, just run the `index.html` file from IntelliJ when the server is running.
- Next, you can browse through the catalog of cars available for rental and book accordingly.
  
- You can also visit the `About` page to learn more about the collaborators on this project.
- You are more than welcome to contribute and customize this project. Fork the repository, make your changes, and submit a pull request if you want to contribute back to the project.

## Car Rental Endpoints
- All endpoints for CarResource are under "cars".
- Within it, the first endpoint is "all" which displays all cars.
- The second endpoint is "available" which displays only the available cars.
- The third endpoint is "book" which allows the user to book a car they want to rent.
- The fourth endpoint is "makeAvailable" which allows the admin to make a car available for a user to book.
- The fifth endpoint is "delete" which allows the admin to delete a car from the list.
- The sixth endpoint is "addCar" which allows the admin to add a car to the list.


### Future Improvements
- We can optimize the server performance to be able to handle larger volumes of concurrent users and messages.
- Include features to allow the admin to share media like images of the cars they would like to list for rental.
- It is possible to add features to allow users to rent for a certain amount of time.
- Pricing options can also be added to give users more options on how they would like to go about their rental.
- Implementing an actual database to enhance the website's performance by efficiently organizing and retrieving data, including images, 
  which would reduce response times and improve scalability.
- Feature that allows user to book a car depending on their location

### Screenshots 
- Home Page
![image info](./home.png)

- Inventory Page
![image info](./inventory.png)

- Available Page
![image info](./available.png)

- About Page
![image info](./about.png)

- Admin Page
![image info](./admin.png)

- Admin Adding Car
![image info](./add_car.png)

- Car Being Added
![image info](./car_added.png)
