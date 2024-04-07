package org.jeecg.biz.salary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName salary_intern_social_fund
 */
@TableName(value ="salary_intern_social_fund")
@Data
public class SalaryInternSocialFund implements Serializable {
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
     * 证件号码
     */
    @TableField(value = "id_card_no")
    private String idCardNo;

    /**
     * 证件类型
     */
    @TableField(value = "id_card_type")
    private String idCardType;

    /**
     * 个人社保号
     */
    @TableField(value = "personal_social_no")
    private String personalSocialNo;

    /**
     * 费款所属期起
     */
    @TableField(value = "price_start_time")
    private String priceStartTime;

    /**
     * 费款所属期止
     */
    @TableField(value = "price_end_time")
    private String priceEndTime;

    /**
     * 特定人员单项工伤保险.缴费基数
     */
    @TableField(value = "injury_company_payment_base")
    private double injuryCompanyPaymentBase;

    /**
     * 特定人员单项工伤保险.应缴金额
     */
    @TableField(value = "injury_pament")
    private double injuryPament;

    /**
     * 单位部分合计
     */
    @TableField(value = "company_pament_total")
    private double companyPamentTotal;

    /**
     * 个人部分合计
     */
    @TableField(value = "personal_pament_total")
    private double personalPamentTotal;

    /**
     * 应缴金额合计
     */
    @TableField(value = "pament_total")
    private double pamentTotal;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}