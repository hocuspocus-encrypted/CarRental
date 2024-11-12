let allCarsUrl = "http://localhost:8080/carRental-1.0/api/cars/all";
let allCarsArray = []; // Declare an array to store the fetched data
// Function to display list of cars on the webpage
function displayCars(allCars) {
  // Get the container element where you want to display the cars
  var container = document.getElementById("carListContainer");

  // Clear the container before adding new cars (optional)
  container.innerHTML = "";

  console.log(allCars)

  // Loop through the list of cars
  allCars.forEach(function(car) {
    // Create a div element for each car
    const carDiv = document.createElement("div");
    carDiv.classList.add("car-item");

    // Create HTML content for the car
    const carContent = `
      <p><strong>Stock ID:</strong> ${car.stockID}</p>
      <p><strong>Name:</strong> ${car.name}</p>
      <p><strong>Model:</strong> ${car.model}</p>
      <p><strong>Manufacturer:</strong> ${car.manufacturer}</p>
      <p><strong>Color:</strong> ${car.color}</p>
      <p><strong>Year:</strong> ${car.year}</p>
      <p><strong>Availability:</strong> ${car.available ? "Available" : "Not Available"}</p>
      <p><strong>Price Per Day:</strong> ${car.pricePerDay}</p>
    `;

    carDiv.innerHTML = carContent;

    // Create an image element for the car
    const img = document.createElement("img");
    img.src = "img/"+car.stockID+".jpg"; // Adjust the image path relative to the HTML folder
    img.alt = car.name; // Set the alt attribute to the name of the car
    img.classList.add("car-image");

    //img.style.width = "100px";
    img.style.height = "200px";

    // Append the image element to the car div
    carDiv.appendChild(img);

    const hr = document.createElement("hr");

    // Apply styles to the <hr> element
    hr.style.borderTop = "1px solid #333"; // Border style
    hr.style.marginTop = "10px"; // Top margin

    // Append the <hr> element to a container (e.g., body)
    container.appendChild(hr);

    // Append the car div to the container
    container.appendChild(carDiv);
  });
}

// Fetch all cars from the server
function requestDataFromServer(url) {
  fetch(url)
    .then(response => {
      if (!response.ok) {
        throw new Error("Failed to fetch cars");
      }
      return response.json(); // Parse response body as JSON
    })
    .then(allCars => {
      // Process the list of cars
      allCarsArray = allCars
      console.log(allCarsArray)// For debugging, you can log the list of cars
      displayCars(allCarsArray);
      // Here you can perform any operations with the list of cars, such as displaying them on the webpage
    })
    .catch(error => {
      console.error(error); // Handle error
    });
}

document.getElementById("addCarButton").addEventListener("click", function() {
  document.getElementById("addCarFormContainer").classList.remove("hidden");
});

document.getElementById("addCarForm").addEventListener("submit", function(event) {
  event.preventDefault(); // Prevent default form submission

  // Collect form data
  const formData = new FormData(document.getElementById("addCarForm"));

  // Convert form data to JSON object
  const jsonObject = {};
  formData.forEach((value, key) => {
    jsonObject[key] = value;
  });

  console.log("Data to be sent:", jsonObject);

  // Make POST request to server
  fetch("http://localhost:8080/carRental-1.0/api/cars/addCar", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(jsonObject)
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Failed to add car");
      }
      return response.json();
    })
    .then(data => {
      console.log(data); // Handle successful response
      // Close the form
      allCarsArray.push(data)
      displayCars(allCarsArray);
      document.getElementById("addCarFormContainer").classList.add("hidden");
    })
    .catch(error => {
      console.error(error); // Handle error
    });
});


function promptForCredentials(maxAttempts) {
  while (true) {
    var password = prompt("Enter your password (password : admin):");

    if (password === "admin") {
      alert("Welcome Admin!");
      return; // Exit the function if credentials are correct
    } else {
      alert("Invalid password. Please try again.");
    }
  }
}

function onLoad(){

  promptForCredentials(1);
  requestDataFromServer(allCarsUrl);

}

document.addEventListener('DOMContentLoaded', onLoad);
