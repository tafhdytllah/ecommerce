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

#### 1. Show All Data Transaction

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
          ],
          "errors": null
      }
      ```
    
#### 2. Get Transaction by Product and Customer

- **Metode:** [GET]
- **Endpoint:** `/api/transactions/products/3/customers/1`

  #### Response
    - **Status Code:** [200]
    - **Response Body:**
        ```json
        {
            "data": {
              "id": 3,
              "productID": "3",
              "productName": "Lemari",
              "amount": "1",
              "customerName": "Budi",
              "status": 0,
              "transactionDate": "2024-05-27 11:37:22",
              "createBy": "admin",
              "createOn": "2024-05-27 11:37:22"
            },
            "status": [
              {
                "id": 0,
                "name": "SUCCESS"
              }
            ],
            "errors": null
        }
        ```

  - **Status Code:** [404]
  - **Response Body:**
      ```json
      {
          "data": null,
          "status": [
            {
              "id": 1,
              "name": "FAILED"
            }
          ],
          "errors": "Transaction is not found"
      }
      ```

#### 3. Create Transaction

- **Metode:** [POST]
- **Endpoint:** `/api/transactions`
  #### Request
  ```json
  {
    "product_id": 5,
    "customer_id": 1,
    "amount": 8,
    "status": true,
    "create_by": "admin"
  }
  ```

  #### Response
    - **Status Code:** [200]
    - **Response Body:**
        ```json
        {
            "data": {
              "id": 9,
              "productID": "5",
              "productName": "Kompor",
              "amount": "8",
              "customerName": "Budi",
              "status": 1,
              "transactionDate": "2024-05-27 11:37:22",
              "createBy": "admin",
              "createOn": "2024-05-27 11:37:22"
            },
            "status": [
              {
                "id": 0,
                "name": "SUCCESS"
              }
            ],
            "errors": null
        }
        ```

    - **Status Code:** [404]
    - **Response Body:**
        ```json
        {
            "data": null,
            "status": [
              {
                "id": 1,
                "name": "FAILED"
              }
            ],
            "errors": "Transaction is not found"
        }
        ```
      
#### 4. Update Transaction

- **Metode:** [PUT]
- **Endpoint:** `/api/transactions/products/3/customers/1`
  #### Request
  ```json
  {
    "amount": 8,
    "status": false,
    "create_by": "user"
  }
  ```

  #### Response
    - **Status Code:** [200]
    - **Response Body:**
        ```json
        {
            "data": {
              "id": 9,
              "productID": "3",
              "productName": "Lemari",
              "amount": "8",
              "customerName": "Budi",
              "status": 0,
              "transactionDate": "2024-05-27 11:37:22",
              "createBy": "user",
              "createOn": "2024-05-27 11:37:22"
            },
            "status": [
              {
                "id": 0,
                "name": "SUCCESS"
              }
            ],
            "errors": null
        }
        ```

    - **Status Code:** [404]
    - **Response Body:**
        ```json
        {
            "data": null,
            "status": [
              {
                "id": 1,
                "name": "FAILED"
              }
            ],
            "errors": "Transaction is not found"
        }
        ```
      
#### 5. Delete Transaction

- **Metode:** [DELETE]
- **Endpoint:** `/api/transactions/products/3/customers/1`
  
  #### Response
    - **Status Code:** [200]
    - **Response Body:**
        ```json
        {
            "data": "OK",
            "status": [
              {
                "id": 0,
                "name": "SUCCESS"
              }
            ],
            "errors": null
        }
        ```

    - **Status Code:** [404]
    - **Response Body:**
        ```json
        {
            "data": null,
            "status": [
              {
                "id": 1,
                "name": "FAILED"
              }
            ],
            "errors": "Transaction is not found"
        }
        ```
