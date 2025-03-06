# Banking-Application-Project

This project is a banking application that provides various functionalities for managing customers, cards, transactions, and user authentication. 
It integrates **Spring Security** for authentication and authorization, and uses **JWT (JSON Web Tokens)** for secure token-based user authentication.

## Project Objectives

The main goal of this project is to implement various services for banking operations, including:

- User registration and login (with JWT).
- Managing customer profiles.
- Handling card-related operations.
- Managing transactions.

## Features

### **AuthService**
Handles authentication and user management operations:
- **registerCustomer**: Allows new customers to register by providing their details.
- **loginCustomer**: Handles customer login, validates credentials, and returns a JWT token.

### **CustomerService**
Handles all operations related to managing customer profiles:
- **editProfile**: Allows customers to update their profile information, such as name, email, etc.

### **CardService**
Handles all operations related to managing customer cards:
- **getUserCards**: Retrieves all cards associated with a specific customer.
- **editCard**: Allows the customer to update card details (e.g., blocking, activating).

### **TransactionService**
Handles all operations related to transactions:
- **createTransaction**: Allows a customer to initiate a transaction, transferring money between accounts.

## Technologies Used

- **Java 17**: Programming language for building the application.
- **Spring Boot**: Backend framework for creating RESTful APIs.
- **Spring Security**: Provides authentication and authorization mechanisms, ensuring secure access.
- **JWT (JSON Web Token)**: Used for secure, token-based authentication.
- **Spring Data JPA**: Simplifies data access and integrates seamlessly with the database layer.
- **PostgreSQL**: Database for storing customer, card, and transaction data.
- **JPA (Java Persistence API)**: For object-relational mapping and database interaction.
