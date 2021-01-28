DROP TABLE IF EXISTS fruitstock;
  
CREATE TABLE fruitstock (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  fruitname VARCHAR(10) NOT NULL,
  stockcount INT NOT NULL
);
