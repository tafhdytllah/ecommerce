CREATE DATABASE ecommerce;

USE ecommerce;

DROP TABLE transactions, products, customers;
# SHOW TABLES;
# SELECT * FROM products;
# SELECT * FROM customers;
# SELECT * FROM transactions;
# desc transactions;
CREATE TABLE products(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO products(name) VALUES('Laptop'),('Kulkas'),('Lemari'),('Mesin Cuci'),('Kompor');

CREATE TABLE customers(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO customers(name) VALUES('Budi'),('Toni'),('Santi'),('Suci');

CREATE TABLE transactions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    customer_id INT NOT NULL,
    amount INT NOT NULL NOT NULL,
    status BOOLEAN NOT NULL,
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

INSERT INTO transactions(product_id, customer_id, amount, status, create_by)
VALUES (2,1,5,1, 'admin'), (1,1,3,1, 'admin'), (3,1,1,0,'admin'), (3,3,10,1,'admin');


select
    t.id,
    t.product_id,
    p.name as product_name,
    t.amount,
    c.name as customer_name,
    t.status,
    t.transaction_date,
    t.create_by,
    t.created_at
from
    transactions as t
join
    products as p on t.product_id = p.id
join
    customers as c on t.customer_id = c.id;


truncate table transactions;

select * from transactions;