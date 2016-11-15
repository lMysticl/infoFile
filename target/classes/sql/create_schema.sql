CREATE TABLE `statistics_db`.`file` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(500) NOT NULL,
  `file` BLOB NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `statistics_db`.`statistics_by_file` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `line` VARCHAR(500) NOT NULL,
  `longest_word` INT NOT NULL,
  `shortest_word` INT NOT NULL,
  `line_length` INT NOT NULL,
  `average_word_length` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));

 CREATE TABLE `statistics_db`.`aggregate_statistic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `aggregate_longest_word` INT NOT NULL,
  `aggregate_shortest_word` INT NOT NULL,
  `aggregate_line_length` INT NOT NULL,
  `aggregate_average_word_length` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));
  