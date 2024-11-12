<<<<<<< Updated upstream
let allCarsUrl = "http://localhost:8080/carRental-1.0/api/cars/all"; //Url to get all cars
let allCarsArray = []; // Declare an array to store the fetched data

// Function to display list of cars on the webpage
=======
let allCarsUrl = "http://localhost:8080/carRental-1.0/api/cars/all";
let allCarsArray = []; // Declare an array to store the fetched data

>>>>>>> Stashed changes
function displayCars(allCars) {
  // Get the container element where you want to display the cars
  var container = document.getElementById("inventory-list");

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

<<<<<<< Updated upstream
=======
    //img.style.width = "100px";
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

// Fetch all cars from the server
=======
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
// functions to be used when page is loaded
=======
>>>>>>> Stashed changes
function onLoad(){

  requestDataFromServer(allCarsUrl);

}

document.addEventListener('DOMContentLoaded', onLoad);



<<<<<<< Updated upstream
=======
// // Function to request data from the server to send to the client
// function requestDataFromServer(url, updateFunction)
// {
//   fetch(url, {
//     method: 'GET',
//     headers: {
//       'Accept': 'application/json'
//     }
//   }).then(response => {if (!response.ok){throw new Error('Network response not ok');}
//     return response.json();})
//     .then(data => {
//       updateFunction(data);
//     })
//       .catch(error => console.log(error));
// }
//
// //Adding the requested data to the client in the form of a table
// function updateUI(data){
//   const dataTableBody = document.querySelector('#data-table tbody');
//
//   dataTableBody.innerHTML='';
//
//   for(let i=0;i < data.length;i++){
//     const entry = data[i];
//     const tableRow = document.createElement('tr');
//
//     const fileCell = document.createElement('td');
//     fileCell.textContent = entry.file;
//
//     const spamProbabilityCell = document.createElement('td');
//     spamProbabilityCell.textContent = entry.spamProbability;
//
//     const actualClassCell = document.createElement('td');
//     actualClassCell.textContent = entry.actualClass;
//
//     tableRow.appendChild(fileCell);
//     tableRow.appendChild(spamProbabilityCell);
//     tableRow.appendChild(actualClassCell);
//
//     dataTableBody.appendChild(tableRow);
//   }
// }
//
// //Adding the values for accuracy to the client from the server
// function updateAccuracy(accuracy){
//   const accuracyElement = document.querySelector('#accuracy');
//   accuracyElement.textContent = `Accuracy : ${accuracy}`;
// }
//
// //Adding the values for precision to the client from the server
// function updatePrecision(precision){
//   const precisionElement = document.querySelector('#precision');
//   precisionElement.textContent = `Precision : ${precision}`;
// }
//
// //Declaring the sources from which the server is fetching the data for the client and calling the functions
// function onLoad(){
//   let apiUrl = "http://localhost:8080/spamDetector-1.0/api/spam/data"
//   let accuracyUrl = "http://localhost:8080/spamDetector-1.0/api/spam/accuracy"
//   let precisionUrl = "http://localhost:8080/spamDetector-1.0/api/spam/precision"
//
//   requestDataFromServer(apiUrl, updateUI);
//
//   requestDataFromServer(accuracyUrl, updateAccuracy);
//
//   requestDataFromServer(precisionUrl, updatePrecision);
// }
//
// document.addEventListener('DOMContentLoaded', onLoad);
//
//
// function w3_close() {
//   document.getElementById("mySidebar").style.display = "none";
//   document.getElementById("myOverlay").style.display = "none";
// }
>>>>>>> Stashed changes
