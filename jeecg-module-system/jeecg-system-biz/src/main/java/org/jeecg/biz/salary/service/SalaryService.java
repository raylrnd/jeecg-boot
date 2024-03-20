package org.jeecg.biz.salary.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import generator.mapper.*;
import org.apache.commons.collections4.CollectionUtils;
import org.jeecg.biz.salary.entity.*;
import org.jeecg.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SalaryService {

    @Autowired
    private SalaryAdditionMapper additionMapper;

    @Autowired
    private SalaryCentralAgedFundMapper salaryCentralAgedFundMapper;

    @Autowired
    private SalaryCentralEnterpriseFundMapper salaryCentralEnterpriseFundMapper;

    @Autowired
    private SalaryCentralReportMapper salaryCentralReportMapper;

    @Autowired
    private SalaryCentralReserveFundMapper salaryCentralReserveFundMapper;

    @Autowired
    private SalaryCentralSocialSecurityFundMapper salaryCentralSocialSecurityFundMapper;

    @Autowired
    private SalaryDepartmentPerformanceMapper salaryDepartmentPerformanceMapper;

    @Autowired
    private SalaryInternSocialFundMapper salaryInternSocialFundMapper;

    @Autowired
    private SalaryOutsourcingReserveFundMapper salaryOutsourcingReserveFundMapper;

    @Autowired
    private SalaryOutsourcingSocialFundMapper salaryOutsourcingSocialFundMapper;

    @Autowired
    private SalaryTaxMapper salaryTaxMapper;

    @Autowired
    private SalaryUserBaseInfoMapper salaryUserBaseInfoMapper;

    private static final int QUERY_PAGE_SIZE = 500;
    // 住宿补贴
    private static final double ACCOMMODATION_SUBSIDY = 300.0;
    // 见习补贴
    private static final double NOVICIATE_SUBSIDY = 500.0;

    private void handleSalaryCompute() {
        // 先把输出表数据删了
        // 本部报表
        int deleteSalaryCentralReport = salaryCentralReportMapper.delete(new LambdaQueryWrapper<SalaryCentralReport>().gt(SalaryCentralReport::getId,-1));

        // 查询输入数据
        // 本部养老金
        Page<SalaryCentralAgedFund> salaryCentralAgedFundPage = salaryCentralAgedFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 本部企业年金
        Page<SalaryCentralEnterpriseFund> salaryCentralEnterpriseFundPage = salaryCentralEnterpriseFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 本部公积金
        Page<SalaryCentralReserveFund> salaryCentralReserveFundPage = salaryCentralReserveFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 本部社保单
        Page<SalaryCentralSocialSecurityFund> salaryCentralSocialSecurityFundPage = salaryCentralSocialSecurityFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 各部门绩效
        Page<SalaryDepartmentPerformance> salaryDepartmentPerformancePage = salaryDepartmentPerformanceMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 实习生社保单
        Page<SalaryInternSocialFund> salaryInternSocialFundPage = salaryInternSocialFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 空港公积金
        Page<SalaryOutsourcingReserveFund> salaryOutsourcingReserveFundPage = salaryOutsourcingReserveFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 空港社保单
        Page<SalaryOutsourcingSocialFund> salaryOutsourcingSocialFundPage = salaryOutsourcingSocialFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 附加字段
        Page<SalaryAddition> salaryAdditionPage = additionMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 税务表
        Page<SalaryTax> salaryTaxPage = salaryTaxMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 人员信息库
        Page<SalaryUserBaseInfo> salaryUserBaseInfoPage = salaryUserBaseInfoMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());

        if (salaryUserBaseInfoPage == null || CollectionUtils.isEmpty(salaryUserBaseInfoPage.getRecords())) {
            return;
        }

        // 计算本部工资报表
        SalaryCentralReport salaryCentralReport = new SalaryCentralReport();
        Map<String, SalaryCentralAgedFund> salaryCentralAgedFundMap = salaryCentralAgedFundPage.getRecords().stream().collect(Collectors.toMap(SalaryCentralAgedFund::getIdCardNo, Function.identity()));
        Map<String, SalaryCentralEnterpriseFund> salaryCentralEnterpriseFundMap = salaryCentralEnterpriseFundPage.getRecords().stream().collect(Collectors.toMap(SalaryCentralEnterpriseFund::getIdCardNo, Function.identity()));
        Map<String, SalaryCentralReserveFund> salaryCentralReserveFundMap = salaryCentralReserveFundPage.getRecords().stream().collect(Collectors.toMap(SalaryCentralReserveFund::getIdCardNo, Function.identity()));
        Map<String, SalaryCentralSocialSecurityFund> salaryCentralSocialSecurityFundMap = salaryCentralSocialSecurityFundPage.getRecords().stream().collect(Collectors.toMap(SalaryCentralSocialSecurityFund::getIdCardNo, Function.identity()));

        // 工资计算月份
        Date computeTimeBase = new Date();
        List<SalaryUserBaseInfo> userBaseInfoRecords = salaryUserBaseInfoPage.getRecords();
        for (SalaryUserBaseInfo salaryUserBaseInfo : userBaseInfoRecords) {
            salaryCentralReport.setName(salaryUserBaseInfo.getName());
            salaryCentralReport.setYearMerit(calYearMerit(salaryUserBaseInfo));
            // 基本工资
            double baseSalary = calFloatSalary(salaryUserBaseInfo.getBaseSalary(), computeTimeBase, salaryUserBaseInfo);
            // 岗位工资
            double jobSalary = calFloatSalary(salaryUserBaseInfo.getJobSalary(), computeTimeBase, salaryUserBaseInfo);
            // 岗位(职级)工资
            salaryCentralReport.setPost(BigDecimal.valueOf(baseSalary).add(BigDecimal.valueOf(jobSalary)).doubleValue());
            // 住宿补贴
            double accommodationSubsidy = "是".equals(salaryUserBaseInfo.getHasAccommodationSubsidy()) ? calFloatSalary(ACCOMMODATION_SUBSIDY, computeTimeBase, salaryUserBaseInfo) : 0.0;
            // 见习补贴
            double noviciateSubsidy = "是".equals(salaryUserBaseInfo.getHasNoviciateSubsidy()) ? calFloatSalary(NOVICIATE_SUBSIDY, computeTimeBase, salaryUserBaseInfo) : 0.0;
            // 餐费
//            double foodSubsidy = salaryUserBaseInfo.getLevel() == 4 ? 400 : 600;
        }
    }

    /**
     * 计算年功工资
     * @param salaryUserBaseInfo
     * @return
     */
    private double calYearMerit(SalaryUserBaseInfo salaryUserBaseInfo) {
        // 基本工资为0则年功工资为0
        if (salaryUserBaseInfo.getBaseSalary() == null || salaryUserBaseInfo.getBaseSalary() == 0) {
            return 0.0;
        } else if (salaryUserBaseInfo.getLeaveTime() == null) {
            return DateUtils.yearDiff(salaryUserBaseInfo.getEntryTime(), null) * 50.0;
        } else {
            return DateUtils.yearDiff(salaryUserBaseInfo.getEntryTime(), salaryUserBaseInfo.getLeaveTime()) * 50.0;
        }
    }

    private double calFloatSalary(double calItem, Date computeTimeBase, SalaryUserBaseInfo salaryUserBaseInfo) {
        Calendar entryTimeC = DateUtils.getCalendar(salaryUserBaseInfo.getEntryTime().getTime());
        Calendar timeBaseC = DateUtils.getCalendar(computeTimeBase.getTime());
        // 当月天数
        int daysOfMonth = timeBaseC.getActualMaximum(Calendar.DAY_OF_MONTH);
        BigDecimal calItemBd = new BigDecimal(calItem);
        if (salaryUserBaseInfo.getLeaveTime() != null) {
            Calendar leaveTimeC = DateUtils.getCalendar(salaryUserBaseInfo.getLeaveTime().getTime());
            // 当月入职且离职
            if (DateUtils.isSameMonth(salaryUserBaseInfo.getEntryTime(), salaryUserBaseInfo.getLeaveTime())) {
                int dateDiff = DateUtils.dateDiff('d', entryTimeC, leaveTimeC);
                return calItemBd.multiply(BigDecimal.valueOf(dateDiff)).divide(BigDecimal.valueOf(daysOfMonth), RoundingMode.HALF_UP).doubleValue();
            }
            // 当月离职
            if (DateUtils.isSameMonth(salaryUserBaseInfo.getLeaveTime(), computeTimeBase)) {
                int leaveDays = leaveTimeC.get(Calendar.DAY_OF_MONTH);
                return calItemBd.multiply(BigDecimal.valueOf(leaveDays)).divide(BigDecimal.valueOf(daysOfMonth), RoundingMode.HALF_UP).doubleValue();
            }
        }
        // 当月入职
        if (DateUtils.isSameMonth(salaryUserBaseInfo.getEntryTime(), computeTimeBase)) {
            int entryDays = entryTimeC.get(Calendar.DAY_OF_MONTH);
            return calItemBd.multiply(BigDecimal.valueOf(daysOfMonth - entryDays + 1)).divide(BigDecimal.valueOf(daysOfMonth), RoundingMode.HALF_UP).doubleValue();
        }
        return calItem;
    }
}
