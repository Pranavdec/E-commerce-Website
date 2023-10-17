CREATE TABLE Item (
  item_id INT AUTO_INCREMENT,
  item_name VARCHAR(50),
  price DECIMAL(10, 2),
  quantity INT,
  company_name VARCHAR(50),
  description TEXT,
  image_path varchar(100),
  category varchar(30),
  PRIMARY KEY (item_id),
  
  foreign key (company_name) references shopkeeper(Companyname)
);
