package org.jeecg.biz.salary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName salary_central_enterprise_fund
 */
@TableName(value ="salary_central_enterprise_fund")
@Data
public class SalaryCentralEnterpriseFund implements Serializable {
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
     * 年基数
     */
    @TableField(value = "year_basis")
    private String yearBasis;

    /**
     * 企业月缴费额.正常缴费
     */
    @TableField(value = "regular_payment")
    private Double regularPayment;

    /**
     * 企业月缴费额.公共账户缴费
     */
    @TableField(value = "public_account_payment")
    private Double publicAccountPayment;

    /**
     * 企业月缴费额.企业缴费合计
     */
    @TableField(value = "total_enterprise_contributions")
    private Double totalEnterpriseContributions;

    /**
     * 个人月缴费额
     */
    @TableField(value = "individual_monthly_payment")
    private Double individualMonthlyPayment;

    /**
     * 月缴费合计
     */
    @TableField(value = "total_monthly_payment")
    private Double totalMonthlyPayment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}