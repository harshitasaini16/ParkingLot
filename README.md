ParkingLot System

A comprehensive ParkingLot management system built using Java Spring Boot, Hibernate, and JPA. 
The system facilitates vehicle parking, ticket generation, slot management, and payment processing with clean and modular design principles.

Features:
  1. Vehicle Parking and Unparking: Allows vehicles to park and unpark with associated ticket and payment processing.
  2. Slot Management: Handles dynamic allocation and deallocation of parking slots based on distance and vehicle type.
  3. Payment Processing: Calculates and processes payments during vehicle exit.
  4. REST API Endpoints: Provides endpoints for managing parking spaces, slots, tickets, and payments.
  5. Database Integration: Uses Hibernate for ORM and MySQL as the database.

Technologies Used:
  1. Backend: Java Spring Boot
  2. ORM: Hibernate, JPA
  3. Database: MySQL
  4. Tools: Maven, Postman

Installation:

Clone the repository:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/appointmentdb
    spring.datasource.username=root
    spring.datasource.password=<password>
    spring.mail.username=<your-email>
    spring.mail.password=<your-email-password>
   ```

Set up MySQL database:

Create a database named parking_lot.

Update database configurations in application.properties:
   ```bash
  spring.datasource.url=jdbc:mysql://localhost:3306/parking_lot
  spring.datasource.username=<your-username>
  spring.datasource.password=<your-password>
  spring.jpa.hibernate.ddl-auto=update
  ```

Build the project:
  ```bash
    mvn clean install
  ```

Run the application:
  ```bash
  mvn spring-boot:run
  ```

Access the API:

Base URL: http://localhost:8080

Example endpoint: POST /park for parking a vehicle.


API Endpoints
  
1. Parking Management

Park a Vehicle:

    POST /park

Request Body:

{

  "vehicleId": 677,
   
  "vehicleType": "TwoWheeler",
    
  "parkingSpaceId": 2

}

Unpark a Vehicle:
    
    PUT /park/{ticketId}

2.Slot Management

Update Slot:

    PUT /slots/{id}

3. Ticket Management

Get Ticket Details:

    GET /tickets/{id}

Project Structure

    src/main/java

      |-- com.example.demo
  
        |-- controller      # REST controllers
    
        |-- entity          # JPA entities (Slot, Ticket, Payments, etc.)
    
        |-- service         # Service layer interfaces and implementations
    
        |-- repository      # JPA repositories for database interaction
    
        |-- model           # Request/Response DTOs

Example Workflow

Park a Vehicle:

Send a POST request to /park with vehicle details.

Receive a ticket with slot allocation.

Unpark a Vehicle:

Send a PUT request to /park/{ticketId}.

Receive payment details for the parking duration.


Check Ticket Details:

Send a GET request to /tickets/{id}.

Get ticket information.


Contributing

Fork the repository.

Create a feature branch: 
  ```bash
  git checkout -b feature-name.
  ```

Commit your changes: 
  ```bash
  git commit -m 'Add some feature'.
  ```
Push to the branch: 
  ```bash
  git push origin feature-name.
  ```
Open a pull request.
