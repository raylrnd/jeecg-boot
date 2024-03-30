package generator.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName salary_outsourcing_total
 */
@TableName(value ="salary_outsourcing_total")
@Data
public class SalaryOutsourcingTotal implements Serializable {
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
     * 夜餐补贴
     */
    @TableField(value = "night_food_subsidy")
    private Double nightFoodSubsidy;

    /**
     * 值班补贴
     */
    @TableField(value = "on_duty_subsidy")
    private Double onDutySubsidy;

    /**
     * 减人不减资（元）
     */
    @TableField(value = "jianrenbujianzi")
    private Double jianrenbujianzi;

    /**
     * 还建/跑道补贴
     */
    @TableField(value = "huanjianpaodaosubsidy")
    private Double huanjianpaodaosubsidy;

    /**
     * 岗位补贴
     */
    @TableField(value = "job_subsidy")
    private Double jobSubsidy;

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
     * 高温补贴
     */
    @TableField(value = "high_temper")
    private Double highTemper;

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
     * 空港应发合计
     */
    @TableField(value = "outsourcing_should_fund")
    private Double outsourcingShouldFund;

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
     * 个人工会费
     */
    @TableField(value = "party_personal")
    private Double partyPersonal;

    /**
     * 专项扣除数
     */
    @TableField(value = "special_deduction")
    private Double specialDeduction;

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
     * 伙食补贴
     */
    @TableField(value = "food_subsidy")
    private Double foodSubsidy;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

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
     * 补发工资
     */
    @TableField(value = "addtion_pay")
    private Double addtionPay;

    /**
     * 是否为交通银行
     */
    @TableField(value = "is_jiaotong_bank")
    private String isJiaotongBank;

    /**
     * 单位公积金
     */
    @TableField(value = "enterprise_reservce")
    private Double enterpriseReservce;

    /**
     * 管理费用
     */
    @TableField(value = "manage_fee")
    private Double manageFee;

    /**
     * 单位社保
     */
    @TableField(value = "enterprise_social")
    private Double enterpriseSocial;

    /**
     * 小计
     */
    @TableField(value = "total")
    private Double total;

    /**
     * 身份证号
     */
    @TableField(value = "id_card_no")
    private String idCardNo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}