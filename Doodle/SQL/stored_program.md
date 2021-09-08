```SQL
DROP PROCEDURE IF EXISTS ifProc3;
DELIMITER $$
CREATE PROCEDURE ifProc3()
BEGIN
    DECLARE point INT;
    DECLARE credit CHAR(1);
    SET point = 77;

    IF point >= 90 THEN
        SET credit = 'A';
    ELSEIF point >= 80 THEN
        SET credit = 'B';
    ELSEIF point >= 70 THEN
        SET credit = 'C';
    ELSEIF point >= 60 THEN
        SET credit = 'D';
    ELSE
        SET credit = 'F';
    END IF
    SELECT CONCAT('취득점수 : ', point), CONCAT('학점: ', credit);
END $$
DELIMITER;
CALL ifProc3();
```

```SQL
DROP PROCEDURE IF EXISTS whileProc;
DELIMITER $$
CREATE PROCEDURE whileProc()
BEGIN
    DECLARE i INT; 
    DECLARE hap INT; 

    SET i = 1;
    SET hap = 0;

        WHILE(i <= 100) DO
            SET hap = hap + i;
            SET i = i + 1;
        END WHILE;

        SELECT hap:
END $$
DELIMITER;
CALL whileProc();
```

```SQL
DELIMITER //
CREATE FUNCTION square(a INT) RETURNS DOUBLE
BEGIN
    RETURN a * a;
END
//
DELIMITER;

SELECT square(5);
```
