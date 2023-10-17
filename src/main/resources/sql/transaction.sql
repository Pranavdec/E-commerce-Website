CREATE TABLE Transaction (
  transaction_id INT AUTO_INCREMENT,
  user_email varchar(50),
  item_id INT,
  quantity INT,
  time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  company_name VARCHAR(50),
  
  PRIMARY KEY (transaction_id),
  FOREIGN KEY (user_email) REFERENCES User(Email),
  FOREIGN KEY (item_id) REFERENCES Item(item_id),
  FOREIGN KEY (company_name) REFERENCES shopkeeper(Companyname)
);
