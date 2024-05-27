# Project Starter

## System Requirements
Before getting started, ensure your system meets the following requirements:
- [java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) is installed on your machine.
- [Maven](https://maven.apache.org/) is installed on your machine.
- [MySql](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/) is installed on your machine.

### Installation

1. Clone the repository:

    ```bash
    $ git clone https://github.com/tafhdytllah/ecommerce.git
    ```

2. Navigate to the project directory:

    ```bash
    $ cd ecommerce
    ```
3. Create database "ecommerce" into your machine and execute this script [database.sql](/database.sql)

4. Run into local machine :

    ```bash
    $ mvn spring-boot:run
    ```

5. The Spring Boot application will be accessible at `http://localhost:8080`.

## Endpoint

#### Show All Data Transaction

- **Metode:** [GET]
- **Endpoint:** `/api/transactions`

    #### Response
  - **Status Code:** [200]
  - **Response Body:**
      ```json
      {
          "data": [
            {
              "id": 1,
              "productID": "2",
              "productName": "Kulkas",
              "amount": "5",
              "customerName": "Budi",
              "status": 1,
              "transactionDate": "2024-05-27 11:37:22",
              "createBy": "admin",
              "createOn": "2024-05-27 11:37:22"
            }  
          ],
          "status": [
            {
              "id": 0,
              "name": "SUCCESS"
            }
    ]
      }
      ```
