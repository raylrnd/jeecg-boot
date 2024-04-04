package org.jeecg.biz.salary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName salary_food
 */
@TableName(value ="salary_food")
@Data
public class SalaryFood implements Serializable {
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
     * 所属部门
     */
    @TableField(value = "sys_org_code")
    private String sysOrgCode;

    /**
     * 部门
     */
    @TableField(value = "department")
    private String department;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 员工编号
     */
    @TableField(value = "member_no")
    private String memberNo;

    /**
     * 金额（元）
     */
    @TableField(value = "food_subsidy")
    private double foodSubsidy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}