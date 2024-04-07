package org.jeecg.biz.salary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName salary_total
 */
@TableName(value ="salary_total")
@Data
public class SalaryTotal implements Serializable {
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
     * 身份证号
     */
    @TableField(value = "id_card_no")
    private String idCardNo;

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
     * 年功工资
     */
    @TableField(value = "year_merit")
    private double yearMerit;

    /**
     * 基本工资
     */
    @TableField(value = "basic_salary")
    private double basicSalary;

    /**
     * 岗位(职级)工资
     */
    @TableField(value = "post")
    private double post;

    /**
     * 绩效工资
     */
    @TableField(value = "merit")
    private double merit;

    /**
     * 房改补贴
     */
    @TableField(value = "housing_reform_reward")
    private double housingReformReward;

    /**
     * 还建/跑道补贴
     */
    @TableField(value = "huanjianpaodaosubsidy")
    private double huanjianpaodaosubsidy;

    /**
     * 值班补贴
     */
    @TableField(value = "on_duty_subsidy")
    private double onDutySubsidy;

    /**
     * 夜餐补贴
     */
    @TableField(value = "night_food_subsidy")
    private double nightFoodSubsidy;

    /**
     * 减人不减资（元）
     */
    @TableField(value = "jianrenbujianzi")
    private double jianrenbujianzi;

    /**
     * 高温补贴
     */
    @TableField(value = "high_temper")
    private double highTemper;

    /**
     * 先进奖励
     */
    @TableField(value = "advanced")
    private double advanced;

    /**
     * 安全生产工作岗位津贴
     */
    @TableField(value = "safety_job_subsidy")
    private double safetyJobSubsidy;

    /**
     * 党建考核奖金
     */
    @TableField(value = "party_build_subsidy")
    private double partyBuildSubsidy;

    /**
     * 应急救援岗绩效
     */
    @TableField(value = "rescue_subsidy")
    private double rescueSubsidy;

    /**
     * 安全绩效
     */
    @TableField(value = "safety_subsidy")
    private double safetySubsidy;

    /**
     * 其他补发
     */
    @TableField(value = "other_reward")
    private double otherReward;

    /**
     * 其他补扣
     */
    @TableField(value = "othe_deduct")
    private double otheDeduct;

    /**
     * 减病假
     */
    @TableField(value = "sick")
    private double sick;

    /**
     * 减事假
     */
    @TableField(value = "personal_leave_days_deduct")
    private double personalLeaveDaysDeduct;

    /**
     * 其他应发合计
     */
    @TableField(value = "other_should_fund")
    private double otherShouldFund;

    /**
     * 应发合计
     */
    @TableField(value = "should_fund")
    private double shouldFund;

    /**
     * 公积金个人缴交额
     */
    @TableField(value = "reserve_personal_fund")
    private double reservePersonalFund;

    /**
     * 养老保险个人缴交额
     */
    @TableField(value = "eged_personal")
    private double egedPersonal;

    /**
     * 失业保险个人缴交额
     */
    @TableField(value = "lose_job_personal")
    private double loseJobPersonal;

    /**
     * 医疗保险个人缴交额
     */
    @TableField(value = "medical_personal")
    private double medicalPersonal;

    /**
     * 年金个人缴交额
     */
    @TableField(value = "enterprise_annuity_personal")
    private double enterpriseAnnuityPersonal;

    /**
     * 专项扣除数
     */
    @TableField(value = "special_deduction")
    private double specialDeduction;

    /**
     * 个人工会费
     */
    @TableField(value = "party_personal")
    private double partyPersonal;

    /**
     * 威尼斯宿舍住房费用
     */
    @TableField(value = "weinisi_price")
    private double weinisiPrice;

    /**
     * 应纳税所得额
     */
    @TableField(value = "should_tax")
    private double shouldTax;

    /**
     * 本部预扣预缴个税
     */
    @TableField(value = "ready_deduct_tax")
    private double readyDeductTax;

    /**
     * 本部代扣款合计
     */
    @TableField(value = "replace_deduct")
    private double replaceDeduct;

    /**
     * 本部实发工资
     */
    @TableField(value = "real_salary")
    private double realSalary;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private double remark;


    /**
     * 加发其他（总额）
     */
    @TableField(value = "addition_other_total")
    private double additionOtherTotal;

    /**
     * 考勤扣款
     */
    @TableField(value = "checking_in_deduct")
    private double checkingInDeduct;

    /**
     * 年终奖
     */
    @TableField(value = "year_end")
    private double yearEnd;

    /**
     * 经济补偿金
     */
    @TableField(value = "economic_compensation")
    private double economicCompensation;

    /**
     * 工伤保险单位缴交额
     */
    @TableField(value = "injury_company")
    private double injuryCompany;

    /**
     * 补充医疗保险单位缴交额
     */
    @TableField(value = "addition_medical_company")
    private Double additionMedicalCompany;

    /**
     * 是否为交通银行
     */
    @TableField(value = "is_jiaotong_bank")
    private String isJiaotongBank;

    /**
     * 个税调整
     */
    @TableField(value = "tax_fix")
    private double taxFix;

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
    private double jobTime;

    /**
     * 岗位名称
     */
    @TableField(value = "job_title")
    private String jobTitle;

    /**
     * 值班工资
     */
    @TableField(value = "duty")
    private double duty;

    /**
     * 公积金单位缴交额
     */
    @TableField(value = "reserve_company_fund")
    private double reserveCompanyFund;

    /**
     * 养老保险单位缴交额
     */
    @TableField(value = "eged_company")
    private double egedCompany;

    /**
     * 失业保险单位缴交额
     */
    @TableField(value = "lose_job_company")
    private double loseJobCompany;

    /**
     * 医疗保险单位缴交额
     */
    @TableField(value = "medical_company")
    private double medicalCompany;

    /**
     * 年金单位缴交额
     */
    @TableField(value = "enterprise_annuity_company")
    private double enterpriseAnnuityCompany;

    /**
     * 住宿补贴
     */
    @TableField(value = "house_subsidy")
    private double houseSubsidy;

    /**
     * 实习补贴
     */
    @TableField(value = "intern_subsidy")
    private double internSubsidy;

    /**
     * 岗位补贴
     */
    @TableField(value = "job_subsidy")
    private double jobSubsidy;

    /**
     * 见习补贴
     */
    @TableField(value = "jianx_subsidy")
    private double jianxSubsidy;

    /**
     * 实习生总计
     */
    @TableField(value = "total")
    private double total;

    /**
     * 岗位工资
     */
    @TableField(value = "job_salary")
    private double jobSalary;

    /**
     * 部门类别
     */
    @TableField(value = "department_cat")
    private String departmentCat;

    /**
     * 人员类别
     */
    @TableField(value = "member_cat")
    private String memberCat;

    /**
     * 管理费用
     */
    @TableField(value = "manage_fee")
    private Double manageFee;

    /**
     * 空港联系单备注
     */
    @TableField(value = "outsourcing_connect_remark")
    private String outsourcingConnectRemark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}