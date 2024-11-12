<<<<<<< Updated upstream
let allCarsUrl = "http://localhost:8080/carRental-1.0/api/cars/all"; //Url to get all cars
let allCarsArray = []; // Declare an array to store the fetched data

=======
let allCarsUrl = "http://localhost:8080/carRental-1.0/api/cars/all";
let allCarsArray = []; // Declare an array to store the fetched data
>>>>>>> Stashed changes
// Function to display list of cars on the webpage
function displayCars(allCars) {
  // Get the container element where you want to display the cars
  var container = document.getElementById("carListContainer");

  // Clear the container before adding new cars (optional)
  container.innerHTML = "";

<<<<<<< Updated upstream
=======
  console.log(allCars)

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    img.src = "img/" + car.stockID + ".jpg"; // Adjust the image path relative to the HTML folder
    img.alt = car.name; // Set the alt attribute to the name of the car
    img.classList.add("car-image");
    img.style.height = "200px"; // Set the height of the image
=======
    img.src = "img/"+car.stockID+".jpg"; // Adjust the image path relative to the HTML folder
    img.alt = car.name; // Set the alt attribute to the name of the car
    img.classList.add("car-image");

    //img.style.width = "100px";
    img.style.height = "200px";
>>>>>>> Stashed changes

    // Append the image element to the car div
    carDiv.appendChild(img);

<<<<<<< Updated upstream
    carDiv.appendChild(document.createElement("br"));
    carDiv.appendChild(document.createElement("hr"));

    // Create delete button
    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.id = "deleteButton"; // Assigning an id
    deleteButton.addEventListener("click", function() {
      // Call a function to handle deletion of this car
      deleteCar(car.stockID);
    });
    carDiv.appendChild(deleteButton);

    // Create make available button
    const makeAvailableButton = document.createElement("button");
    makeAvailableButton.textContent = "Make Available";
    makeAvailableButton.id = "makeAvailableButton"; // Assigning an id
    makeAvailableButton.addEventListener("click", function() {
      // Call a function to handle making this car available
      makeAvailable(car.stockID);
    });
    carDiv.appendChild(makeAvailableButton);
=======
    const hr = document.createElement("hr");

    // Apply styles to the <hr> element
    hr.style.borderTop = "1px solid #333"; // Border style
    hr.style.marginTop = "10px"; // Top margin

    // Append the <hr> element to a container (e.g., body)
    container.appendChild(hr);
>>>>>>> Stashed changes

    // Append the car div to the container
    container.appendChild(carDiv);
  });
}

<<<<<<< Updated upstream
// Deletes a particular car on Server Side
function deleteCar(stockID) {
  var confirmed = confirm("Warning!!! Once you delete this car, it's gone forever. Press ok to proceed/cancel to abort (YOU WILL HAVE TO ADD IN CSV FILE AGAIN IN TARGET FROM SRC/MAIN/RESOURCES TO RESET)");
  if (confirmed) {
  fetch("http://localhost:8080/carRental-1.0/api/cars/delete/" + stockID, {
    method: "GET",
    headers: {
      "Accept": "text/plain"
    }
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Failed Deleting");
      }
      return response.text();
    })
    .then(data => {
      console.log(data);
      requestDataFromServer(allCarsUrl)
    })
    .catch(error => {
      console.error("Error:", error);
    });
  } else {
    location.reload();
  }

}

// Function to handle making a car available
function makeAvailable(stockID) {
  fetch("http://localhost:8080/carRental-1.0/api/cars/makeAvailable/" + stockID, {
    method: "GET",
    headers: {
      "Accept": "text/plain"
    }
  })
    .then(response => {
      if (!response.ok) {
        alert("Car already available")
        throw new Error("Failed to make car avail");
      }
      return response.text();
    })
    .then(data => {
      console.log(data);
      alert("Car ID : "+stockID+" is available again")
      // You can perform any action with the fetched data here
    })
    .catch(error => {
      console.error("Error:", error);
    });
}

=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
  document.getElementById("addCarButton").classList.add("hidden");
});

//function to submit form for new car
=======
});

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
      document.getElementById("addCarButton").classList.remove("hidden");
=======
>>>>>>> Stashed changes
    })
    .catch(error => {
      console.error(error); // Handle error
    });
});

<<<<<<< Updated upstream
//Function to force user to put password to visit admin
  function promptForCredentials(maxAttempts) {
    while (true) {// Repeats itself till it is correct password
      var password = prompt("Enter your password (password = admin) :");

      if (password === "admin") {
        alert("Welcome Admin!");
        return; // Exit the function if password is correct
      } else {
        alert("Invalid password. Please try again.");
      }
    }
  }

// functions to be used when page is loaded
=======

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

>>>>>>> Stashed changes
function onLoad(){

  promptForCredentials(1);
  requestDataFromServer(allCarsUrl);

}

document.addEventListener('DOMContentLoaded', onLoad);
