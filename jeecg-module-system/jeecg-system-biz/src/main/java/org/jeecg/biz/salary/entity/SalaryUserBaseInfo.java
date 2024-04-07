package org.jeecg.biz.salary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName salary_user_base_info
 */
@TableName(value ="salary_user_base_info")
@Data
public class SalaryUserBaseInfo implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

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
     * 基本工资
     */
    @TableField(value = "base_salary")
    private double baseSalary;

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
     * 所属部门
     */
    @TableField(value = "sys_org_code")
    private String sysOrgCode;

    /**
     * 员工编号
     */
    @TableField(value = "member_no")
    private String memberNo;

    /**
     * 当月是否要买社保公积金企业年金
     */
    @TableField(value = "buy_social_security")
    private String buySocialSecurity;

    /**
     * 银行卡号
     */
    @TableField(value = "bank_no")
    private String bankNo;

    /**
     * 是否为交通银行
     */
    @TableField(value = "is_traffic_bank")
    private String isTrafficBank;

    /**
     * 入职时间
     */
    @TableField(value = "entry_time")
    private Date entryTime;

    /**
     * 离职时间
     */
    @TableField(value = "leave_time")
    private Date leaveTime;

    /**
     * 部门
     */
    @TableField(value = "department")
    private String department;

    /**
     * 岗位名称
     */
    @TableField(value = "job_title")
    private String jobTitle;

    /**
     * 岗位工资
     */
    @TableField(value = "job_salary")
    private double jobSalary;

    /**
     * 部门类别 1:一线部门，2:机关部门
     */
    @TableField(value = "department_cat")
    private String departmentCat;

    /**
     * 人员类别 1:本部，2:惠泽，3:空港，4:实习生
     */
    @TableField(value = "member_cat")
    private String memberCat;

    /**
     * 级别 1:领导班子，2:部门负责人，3:本部普通职工：4:实习生，5:劳派
     */
    @TableField(value = "level")
    private int level;

    /**
     * 薪级
     */
    @TableField(value = "salary_level")
    private String salaryLevel;

    /**
     * 薪档
     */
    @TableField(value = "salary_grade")
    private double salaryGrade;

    /**
     * 实习补贴
     */
    @TableField(value = "internship_subsidy")
    private double internshipSubsidy;

    /**
     * 手机号
     */
    @TableField(value = "telephone_no")
    private String telephoneNo;

    /**
     * 是否有住宿补贴
     */
    @TableField(value = "has_accommodation_subsidy")
    private String hasAccommodationSubsidy;

    /**
     * 是否有见习补贴
     */
    @TableField(value = "has_noviciate_subsidy")
    private String hasNoviciateSubsidy;

    /**
     * 管理费用
     */
    @TableField(value = "manage_fee")
    private Double manageFee;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}