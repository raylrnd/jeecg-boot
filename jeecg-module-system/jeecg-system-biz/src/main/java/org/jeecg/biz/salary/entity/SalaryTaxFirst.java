package org.jeecg.biz.salary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * 
 * @TableName salary_tax_first
 */
@TableName(value ="salary_tax_first")
@Data
public class SalaryTaxFirst implements Serializable {

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
     * 上月累计应纳税所得额（首次提交需要）
     */
    @TableField(value = "first_tax")
    private Double firstTax;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}