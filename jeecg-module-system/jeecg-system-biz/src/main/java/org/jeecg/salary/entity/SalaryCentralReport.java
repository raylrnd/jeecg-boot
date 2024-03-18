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
 * @TableName salary_central_report
 */
@TableName(value ="salary_central_report")
@Data
public class SalaryCentralReport implements Serializable {
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
     * 人员姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 年功工资
     */
    @TableField(value = "year_merit")
    private Double yearMerit;

    /**
     * 岗位(职级)工资
     */
    @TableField(value = "post")
    private Double post;

    /**
     * 绩效工资
     */
    @TableField(value = "merit")
    private Double merit;

    /**
     * 值班工资
     */
    @TableField(value = "duty")
    private Double duty;

    /**
     * 高温津贴
     */
    @TableField(value = "high_temper")
    private Double highTemper;

    /**
     * 安全奖
     */
    @TableField(value = "safety")
    private Double safety;

    /**
     * 加发其他（总额）
     */
    @TableField(value = "addition_other_total")
    private Double additionOtherTotal;

    /**
     * 加发其他(非总额）
     */
    @TableField(value = "addition_other")
    private Double additionOther;

    /**
     * 扣工资
     */
    @TableField(value = "deduct")
    private Double deduct;

    /**
     * 考勤扣款
     */
    @TableField(value = "checking_in_deduct")
    private Double checkingInDeduct;

    /**
     * 年终奖
     */
    @TableField(value = "year_end")
    private Double yearEnd;

    /**
     * 经济补偿金
     */
    @TableField(value = "economic_compensation")
    private Double economicCompensation;

    /**
     * 公积金个人缴交额
     */
    @TableField(value = "reserve_personal_fund")
    private Double reservePersonalFund;

    /**
     * 养老保险个人缴交额
     */
    @TableField(value = "eged_personal")
    private Double egedPersonal;

    /**
     * 失业保险个人缴交额
     */
    @TableField(value = "lose_job_personal")
    private Double loseJobPersonal;

    /**
     * 医疗保险个人缴交额
     */
    @TableField(value = "medical_personal")
    private Double medicalPersonal;

    /**
     * 年金个人缴交额
     */
    @TableField(value = "enterprise_annuity_personal")
    private Double enterpriseAnnuityPersonal;

    /**
     * 公积金单位缴交额
     */
    @TableField(value = "reserve_company_fund")
    private Double reserveCompanyFund;

    /**
     * 养老保险单位缴交额
     */
    @TableField(value = "eged_company")
    private Double egedCompany;

    /**
     * 失业保险单位缴交额
     */
    @TableField(value = "lose_job_company")
    private Double loseJobCompany;

    /**
     * 医疗保险单位缴交额
     */
    @TableField(value = "medical_company")
    private Double medicalCompany;

    /**
     * 工伤保险单位缴交额
     */
    @TableField(value = "injury_company")
    private Double injuryCompany;

    /**
     * 补充医疗保险单位缴交额
     */
    @TableField(value = "addtion_medical_company")
    private Double addtionMedicalCompany;

    /**
     * 年金单位缴交额
     */
    @TableField(value = "enterprise_annuity_company")
    private Double enterpriseAnnuityCompany;

    /**
     * 个人工会费
     */
    @TableField(value = "party_personal")
    private Double partyPersonal;

    /**
     * 房租
     */
    @TableField(value = "house")
    private Double house;

    /**
     * 个人所得税 （导入）
     */
    @TableField(value = "tax_personal")
    private Double taxPersonal;

    /**
     * 法定节假日加班费
     */
    @TableField(value = "holiday_work")
    private Double holidayWork;

    /**
     * 加班工资
     */
    @TableField(value = "overtime")
    private Double overtime;

    /**
     * 浮动绩效
     */
    @TableField(value = "float_pay")
    private Double floatPay;

    /**
     * 补税工资2(总额)
     */
    @TableField(value = "addtion_tax_total")
    private Double addtionTaxTotal;

    /**
     * 补发工资
     */
    @TableField(value = "addtion_pay")
    private Double addtionPay;

    /**
     * 浮动绩效备注
     */
    @TableField(value = "float_pay_remark")
    private Double floatPayRemark;

    /**
     * 补税工资(非总额)
     */
    @TableField(value = "addtion_tax")
    private Double addtionTax;

    /**
     * 扣工资-税后
     */
    @TableField(value = "deduct_after_tax")
    private Double deductAfterTax;

    /**
     * 绩效年薪
     */
    @TableField(value = "year_pay")
    private Double yearPay;

    /**
     * 经营者绩效年薪
     */
    @TableField(value = "owner_year")
    private Double ownerYear;

    /**
     * 先进奖励
     */
    @TableField(value = "advanced")
    private Double advanced;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}