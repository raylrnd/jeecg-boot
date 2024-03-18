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
 * @TableName salary_outsourcing_social_fund
 */
@TableName(value ="salary_outsourcing_social_fund")
@Data
public class SalaryOutsourcingSocialFund implements Serializable {
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
     * 失业保险(单位缴纳).缴费基数
     */
    @TableField(value = "lose_job_company_payment_base")
    private Double loseJobCompanyPaymentBase;

    /**
     * 失业保险(单位缴纳).应缴金额
     */
    @TableField(value = "lose_job_company_pament")
    private Double loseJobCompanyPament;

    /**
     * 工伤保险.缴费基数
     */
    @TableField(value = "injury_company_payment_base")
    private Double injuryCompanyPaymentBase;

    /**
     * 工伤保险.应缴金额
     */
    @TableField(value = "injury_pament")
    private Double injuryPament;

    /**
     * 单位部分合计
     */
    @TableField(value = "company_pament_total")
    private Double companyPamentTotal;

    /**
     * 个人部分合计
     */
    @TableField(value = "personal_pament_total")
    private Double personalPamentTotal;

    /**
     * 应缴金额合计
     */
    @TableField(value = "pament_total")
    private Double pamentTotal;

    /**
     * 外资民营个体经济养老保险(单位缴纳).缴费基数
     */
    @TableField(value = "aged_company_payment_base")
    private Double agedCompanyPaymentBase;

    /**
     * 外资民营个体经济养老保险(单位缴纳).应缴金额
     */
    @TableField(value = "aged_company_pament")
    private Double agedCompanyPament;

    /**
     * 综合基本医疗保险(单位缴纳).缴费基数
     */
    @TableField(value = "company_pament_base")
    private Double companyPamentBase;

    /**
     * 综合基本医疗保险(单位缴纳).应缴金额
     */
    @TableField(value = "company_pament")
    private Double companyPament;

    /**
     * 综合基本医疗保险（退休）(单位缴纳).缴费基数
     */
    @TableField(value = "retire_company_pament_base")
    private Double retireCompanyPamentBase;

    /**
     * 综合基本医疗保险（退休）(单位缴纳).应缴金额
     */
    @TableField(value = "retire_company_pament")
    private Double retireCompanyPament;

    /**
     * 综合基本医疗保险(个人缴纳).缴费基数
     */
    @TableField(value = "personal_pament_base")
    private Double personalPamentBase;

    /**
     * 综合基本医疗保险(个人缴纳).应缴金额
     */
    @TableField(value = "personal_pament")
    private Double personalPament;

    /**
     * 失业保险(个人缴纳).缴费基数
     */
    @TableField(value = "lose_job_personal_payment_base")
    private Double loseJobPersonalPaymentBase;

    /**
     * 失业保险(个人缴纳).应缴金额
     */
    @TableField(value = "lose_job_personal_payment")
    private Double loseJobPersonalPayment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}