CREATE DATABASE streaks_db;

CREATE USER 'scala'@'localhost' IDENTIFIED BY 'functional';
GRANT ALL ON streaks_db.* TO 'scala'@'localhost';
FLUSH PRIVILEGES;

USE streaks_db;

CREATE TABLE habits (
    id INT(12) NOT NULL auto_increment PRIMARY KEY,
    description VARCHAR(64) NOT NULL,
    INDEX(`id`)	
) ENGINE InnoDB;

CREATE TABLE days (
    id INT(12) NOT NULL auto_increment PRIMARY KEY,
    habit_id INT(12) NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    INDEX (`habit_id`),
    CONSTRAINT FOREIGN KEY (`habit_id`) REFERENCES `habits` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE InnoDB;