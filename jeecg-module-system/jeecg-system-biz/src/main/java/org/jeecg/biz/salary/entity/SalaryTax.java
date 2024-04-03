package org.jeecg.biz.salary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName salary_tax
 */
@TableName(value ="salary_tax")
@Data
public class SalaryTax implements Serializable {
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
     * 1-12月扣税合计
     */
    @TableField(value = "all_year_tax_deduction")
    private double allYearTaxDeduction;

    /**
     * 补扣个税
     */
    @TableField(value = "deduct_personal_tax")
    private double deductPersonalTax;

    /**
     * 其他应纳税所得合计（除去餐补）
     */
    @TableField(value = "other_tax_without_meal")
    private double otherTaxWithoutMeal;

    /**
     * 专项扣除数
     */
    @TableField(value = "special_deduction")
    private double specialDeduction;

    /**
     * 差错调整金额
     */
    @TableField(value = "fixed_tax")
    private double fixedTax;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}