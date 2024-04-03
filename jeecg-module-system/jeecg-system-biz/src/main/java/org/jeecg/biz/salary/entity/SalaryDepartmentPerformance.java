package org.jeecg.biz.salary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName salary_department_performance
 */
@TableName(value ="salary_department_performance")
@Data
public class SalaryDepartmentPerformance implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新日期
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 身份证号
     */
    @TableField(value = "id_card_no")
    private String idCardNo;

    /**
     * 所属部门
     */
    @TableField(value = "sys_org_code")
    private String sysOrgCode;

    /**
     * 月绩效分
     */
    @TableField(value = "month_performance_score")
    private double monthPerformanceScore;

    /**
     * 月绩效金额（元）
     */
    @TableField(value = "month_performance_price")
    private double monthPerformancePrice;

    /**
     * 减人不减资（元）
     */
    @TableField(value = "jianrenbujianzi")
    private double jianrenbujianzi;

    /**
     * 应急救援岗绩效
     */
    @TableField(value = "emergency_rescue_performance")
    private double emergencyRescuePerformance;

    /**
     * 延误天数
     */
    @TableField(value = "delay_days")
    private double delayDays;

    /**
     * 夜班天数
     */
    @TableField(value = "night_days")
    private double nightDays;

    /**
     * 还建/跑道值班天数
     */
    @TableField(value = "huanjianpaodao_days")
    private double huanjianpaodaoDays;

    /**
     * 行政周末值班天数
     */
    @TableField(value = "administration_weekend_days")
    private double administrationWeekendDays;

    /**
     * 节假日值班天数
     */
    @TableField(value = "holiday_days")
    private double holidayDays;

    /**
     * 1号值班天数(工作日)
     */
    @TableField(value = "first_duty_workday_days")
    private double firstDutyWorkdayDays;

    /**
     * 1号值班天数(周末)
     */
    @TableField(value = "first_duty_weekend_days")
    private double firstDutyWeekendDays;

    /**
     * 事假天数
     */
    @TableField(value = "personal_leave_days")
    private double personalLeaveDays;

    /**
     * 病假天数
     */
    @TableField(value = "sick_days")
    private double sickDays;

    /**
     * 岗位补贴天数
     */
    @TableField(value = "job_subsidy_days")
    private double jobSubsidyDays;

    /**
     * 威尼斯宿舍住房费用
     */
    @TableField(value = "weinisi_price")
    private double weinisiPrice;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}