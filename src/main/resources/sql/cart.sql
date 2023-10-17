CREATE TABLE Cart (
  cart_id INT AUTO_INCREMENT,
  user_email varchar(50),
  item_id INT,
  quantity INT,
  PRIMARY KEY (cart_id),
  FOREIGN KEY (user_email) REFERENCES User(Email),
  FOREIGN KEY (item_id) REFERENCES Item(item_id)
);
