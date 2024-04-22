DELIMITER $$

CREATE TRIGGER CheckPerformanceBeforeInsert
    BEFORE INSERT ON salary_department_performance FOR EACH ROW
BEGIN
    DECLARE v_max_merit DECIMAL(10,2);
    DECLARE v_name VARCHAR(255);
    DECLARE v_sys_org_code VARCHAR(255);
    DECLARE v_error_message VARCHAR(255);

    SELECT max_merit INTO v_max_merit
    FROM salary_department_config
    WHERE sys_org_code = NEW.sys_org_code;

    IF NEW.month_performance_price > v_max_merit THEN
        SET v_name = NEW.name;
        SET v_sys_org_code = NEW.sys_org_code;

        SET v_error_message = CONCAT('当前提供的绩效金额超过了所能上传的最大绩效金额，请检查. 员工姓名: ', v_name, ', 所属部门: ', v_sys_org_code);
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = v_error_message;
    END IF;
END$$

DELIMITER ;


DELIMITER $$

CREATE TRIGGER CheckPerformanceBeforeUpdate
    BEFORE UPDATE ON salary_department_performance FOR EACH ROW
BEGIN
    DECLARE v_max_merit DECIMAL(10,2);
    DECLARE v_name VARCHAR(255);
    DECLARE v_sys_org_code VARCHAR(255);
    DECLARE v_error_message VARCHAR(255);

    SELECT max_merit INTO v_max_merit
    FROM salary_department_config
    WHERE sys_org_code = OLD.sys_org_code;

    IF NEW.month_performance_price > v_max_merit THEN
        SET v_name = NEW.name;
        SET v_sys_org_code = NEW.sys_org_code;

        SET v_error_message = CONCAT('当前提供的绩效金额超过了所能上传的最大绩效金额，请检查. 员工姓名: ', v_name, ', 所属部门: ', v_sys_org_code);

        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = v_error_message;
    END IF;
END$$

DELIMITER ;

