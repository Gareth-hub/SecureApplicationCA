CREATE TABLE `demo_db`.`staff` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `age` VARCHAR(45) NOT NULL,
  `salary` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
  INSERT INTO staff (id, name, email, age, salary) VALUES ("1", "mark", "theceo@gmail.com", "50", "110000000000");
  INSERT INTO staff (id, name, email, age, salary) VALUES ("2", "Josh", "theintern@gmail.com", "20", "15000");
  INSERT INTO staff (id, name, email, age, salary) VALUES ("3", "Shawn", "enterylevel@gmail.com", "35", "350000");
  
  CREATE TABLE `demo_db`.`user_accounts` (
  `account_id` INT NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `confirmpassword` VARCHAR(45) NOT NULL,
  `clearancecode` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`account_id`));
  
INSERT INTO user_account (`account_id`, `firstname`, `lastname`, `username`, `email`, `password`, `clearancecode`) VALUES ('67', 'No', 'Body', 'Nobody', 'nobody@gmail.com', '0101', '7772');
INSERT INTO user_account (`account_id`, `firstname`, `lastname`, `username`, `email`, `password`, `clearancecode`) VALUES ('25', 'john', 'dough', 'johnn', 'john99@hotmail.com', '111', '333');
INSERT INTO user_account (`account_id`, `firstname`, `lastname`, `username`, `email`, `password`, `clearancecode`) VALUES ('22', 'Anthony', 'Shudder', 'Anton', 'mr@outlook.ie', '1371', '555');


CREATE TABLE `demo_db`.`user_querys` (
  `account_id` INT NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `query` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser_query`));
  
  INSERT INTO user_query (name, username, email, query) VALUES ("demo", "testing", "demo@gmail.com", "hellllppppppppppppp");
  INSERT INTO user_query (name, username, email, query) VALUES ("John", "Johnny", "johny@gmail.com", "Cant rememeber clearance code");
  INSERT INTO user_query (name, username, email, query) VALUES ("Former", "Employee", "wrongfullyfired@gmail.com", "U SUCK !!!!")

