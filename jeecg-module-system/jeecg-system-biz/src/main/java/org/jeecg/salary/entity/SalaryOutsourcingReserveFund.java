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
 * @TableName salary_outsourcing_reserve_fund
 */
@TableName(value ="salary_outsourcing_reserve_fund")
@Data
public class SalaryOutsourcingReserveFund implements Serializable {
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
     * 个人账号
     */
    @TableField(value = "personal_account")
    private String personalAccount;

    /**
     * 个人账号状态
     */
    @TableField(value = "personal_account_status")
    private String personalAccountStatus;

    /**
     * 手机号码
     */
    @TableField(value = "telephone")
    private String telephone;

    /**
     * 缴至年月
     */
    @TableField(value = "pay_to_date")
    private String payToDate;

    /**
     * 个人缴存基数
     */
    @TableField(value = "personal_deposit_base")
    private Double personalDepositBase;

    /**
     * 月缴存总额
     */
    @TableField(value = "total_monthly_deposits")
    private Double totalMonthlyDeposits;

    /**
     * 个人缴存比例
     */
    @TableField(value = "individual_deposit_ratio")
    private Double individualDepositRatio;

    /**
     * 单位缴存比例
     */
    @TableField(value = "company_deposit_ratio")
    private Double companyDepositRatio;

    /**
     * 个人月缴存额
     */
    @TableField(value = "personal_monthly_deposit")
    private Double personalMonthlyDeposit;

    /**
     * 单位月缴存额
     */
    @TableField(value = "company_monthly_deposit")
    private Double companyMonthlyDeposit;

    /**
     * 账户余额
     */
    @TableField(value = "account_balance")
    private Double accountBalance;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}