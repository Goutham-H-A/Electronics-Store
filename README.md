# E-Commerce Web Application

## Overview

This is a fully functional e-commerce web application built using core Java technologies without any frameworks like Spring. The application includes user authentication, product management, a shopping cart, order management, and an admin panel.

## Prerequisites

- JDK 8 or higher
- Apache Maven
- MySQL
- Apache Tomcat (or any other servlet container)

## Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/Goutham-H-A/Electronics-Store.git
cd Electronics-Store
```

### Step 2: Set Up the Database

1. **Create a MySQL database:**

   ```sql
   CREATE DATABASE electronicsstore;
   ```

2. **Import the database schema:**

   Import the provided SQL script (`ecommerce.sql`) into the `ecommerce` database.

### Step 3: Configure Database Connection

Update the database connection settings in `src/main/resources/db.properties` with your MySQL username and password.

### Step 4: Build the Project

Use Eclipse IDE to build the project:
install Eclipse IDE for Java EE Developers
```bash
install Eclipse from https://www.eclipse.org/downloads/packages/installer
```

### Step 5: Deploy to Servlet Container

Deploy the generated WAR file (`target/ecommerce-web-app.war`) to your servlet container (e.g., Apache Tomcat).

### Step 6: Run the Application

Start your servlet container and access the application via:

```
http://localhost:8080/Electronics-Store
```

## Usage

- **User Registration/Login:** Register a new account or log in with existing credentials.
- **Shopping Cart:** Add products to the cart and proceed to checkout.
- **Order Management:** View order history and current order status.
- **Admin Functions:** Add new product,Manage products, orders, and user accounts via the admin panel.


## Contact

For any questions or support, please contact [gouthamubaley15@gmail.com].
