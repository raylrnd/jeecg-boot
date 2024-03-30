package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName salary_intern_total
 */
@TableName(value ="salary_intern_total")
@Data
public class SalaryInternTotal implements Serializable {
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
     * 入职时间
     */
    @TableField(value = "entry_time")
    private Date entryTime;

    /**
     * 住宿补贴
     */
    @TableField(value = "house_subsidy")
    private Double houseSubsidy;

    /**
     * 实习补贴
     */
    @TableField(value = "intern_subsidy")
    private Double internSubsidy;

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
     * 值班补贴
     */
    @TableField(value = "on_duty_subsidy")
    private Double onDutySubsidy;

    /**
     * 岗位补贴
     */
    @TableField(value = "job_subsidy")
    private Double jobSubsidy;

    /**
     * 见习补贴
     */
    @TableField(value = "jianx_subsidy")
    private Double jianxSubsidy;

    /**
     * 其他补发
     */
    @TableField(value = "other_subsidy")
    private Double otherSubsidy;

    /**
     * 其他补扣
     */
    @TableField(value = "othe_deduct")
    private Double otheDeduct;

    /**
     * 考勤扣款
     */
    @TableField(value = "checking_in_deduct")
    private Double checkingInDeduct;

    /**
     * 实习生总计
     */
    @TableField(value = "total")
    private Double total;

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
     * 本部实发工资
     */
    @TableField(value = "real_salary")
    private Double realSalary;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 岗位(职级)工资
     */
    @TableField(value = "post")
    private Double post;

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
     * 扣工资
     */
    @TableField(value = "deduct")
    private Double deduct;

    /**
     * 加发其他（总额）
     */
    @TableField(value = "addition_other_total")
    private Double additionOtherTotal;

    /**
     * 个人所得税 （导入）
     */
    @TableField(value = "tax_personal")
    private Double taxPersonal;

    /**
     * 工伤保险单位缴交额
     */
    @TableField(value = "injury_company")
    private Double injuryCompany;

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
     * 身份证号
     */
    @TableField(value = "id_card_no")
    private String idCardNo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}