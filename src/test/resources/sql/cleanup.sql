CREATE PROCEDURE truncate_tables()
BEGIN
  DECLARE tblName CHAR(200);
  DECLARE done INT DEFAULT FALSE;
  DECLARE dbTables CURSOR FOR
    SELECT table_name
    FROM information_schema.tables
    WHERE table_schema = (SELECT DATABASE());
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN dbTables;
  SET FOREIGN_KEY_CHECKS = 0;

  read_loop: LOOP
    FETCH dbTables INTO tblName;
    IF done THEN
      LEAVE read_loop;
    END IF;
    SET @s = CONCAT('TRUNCATE TABLE ', tblName);
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END LOOP read_loop;

  CLOSE dbTables;
  SET FOREIGN_KEY_CHECKS = 1;
END
^

CALL truncate_tables();
DROP PROCEDURE IF EXISTS truncate_tables;