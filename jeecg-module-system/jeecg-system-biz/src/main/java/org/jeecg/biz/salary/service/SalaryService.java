package org.jeecg.biz.salary.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import generator.mapper.*;
import org.apache.commons.collections4.CollectionUtils;
import org.jeecg.biz.salary.entity.*;
import org.jeecg.biz.salary.exception.JeecgSalaryException;
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

    @Autowired
    private SalaryTaxFirstMapper salaryTaxFirstMapper;

    @Autowired
    private SalaryCentralTotalMapper salaryCentralTotalMapper;

    @Autowired
    private SalaryHuizeTotalMapper salaryHuizeTotalMapper;

    @Autowired
    private SalaryInternTotalMapper salaryInternTotalMapper;

    @Autowired
    private SalaryOutsourcingTotalMapper salaryOutsourcingTotalMapper;

    private static final int QUERY_PAGE_SIZE = 500;
    // 住宿补贴
    private static final double ACCOMMODATION_SUBSIDY = 300.0;
    // 见习补贴
    private static final double NOVICIATE_SUBSIDY = 500.0;

    private void handleSalaryCompute() throws Exception {
        // 先把输出表数据删了
        // 本部报表
        int deleteSalaryCentralReport = salaryCentralReportMapper.delete(new LambdaQueryWrapper<SalaryCentralReport>().gt(SalaryCentralReport::getId,-1));

        // 查询输入数据
        // 本部养老金
        Page<SalaryCentralAgedFund> salaryCentralAgedFundPage = salaryCentralAgedFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryCentralAgedFundPage);
        // 本部企业年金
        Page<SalaryCentralEnterpriseFund> salaryCentralEnterpriseFundPage = salaryCentralEnterpriseFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryCentralEnterpriseFundPage);
        // 本部公积金
        Page<SalaryCentralReserveFund> salaryCentralReserveFundPage = salaryCentralReserveFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryCentralReserveFundPage);
        // 本部社保单
        Page<SalaryCentralSocialSecurityFund> salaryCentralSocialSecurityFundPage = salaryCentralSocialSecurityFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryCentralSocialSecurityFundPage);
        // 各部门绩效
        Page<SalaryDepartmentPerformance> salaryDepartmentPerformancePage = salaryDepartmentPerformanceMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryDepartmentPerformancePage);
        // 实习生社保单
        Page<SalaryInternSocialFund> salaryInternSocialFundPage = salaryInternSocialFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryInternSocialFundPage);
        // 空港公积金
        Page<SalaryOutsourcingReserveFund> salaryOutsourcingReserveFundPage = salaryOutsourcingReserveFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryOutsourcingReserveFundPage);
        // 空港社保单
        Page<SalaryOutsourcingSocialFund> salaryOutsourcingSocialFundPage = salaryOutsourcingSocialFundMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryOutsourcingSocialFundPage);
        // 附加字段
        Page<SalaryAddition> salaryAdditionPage = additionMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        // 税务表
        Page<SalaryTax> salaryTaxPage = salaryTaxMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryTaxPage);
        // 人员信息库
        Page<SalaryUserBaseInfo> salaryUserBaseInfoPage = salaryUserBaseInfoMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryUserBaseInfoPage);
        // 人员信息库
        Page<SalaryTaxFirst> salaryTaxFirstPage = salaryTaxFirstMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryTaxFirstPage);
        // 工资表--本部
        Page<SalaryCentralTotal> salaryCentralTotalPage = salaryCentralTotalMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryCentralTotalPage);
        // 工资表--惠泽
        Page<SalaryHuizeTotal> salaryHuizeTotalPage = salaryHuizeTotalMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryHuizeTotalPage);
        // 工资表--实习生
        Page<SalaryInternTotal> salaryInternTotalPage = salaryInternTotalMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryInternTotalPage);
        // 工资表--空港
        Page<SalaryOutsourcingTotal> salaryOutsourcingTotalPage = salaryOutsourcingTotalMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryOutsourcingTotalPage);

        // 计算本部工资报表
        SalaryCentralReport salaryCentralReport = new SalaryCentralReport();
        Map<String, SalaryCentralAgedFund> salaryCentralAgedFundMap = salaryCentralAgedFundPage.getRecords().stream().collect(Collectors.toMap(SalaryCentralAgedFund::getIdCardNo, Function.identity()));
        Map<String, SalaryCentralEnterpriseFund> salaryCentralEnterpriseFundMap = salaryCentralEnterpriseFundPage.getRecords().stream().collect(Collectors.toMap(SalaryCentralEnterpriseFund::getIdCardNo, Function.identity()));
        Map<String, SalaryCentralReserveFund> salaryCentralReserveFundMap = salaryCentralReserveFundPage.getRecords().stream().collect(Collectors.toMap(SalaryCentralReserveFund::getIdCardNo, Function.identity()));
        Map<String, SalaryCentralSocialSecurityFund> salaryCentralSocialSecurityFundMap = salaryCentralSocialSecurityFundPage.getRecords().stream().collect(Collectors.toMap(SalaryCentralSocialSecurityFund::getIdCardNo, Function.identity()));
        Map<String, SalaryAddition> salaryAdditionMap = salaryAdditionPage.getRecords().stream().collect(Collectors.toMap(SalaryAddition::getIdCardNo, Function.identity()));
        Map<String, SalaryDepartmentPerformance> salaryDepartmentPerformanceMap = salaryDepartmentPerformancePage.getRecords().stream().collect(Collectors.toMap(SalaryDepartmentPerformance::getIdCardNo, Function.identity()));
        Map<String, SalaryTax> salaryTaxMap = salaryTaxPage.getRecords().stream().collect(Collectors.toMap(SalaryTax::getIdCardNo, Function.identity()));
        Map<String, SalaryTaxFirst> salaryTaxFirstMap = salaryTaxFirstPage.getRecords().stream().collect(Collectors.toMap(SalaryTaxFirst::getIdCardNo, Function.identity()));
        Map<String, SalaryOutsourcingReserveFund> salaryOutsourcingReserveFundMap = salaryOutsourcingReserveFundPage.getRecords().stream().collect(Collectors.toMap(SalaryOutsourcingReserveFund::getIdCardNo, Function.identity()));
        Map<String, SalaryOutsourcingSocialFund> salaryOutsourcingSocialFundMap = salaryOutsourcingSocialFundPage.getRecords().stream().collect(Collectors.toMap(SalaryOutsourcingSocialFund::getIdCardNo, Function.identity()));
        Map<String, SalaryInternSocialFund> salaryInternSocialFundMap = salaryInternSocialFundPage.getRecords().stream().collect(Collectors.toMap(SalaryInternSocialFund::getIdCardNo, Function.identity()));

        // 工资计算月份
        Date computeTimeBase = new Date();
        List<SalaryUserBaseInfo> userBaseInfoRecords = salaryUserBaseInfoPage.getRecords();
        for (SalaryUserBaseInfo salaryUserBaseInfo : userBaseInfoRecords) {
            salaryCentralReport.setName(salaryUserBaseInfo.getName());
            // 年功工资
            double yearMerit = calYearMerit(salaryUserBaseInfo);
            salaryCentralReport.setYearMerit(yearMerit);
            // 基本工资
            double baseSalary = calFloatSalary(salaryUserBaseInfo.getBaseSalary(), computeTimeBase, salaryUserBaseInfo);
            // 岗位工资
            double jobSalary = calFloatSalary(salaryUserBaseInfo.getJobSalary(), computeTimeBase, salaryUserBaseInfo);
            // 岗位(职级)工资
            salaryCentralReport.setPost(BigDecimal.valueOf(baseSalary).add(BigDecimal.valueOf(jobSalary)).doubleValue());
            // 住宿补贴
            double accommodationSubsidy = "是".equals(salaryUserBaseInfo.getHasAccommodationSubsidy()) ? calFloatSalary(ACCOMMODATION_SUBSIDY, computeTimeBase, salaryUserBaseInfo) : 0.0;
            // 餐费
            double foodSubsidy = calFloatSalary(salaryUserBaseInfo.getLevel() == 4 ? 400 : 600, computeTimeBase, salaryUserBaseInfo);

            SalaryAddition salaryAddition = salaryAdditionMap.get(salaryUserBaseInfo.getIdCardNo());
            SalaryTax salaryTax = salaryTaxMap.get(salaryUserBaseInfo.getIdCardNo());
            SalaryDepartmentPerformance salaryDepartmentPerformance = salaryDepartmentPerformanceMap.get(salaryUserBaseInfo.getIdCardNo());
            SalaryTaxFirst salaryTaxFirst = salaryTaxFirstMap.get(salaryUserBaseInfo.getIdCardNo());

            // 其他补发
            double otherReward = salaryAddition.getSafetyReward() + salaryAddition.getOtherReward() + salaryAddition.getSafetyJobReward() + salaryDepartmentPerformance.getEmergencyRescuePerformance();

            Calendar timeBaseC = DateUtils.getCalendar(computeTimeBase.getTime());
            timeBaseC.add(Calendar.MONTH, -1);
            // 上月天数
            int daysOfMonth = timeBaseC.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 值班补贴
            double onDutyDaySubsidy = 100 * salaryDepartmentPerformance.getAdministrationWeekendDays() + 200 * salaryDepartmentPerformance.getFirstDutyWorkdayDays() + 300 * salaryDepartmentPerformance.getFirstDutyWeekendDays()
                    + salaryUserBaseInfo.getLevel() == 4 ? 1600 * 3 * salaryDepartmentPerformance.getHolidayDays() / daysOfMonth : 1720 * 3 * salaryDepartmentPerformance.getHolidayDays() / daysOfMonth;
            // 还建/跑道补贴
            double huanjianpaodaoDaysSubsidy = salaryDepartmentPerformance.getHuanjianpaodaoDays() * 100;
            // 减病假
            double sickDayDeduct = (salaryUserBaseInfo.getLevel() == 4 ? 1600 : (baseSalary + jobSalary)) / daysOfMonth * 0.35 * salaryDepartmentPerformance.getSickDays();
            // 减事假
            double personalLeaveDaysDeduct = (salaryUserBaseInfo.getLevel() == 4 ? 1600 : (baseSalary + jobSalary)) / daysOfMonth * salaryDepartmentPerformance.getPersonalLeaveDays();
            // 工会经费
            double partyPersonal = (jobSalary + salaryDepartmentPerformance.getMonthPerformancePrice()) * 0.005;
            // 夜餐补贴
            double nightFoodSubsidy = salaryDepartmentPerformance.getDelayDays() * 30 + salaryDepartmentPerformance.getNightDays() * 20;
            // 1号值班天数
            double firstDutyDays = salaryDepartmentPerformance.getFirstDutyWorkdayDays() + salaryDepartmentPerformance.getFirstDutyWeekendDays();
            // 值班补贴+夜餐补贴+减人不减资（元）+高温补贴+其他补发-其他补扣-减事假-减病假
            double sumBase = onDutyDaySubsidy + nightFoodSubsidy
                    + salaryDepartmentPerformance.getJianrenbujianzi() + salaryAddition.getHighTemperatureReward() + salaryAddition.getOtherReward()
                    - salaryAddition.getOtherDeduct() - sickDayDeduct - personalLeaveDaysDeduct;
            // 其他应纳税所得合计
            double otherShouldTax = salaryTax.getOtherTaxWithoutMeal() + foodSubsidy;
            // 1:本部，2:惠泽，3:空港，4:实习生
            if (salaryUserBaseInfo.getLevel() == 1 || salaryUserBaseInfo.getLevel() == 2) {
                // 本部其他应发合计
                double centralOtherShouldFund = salaryAddition.getAdvancedReward() + salaryAddition.getPartyBuildingReward() + sumBase;
                // 本部应发合计
                double centralShouldFund = baseSalary + jobSalary + yearMerit + salaryDepartmentPerformance.getMonthPerformancePrice() + huanjianpaodaoDaysSubsidy
                        + centralOtherShouldFund + salaryAddition.getHousingReformReward();
                SalaryCentralEnterpriseFund salaryCentralEnterpriseFund = salaryCentralEnterpriseFundMap.get(salaryUserBaseInfo.getIdCardNo());
                SalaryCentralReserveFund salaryCentralReserveFund = salaryCentralReserveFundMap.get(salaryUserBaseInfo.getIdCardNo());
                SalaryCentralSocialSecurityFund salaryCentralSocialSecurityFund = salaryCentralSocialSecurityFundMap.get(salaryUserBaseInfo.getIdCardNo());
                SalaryCentralAgedFund salaryCentralAgedFund = salaryCentralAgedFundMap.get(salaryUserBaseInfo.getIdCardNo());
                // 社保=养老个人+失业个人+医保个人+企业年金
                double socialTotal = salaryCentralEnterpriseFund.getIndividualMonthlyPayment()
                        + salaryCentralSocialSecurityFund.getLoseJobPersonalPament() + salaryCentralSocialSecurityFund.getPersonalPament() + salaryCentralAgedFund.getPersonalPament();
                // 社保+公积金
                double socialAndReserve = socialTotal + salaryCentralReserveFund.getPersonalMonthlyDeposit();
                // 本部应纳税所得额
                double centralShouldTax = (salaryCentralReserveFund.getPersonalMonthlyDeposit() > 3476 ?
                salaryCentralReserveFund.getPersonalMonthlyDeposit() + centralShouldFund + otherShouldTax - 3476 - (3476 + socialTotal + salaryTax.getSpecialDeduction() + 5000)
                : centralShouldFund + otherShouldTax - (socialAndReserve + salaryTax.getSpecialDeduction() + 5000)) + salaryTaxFirst.getFirstTax();
                // 本部预扣预缴个税
                double readyDeductTax = calReadyDeductTax(centralShouldTax, salaryTax.getAllYearTaxDeduction());
                // 本部代扣款合计
                double replaceDeduct = socialAndReserve + partyPersonal + salaryDepartmentPerformance.getWeinisiPrice() + readyDeductTax;
                // 本部实发工资
                double realSalary = centralShouldFund - replaceDeduct - salaryTax.getDeductPersonalTax();
                // 本部加发其他（总额）
                double addtionSalary = salaryAddition.getPartyBuildingReward() + salaryAddition.getHousingReformReward() + huanjianpaodaoDaysSubsidy + salaryDepartmentPerformance.getJianrenbujianzi() + salaryAddition.getOtherReward();
            } else if (salaryUserBaseInfo.getLevel() == 3) {
                SalaryOutsourcingReserveFund salaryOutsourcingReserveFund = salaryOutsourcingReserveFundMap.get(salaryUserBaseInfo.getIdCardNo());
                SalaryOutsourcingSocialFund salaryOutsourcingSocialFund = salaryOutsourcingSocialFundMap.get(salaryUserBaseInfo.getIdCardNo());
                // 社保公积金=公积金个人+失业个人+养老个人+医保个人
                double socialAndReserve = salaryOutsourcingSocialFund.getPersonalPament() + salaryOutsourcingSocialFund.getLoseJobPersonalPayment() + salaryOutsourcingReserveFund.getPersonalMonthlyDeposit() + salaryOutsourcingSocialFund.getAgedPersonalPament();
                // 岗位补贴
                double jobSubsidy = salaryDepartmentPerformance.getJobSubsidyDays() * 200;
                // 空港应发合计
                double outsourcingShouldFund = salaryAddition.getAdvancedReward() + salaryAddition.getSafetyJobReward() + jobSalary + baseSalary + salaryDepartmentPerformance.getMonthPerformancePrice() + jobSubsidy + huanjianpaodaoDaysSubsidy + sumBase;
                // 空港应纳税所得额 = (空港应发合计+餐费+其他应纳税所得合计 (除去餐补))-(养老个人+公积金个人+失业个人+医保个人+专项扣除数)-5000+上月空港应纳税所得额+差错调整金额
                double shouldTax = (outsourcingShouldFund + otherShouldTax) - (socialAndReserve + salaryTax.getSpecialDeduction()) - 5000 + salaryTaxFirst.getFirstTax() + salaryTax.getFixedTax();
                // 空港预扣预缴个税
                double readyDeductTax = calReadyDeductTax(shouldTax, salaryTax.getAllYearTaxDeduction());
                // 空港代扣款合计 = 公积金个人+失业个人+养老个人+医保个人+工会经费+空港预扣预缴个税
                double replaceDeduct = socialAndReserve + partyPersonal + readyDeductTax;
                // 空港实发工资 = 空港应发合计-空港代扣款合计-补扣个税
                double realSalary = outsourcingShouldFund - replaceDeduct - salaryTax.getDeductPersonalTax();
                // 空港加发其他（总额）
                double addtionSalary = salaryAddition.getHousingReformReward() + huanjianpaodaoDaysSubsidy + salaryDepartmentPerformance.getJianrenbujianzi() + salaryAddition.getOtherReward();
            } else {
                // 实习补贴
                double internshipSubsidy = calFloatSalary("地勤服务部".equals(salaryUserBaseInfo.getDepartment()) ? 1000 : 1600, computeTimeBase, salaryUserBaseInfo);
                // 见习补贴
                double noviciateSubsidy = "是".equals(salaryUserBaseInfo.getHasNoviciateSubsidy()) ? calFloatSalary(NOVICIATE_SUBSIDY, computeTimeBase, salaryUserBaseInfo) : 0.0;
                // 岗位补贴
                double jobSubsidy = salaryDepartmentPerformance.getJobSubsidyDays() * 100;
                // 实习生总计（元）= 实习岗位补贴+见习补贴+其他补发+高温补贴+住宿补贴+实习补贴+夜餐补贴+月绩效金额（元）+减人不减资（元）+值班补贴-其他补扣-考勤扣款
                double internshipTotal = jobSubsidy + noviciateSubsidy + accommodationSubsidy + internshipSubsidy + salaryDepartmentPerformance.getMonthPerformancePrice() + sumBase;
                // 实习生应纳税所得额
                double shouldTax = internshipTotal + salaryTaxFirst.getFirstTax() + foodSubsidy - salaryTax.getSpecialDeduction() - 5000;
                // 实习生预扣预缴个税
                double readyDeductTax = calReadyDeductTax(shouldTax, salaryTax.getAllYearTaxDeduction());
                // 实习生实发工资
                double realSalary = internshipTotal - readyDeductTax;
                // 实习生加发其他（总额）
                double addtionSalary = accommodationSubsidy + salaryDepartmentPerformance.getJianrenbujianzi() + jobSubsidy + noviciateSubsidy + salaryAddition.getOtherReward();
                SalaryInternSocialFund salaryInternSocialFund = salaryInternSocialFundMap.get(salaryUserBaseInfo.getIdCardNo());
                // 特定人员单项工伤保险
                salaryInternSocialFund.getInjuryPament();
            }
        }
    }

    private double calReadyDeductTax(double shouldTax, Double allYearTaxDeduction) {
        double taxRate = 0.0;
        double deduction = 0.0;
        double taxToBeWithheld = 0.0;

        // 根据应纳税所得额确定税率和速算扣除数
        if (shouldTax <= 36000) {
            taxRate = 0.03;
            deduction = 2520;
        } else if (shouldTax <= 144000) {
            taxRate = 0.1;
            deduction = 16920;
        } else if (shouldTax <= 300000) {
            taxRate = 0.2;
            deduction = 31920;
        } else if (shouldTax <= 420000) {
            taxRate = 0.25;
            deduction = 52920;
        } else if (shouldTax <= 660000) {
            taxRate = 0.3;
            deduction = 85920;
        } else if (shouldTax <= 960000) {
            taxRate = 0.35;
            deduction = 181920;
        } else {
            taxRate = 0.45;
            deduction = 181920;
        }
        // 计算应预扣预缴的个税
        taxToBeWithheld = Math.max(shouldTax * taxRate - deduction, 0) - allYearTaxDeduction;

        // 如果计算结果小于0，则不需要预扣预缴个税，否则按照计算结果预扣预缴
        return taxToBeWithheld < 0 ? 0 : taxToBeWithheld;
    }

    private void checkPage(Page<?> page) {
        if (page == null || CollectionUtils.isEmpty(page.getRecords()) || page.getSize() >= QUERY_PAGE_SIZE) {
            throw new JeecgSalaryException("某个表单数据为空或者超过" + QUERY_PAGE_SIZE + "条数据！请检查表单");
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
