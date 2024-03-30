package org.jeecg.biz.salary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName salary_huize_total
 */
@TableName(value ="salary_huize_total")
@Data
public class SalaryHuizeTotal implements Serializable {
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
     * 银行卡号
     */
    @TableField(value = "bank_no")
    private String bankNo;

    /**
     * 人员姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 部门
     */
    @TableField(value = "department")
    private String department;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 入职时间
     */
    @TableField(value = "entry_time")
    private Date entryTime;

    /**
     * 司龄
     */
    @TableField(value = "job_time")
    private Double jobTime;

    /**
     * 岗位名称
     */
    @TableField(value = "job_title")
    private String jobTitle;

    /**
     * 薪级
     */
    @TableField(value = "salary_level")
    private String salaryLevel;

    /**
     * 薪档
     */
    @TableField(value = "salary_grade")
    private Double salaryGrade;

    /**
     * 年功工资
     */
    @TableField(value = "year_merit")
    private Double yearMerit;

    /**
     * 基本工资
     */
    @TableField(value = "basic_salary")
    private Double basicSalary;

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
     * 房改补贴
     */
    @TableField(value = "housing_reform_reward")
    private Double housingReformReward;

    /**
     * 还建/跑道补贴
     */
    @TableField(value = "huanjianpaodaosubsidy")
    private Double huanjianpaodaosubsidy;

    /**
     * 夜餐补贴
     */
    @TableField(value = "night_food_subsidy")
    private Double nightFoodSubsidy;

    /**
     * 减人不减资（元）
     */
    @TableField(value = "jianrenbujianzi")
    private Double jianrenbujianzi;

    /**
     * 高温补贴
     */
    @TableField(value = "high_temper")
    private Double highTemper;

    /**
     * 先进奖励
     */
    @TableField(value = "advanced")
    private Double advanced;

    /**
     * 安全生产工作岗位津贴
     */
    @TableField(value = "safety_job_subsidy")
    private Double safetyJobSubsidy;

    /**
     * 党建考核奖金
     */
    @TableField(value = "party_build_subsidy")
    private Double partyBuildSubsidy;

    /**
     * 应急救援岗绩效
     */
    @TableField(value = "rescue_subsidy")
    private Double rescueSubsidy;

    /**
     * 安全绩效
     */
    @TableField(value = "safety_subsidy")
    private Double safetySubsidy;

    /**
     * 其他补发
     */
    @TableField(value = "other_reward")
    private Double otherReward;

    /**
     * 其他补扣
     */
    @TableField(value = "othe_deduct")
    private Double otheDeduct;

    /**
     * 减病假
     */
    @TableField(value = "sick")
    private Double sick;

    /**
     * 减事假
     */
    @TableField(value = "personal_leave_days_deduct")
    private Double personalLeaveDaysDeduct;

    /**
     * 本部其他应发合计
     */
    @TableField(value = "central_other_should_fund")
    private Double centralOtherShouldFund;

    /**
     * 本部应发合计
     */
    @TableField(value = "central_should_fund")
    private Double centralShouldFund;

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
     * 专项扣除数
     */
    @TableField(value = "special_deduction")
    private Double specialDeduction;

    /**
     * 个人工会费
     */
    @TableField(value = "party_personal")
    private Double partyPersonal;

    /**
     * 威尼斯宿舍住房费用
     */
    @TableField(value = "weinisi_price")
    private Double weinisiPrice;

    /**
     * 应纳税所得额
     */
    @TableField(value = "should_tax")
    private Double shouldTax;

    /**
     * 本部预扣预缴个税
     */
    @TableField(value = "ready_deduct_tax")
    private Double readyDeductTax;

    /**
     * 本部代扣款合计
     */
    @TableField(value = "replace_deduct")
    private Double replaceDeduct;

    /**
     * 本部实发工资
     */
    @TableField(value = "real_salary")
    private Double realSalary;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private Double remark;

    /**
     * 值班工资
     */
    @TableField(value = "duty")
    private Double duty;

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
     * 个人所得税 （导入）
     */
    @TableField(value = "tax_personal")
    private Double taxPersonal;

    /**
     * 浮动绩效
     */
    @TableField(value = "float_pay")
    private Double floatPay;

    /**
     * 加班工资
     */
    @TableField(value = "overtime")
    private Double overtime;

    /**
     * 法定节假日加班费
     */
    @TableField(value = "holiday_work")
    private Double holidayWork;

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
     * 房租
     */
    @TableField(value = "house")
    private Double house;

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
     * 是否为交通银行
     */
    @TableField(value = "is_jiaotong_bank")
    private String isJiaotongBank;

    /**
     * 身份证号
     */
    @TableField(value = "id_card_no")
    private String idCardNo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}