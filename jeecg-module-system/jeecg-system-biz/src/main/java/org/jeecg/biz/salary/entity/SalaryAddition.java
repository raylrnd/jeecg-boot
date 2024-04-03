package org.jeecg.biz.salary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName salary_addition
 */
@TableName(value ="salary_addition")
@Data
public class SalaryAddition implements Serializable {
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
     * 经济补偿金
     */
    @TableField(value = "economic_reward")
    private double economicReward;

    /**
     * 房改补贴
     */
    @TableField(value = "housing_reform_reward")
    private double housingReformReward;

    /**
     * 高温补贴
     */
    @TableField(value = "high_temperature_reward")
    private double highTemperatureReward;

    /**
     * 先进奖励
     */
    @TableField(value = "advanced_reward")
    private double advancedReward;

    /**
     * 安全生产工作岗位津贴
     */
    @TableField(value = "safety_job_reward")
    private double safetyJobReward;

    /**
     * 党建考核奖金
     */
    @TableField(value = "party_building_reward")
    private double partyBuildingReward;

    /**
     * 安全绩效
     */
    @TableField(value = "safety_reward")
    private double safetyReward;

    /**
     * 其他补发
     */
    @TableField(value = "other_reward")
    private double otherReward;

    /**
     * 其他补扣
     */
    @TableField(value = "other_deduct")
    private double otherDeduct;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}