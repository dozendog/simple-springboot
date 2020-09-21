DROP TABLE IF EXISTS employee;


CREATE TABLE employee (
	  id INT AUTO_INCREMENT  PRIMARY KEY,
	  firstname VARCHAR(250) NOT NULL,
	  surename VARCHAR(250) NOT NULL,
	  titlename VARCHAR(250),
	  address VARCHAR(250),
	  dateofbirth VARCHAR(20),
	  citizenid VARCHAR(15),
	  nationality VARCHAR(50),
	  position VARCHAR(250),
	  salary DECIMAL(20,2),
	  gendar VARCHAR(20),
	  status INT
);
	 
	
INSERT INTO employee (firstname, surename, titlename, address, dateofbirth, citizenid,
nationality, position, salary, gendar, status) VALUES
  ('John', 'Wick', 'Mr.','123/2 Rama9 35 Bangkok Thailand','30/11/1975','1234567890123',
  'Thai','boogieman',200000,'male',0),
  ('Tony', 'Stark', 'Mr.','456/1 Rama4 102 Bangkok Thailand','30/11/1974','1234567890001',
  'Thai','engineer',300000,'male',0);