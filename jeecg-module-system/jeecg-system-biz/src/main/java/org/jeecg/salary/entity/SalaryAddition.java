package org.jeecg.salary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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
    private Double economicReward;

    /**
     * 房改补贴
     */
    @TableField(value = "housing_reform_reward")
    private Double housingReformReward;

    /**
     * 高温补贴
     */
    @TableField(value = "high_temperature_reward")
    private Double highTemperatureReward;

    /**
     * 先进奖励
     */
    @TableField(value = "advanced_reward")
    private Double advancedReward;

    /**
     * 安全生产工作岗位津贴
     */
    @TableField(value = "safety_job_reward")
    private Double safetyJobReward;

    /**
     * 党建考核奖金
     */
    @TableField(value = "party_building_reward")
    private Double partyBuildingReward;

    /**
     * 安全绩效
     */
    @TableField(value = "safety_reward")
    private Double safetyReward;

    /**
     * 其他补发
     */
    @TableField(value = "other_reward")
    private Double otherReward;

    /**
     * 其他补扣
     */
    @TableField(value = "other_deduct")
    private Double otherDeduct;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}