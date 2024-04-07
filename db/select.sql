SELECT
    department,
    department_cat,
    SUM(house_subsidy) AS house_subsidy_s,
    SUM(intern_subsidy) AS intern_subsidy_s,
    SUM(merit) AS merit_s,
    SUM(night_food_subsidy) AS night_food_subsidy_s,
    SUM(jianrenbujianzi) AS jianrenbujianzi_s,
    SUM(high_temper) AS high_temper_s,
    SUM(on_duty_subsidy) AS on_duty_subsidy_s,
    SUM(job_subsidy) AS job_subsidy_s,
    SUM(jianx_subsidy) AS jianx_subsidy_s,
    SUM(other_reward) AS other_reward_s,
    SUM(checking_in_deduct) AS checking_in_deduct_s,
    SUM(othe_deduct) AS othe_deduct_s,
    SUM(total) AS total_s,
    SUM(ready_deduct_tax) AS ready_deduct_tax_s,
    SUM(real_salary) AS real_salary_s
FROM
    salary_total
where member_cat = '2'
GROUP BY
    department_cat, department