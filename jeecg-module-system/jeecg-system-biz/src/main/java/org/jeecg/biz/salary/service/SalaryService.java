package org.jeecg.biz.salary.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.jeecg.biz.salary.entity.*;
import org.jeecg.biz.salary.exception.JeecgSalaryException;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.mapper.*;
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
import java.util.stream.Stream;

@Service
public class SalaryService {

    @Autowired
    private SalaryAdditionMapper additionMapper;

    @Autowired
    private SalaryCentralAgedFundMapper salaryCentralAgedFundMapper;

    @Autowired
    private SalaryCentralEnterpriseFundMapper salaryCentralEnterpriseFundMapper;

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
    private SalaryTotalMapper salaryTotalMapper;

    private static final int QUERY_PAGE_SIZE = 500;
    // 住宿补贴
    private static final double ACCOMMODATION_SUBSIDY = 300.0;
    // 见习补贴
    private static final double NOVICIATE_SUBSIDY = 500.0;

    public void handleSalaryCompute(Date computeTimeBase) throws Exception {
        // 先把输出表数据删了
        // 本部报表
        int deleteReport = salaryTotalMapper.delete(new LambdaQueryWrapper<SalaryTotal>().gt(SalaryTotal::getId,-1));

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
        checkPage(salaryAdditionPage);
        // 税务表
        Page<SalaryTax> salaryTaxPage = salaryTaxMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryTaxPage);
        // 人员信息库
        Page<SalaryUserBaseInfo> salaryUserBaseInfoPage = salaryUserBaseInfoMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryUserBaseInfoPage);
        // 人员信息库
        Page<SalaryTaxFirst> salaryTaxFirstPage = salaryTaxFirstMapper.selectPage(new Page<>(0, QUERY_PAGE_SIZE), new QueryWrapper<>());
        checkPage(salaryTaxFirstPage);

        // 计算本部工资报表
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

        List<SalaryUserBaseInfo> userBaseInfoRecords = salaryUserBaseInfoPage.getRecords();
        for (SalaryUserBaseInfo salaryUserBaseInfo : userBaseInfoRecords) {
            // 年功工资
            double yearMerit = calYearMerit(salaryUserBaseInfo, computeTimeBase);
            // 基本工资
            double baseSalary = calFloatSalary(salaryUserBaseInfo.getBaseSalary(), computeTimeBase, salaryUserBaseInfo);
            // 岗位工资
            double jobSalary = calFloatSalary(salaryUserBaseInfo.getJobSalary(), computeTimeBase, salaryUserBaseInfo);
            // 岗位(职级)工资
            double post = BigDecimal.valueOf(baseSalary).add(BigDecimal.valueOf(jobSalary)).doubleValue();
            // 住宿补贴
            double accommodationSubsidy = "是".equals(salaryUserBaseInfo.getHasAccommodationSubsidy()) ? calFloatSalary(ACCOMMODATION_SUBSIDY, computeTimeBase, salaryUserBaseInfo) : 0.0;
            // 餐费
            double foodSubsidy = calFloatSalary(salaryUserBaseInfo.getLevel() == 4 ? 400 : 600, computeTimeBase, salaryUserBaseInfo);

            SalaryAddition salaryAddition = salaryAdditionMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryAddition());
            SalaryTax salaryTax = salaryTaxMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryTax());
            SalaryDepartmentPerformance salaryDepartmentPerformance = salaryDepartmentPerformanceMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryDepartmentPerformance());
            SalaryTaxFirst salaryTaxFirst = salaryTaxFirstMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryTaxFirst());

            // 其他补发
//            double otherReward = salaryAddition.getSafetyReward() + salaryAddition.getOtherReward() + salaryAddition.getSafetyJobReward() + salaryDepartmentPerformance.getEmergencyRescuePerformance();

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
            // 考勤扣款
            double checkingInDeduct = sickDayDeduct + personalLeaveDaysDeduct;
            // 工会经费
            double partyPersonal = (jobSalary + salaryDepartmentPerformance.getMonthPerformancePrice()) * 0.005;
            // 夜餐补贴
            double nightFoodSubsidy = salaryDepartmentPerformance.getDelayDays() * 30 + salaryDepartmentPerformance.getNightDays() * 20;
            // 1号值班天数
//            double firstDutyDays = salaryDepartmentPerformance.getFirstDutyWorkdayDays() + salaryDepartmentPerformance.getFirstDutyWeekendDays();
            // 值班补贴+夜餐补贴+减人不减资（元）+高温补贴+其他补发-其他补扣-减事假-减病假
            double sumBase = onDutyDaySubsidy + nightFoodSubsidy
                    + salaryDepartmentPerformance.getJianrenbujianzi() + salaryAddition.getHighTemperatureReward() + salaryAddition.getOtherReward()
                    - salaryAddition.getOtherDeduct() - sickDayDeduct - personalLeaveDaysDeduct;
            // 其他应纳税所得合计
            double otherShouldTax = salaryTax.getOtherTaxWithoutMeal() + foodSubsidy;
            // 值班工资=值班补贴+夜餐补贴
            double duty = onDutyDaySubsidy + nightFoodSubsidy;
            SalaryTotal salaryTotal = new SalaryTotal();
            salaryTotal.setIdCardNo(salaryUserBaseInfo.getIdCardNo());
            salaryTotal.setBasicSalary(salaryUserBaseInfo.getBaseSalary());
            salaryTotal.setName(salaryUserBaseInfo.getName());
            salaryTotal.setSalaryLevel(salaryUserBaseInfo.getSalaryLevel());
            salaryTotal.setBankNo(salaryUserBaseInfo.getBankNo());
            salaryTotal.setDepartment(salaryUserBaseInfo.getDepartment());
            salaryTotal.setAdvanced(salaryAddition.getAdvancedReward());
            salaryTotal.setEconomicCompensation(salaryAddition.getEconomicReward());
            salaryTotal.setAdvanced(salaryAddition.getAdvancedReward());
            salaryTotal.setCheckingInDeduct(checkingInDeduct);
            salaryTotal.setPost(post);
            salaryTotal.setMerit(salaryDepartmentPerformance.getMonthPerformancePrice());
            salaryTotal.setJobTime(calJobTime(salaryUserBaseInfo, computeTimeBase));
            salaryTotal.setPartyBuildSubsidy(salaryAddition.getPartyBuildingReward());
            salaryTotal.setPartyPersonal(partyPersonal);
            salaryTotal.setSex((salaryUserBaseInfo.getIdCardNo().charAt(17) - '0') % 2 == 0 ? "男" : "女");
            salaryTotal.setSalaryGrade(salaryUserBaseInfo.getSalaryGrade());
            salaryTotal.setWeinisiPrice(salaryDepartmentPerformance.getWeinisiPrice());
            salaryTotal.setOtherReward(salaryAddition.getOtherReward());
            salaryTotal.setSick(sickDayDeduct);
            salaryTotal.setPersonalLeaveDaysDeduct(personalLeaveDaysDeduct);
            salaryTotal.setHouseSubsidy(accommodationSubsidy);
            salaryTotal.setDuty(duty);
            salaryTotal.setJobTitle(salaryUserBaseInfo.getJobTitle());
            salaryTotal.setEntryTime(salaryUserBaseInfo.getEntryTime());
            salaryTotal.setIsJiaotongBank(salaryUserBaseInfo.getIsTrafficBank());
            salaryTotal.setTaxFix(salaryTax.getFixedTax());
            salaryTotal.setSpecialDeduction(salaryTax.getSpecialDeduction());
            salaryTotal.setOtheDeduct(salaryAddition.getOtherDeduct());
            salaryTotal.setSafetySubsidy(salaryAddition.getAdvancedReward());
            salaryTotal.setRescueSubsidy(salaryAddition.getAdvancedReward());
            salaryTotal.setSafetySubsidy(salaryDepartmentPerformance.getEmergencyRescuePerformance());
            salaryTotal.setHighTemper(salaryAddition.getHighTemperatureReward());
            salaryTotal.setJianrenbujianzi(salaryDepartmentPerformance.getJianrenbujianzi());
            salaryTotal.setNightFoodSubsidy(nightFoodSubsidy);
            salaryTotal.setOnDutySubsidy(onDutyDaySubsidy);
            salaryTotal.setHuanjianpaodaosubsidy(huanjianpaodaoDaysSubsidy);
            salaryTotal.setHousingReformReward(salaryAddition.getHousingReformReward());
            salaryTotal.setYearMerit(yearMerit);
            salaryTotal.setJobSalary(salaryUserBaseInfo.getJobSalary());

            // 1:本部，2:惠泽，3:空港，4:实习生
            if ("1".equals(salaryUserBaseInfo.getMemberCat()) || "2".equals(salaryUserBaseInfo.getMemberCat())) {
                // 本部其他应发合计
                double otherShouldFund = salaryAddition.getAdvancedReward() + salaryAddition.getPartyBuildingReward() + sumBase;
                // 本部应发合计
                double shouldFund = baseSalary + jobSalary + yearMerit + salaryDepartmentPerformance.getMonthPerformancePrice() + huanjianpaodaoDaysSubsidy
                        + otherShouldFund + salaryAddition.getHousingReformReward();
                SalaryCentralEnterpriseFund salaryCentralEnterpriseFund = salaryCentralEnterpriseFundMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryCentralEnterpriseFund());
                SalaryCentralReserveFund salaryCentralReserveFund = salaryCentralReserveFundMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryCentralReserveFund());
                SalaryCentralSocialSecurityFund salaryCentralSocialSecurityFund = salaryCentralSocialSecurityFundMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryCentralSocialSecurityFund());
                SalaryCentralAgedFund salaryCentralAgedFund = salaryCentralAgedFundMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryCentralAgedFund());
                // 社保=养老个人+失业个人+医保个人+企业年金
                double socialTotal = salaryCentralEnterpriseFund.getIndividualMonthlyPayment()
                        + salaryCentralSocialSecurityFund.getLoseJobPersonalPament() + salaryCentralSocialSecurityFund.getPersonalPament() + salaryCentralAgedFund.getPersonalPament();
                // 社保+公积金
                double socialAndReserve = "是".equals(salaryUserBaseInfo.getBuySocialSecurity()) ? socialTotal + salaryCentralReserveFund.getPersonalMonthlyDeposit() : 0;
                // 本部应纳税所得额
                double shouldTax = (salaryCentralReserveFund.getPersonalMonthlyDeposit() > 3476 ?
                salaryCentralReserveFund.getPersonalMonthlyDeposit() + shouldFund + otherShouldTax - 3476 - (3476 + socialTotal + salaryTax.getSpecialDeduction() + 5000)
                : shouldFund + otherShouldTax - (socialAndReserve + salaryTax.getSpecialDeduction() + 5000)) + salaryTaxFirst.getFirstTax();
                // 本部预扣预缴个税
                double readyDeductTax = calReadyDeductTax(shouldTax, salaryTax.getAllYearTaxDeduction());
                // 本部代扣款合计
                double replaceDeduct = socialAndReserve + partyPersonal + salaryDepartmentPerformance.getWeinisiPrice() + readyDeductTax;
                // 本部实发工资
                double realSalary = shouldFund - replaceDeduct - salaryTax.getDeductPersonalTax();
                // 本部加发其他（总额）
                double addtionSalary = salaryAddition.getPartyBuildingReward() + salaryAddition.getHousingReformReward() + huanjianpaodaoDaysSubsidy + salaryDepartmentPerformance.getJianrenbujianzi() + salaryAddition.getOtherReward();

                salaryTotal.setRealSalary(realSalary);
                salaryTotal.setAdditionOtherTotal(addtionSalary);
                salaryTotal.setOtherShouldFund(otherShouldFund);
                salaryTotal.setShouldTax(shouldTax);
                salaryTotal.setReadyDeductTax(readyDeductTax);
                salaryTotal.setReplaceDeduct(replaceDeduct);
                salaryTotal.setShouldFund(shouldFund);

                if ("是".equals(salaryUserBaseInfo.getBuySocialSecurity())) {
                    // 养老
                    salaryTotal.setEgedPersonal(salaryCentralAgedFund.getPersonalPament());
                    salaryTotal.setEgedCompany(salaryCentralAgedFund.getCompanyPament());
                    // 失业
                    salaryTotal.setLoseJobPersonal(salaryCentralSocialSecurityFund.getLoseJobPersonalPament());
                    salaryTotal.setLoseJobCompany(salaryCentralSocialSecurityFund.getLoseJobCompanyPament());
                    // 医疗
                    salaryTotal.setMedicalPersonal(salaryCentralSocialSecurityFund.getPersonalPament());
                    salaryTotal.setMedicalCompany(salaryCentralSocialSecurityFund.getCompanyPament());
                    salaryTotal.setAdditionMedicalCompany(salaryCentralSocialSecurityFund.getAdditionMedicalPayment());
                    // 企业年金
                    salaryTotal.setEnterpriseAnnuityPersonal(salaryCentralEnterpriseFund.getIndividualMonthlyPayment());
                    salaryTotal.setEnterpriseAnnuityCompany(salaryCentralEnterpriseFund.getTotalEnterpriseContributions());
                    // 公积金
                    salaryTotal.setReservePersonalFund(salaryCentralReserveFund.getPersonalMonthlyDeposit());
                    salaryTotal.setReserveCompanyFund(salaryCentralReserveFund.getCompanyMonthlyDeposit());
                }
            } else if ("3".equals(salaryUserBaseInfo.getMemberCat())) {
                SalaryOutsourcingReserveFund salaryOutsourcingReserveFund = salaryOutsourcingReserveFundMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryOutsourcingReserveFund());
                SalaryOutsourcingSocialFund salaryOutsourcingSocialFund = salaryOutsourcingSocialFundMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryOutsourcingSocialFund());
                // 社保公积金=公积金个人+失业个人+养老个人+医保个人
                double socialAndReserve = "是".equals(salaryUserBaseInfo.getBuySocialSecurity()) ? salaryOutsourcingSocialFund.getPersonalPament() + salaryOutsourcingSocialFund.getLoseJobPersonalPayment() + salaryOutsourcingReserveFund.getPersonalMonthlyDeposit() + salaryOutsourcingSocialFund.getAgedPersonalPament() : 0;
                // 岗位补贴
                double jobSubsidy = salaryDepartmentPerformance.getJobSubsidyDays() * 200;
                // 空港应发合计
                double shouldFund = salaryAddition.getAdvancedReward() + salaryAddition.getSafetyJobReward() + jobSalary + baseSalary + salaryDepartmentPerformance.getMonthPerformancePrice() + jobSubsidy + huanjianpaodaoDaysSubsidy + sumBase;
                // 空港应纳税所得额 = (空港应发合计+餐费+其他应纳税所得合计 (除去餐补))-(养老个人+公积金个人+失业个人+医保个人+专项扣除数)-5000+上月空港应纳税所得额+差错调整金额
                double shouldTax = (shouldFund + otherShouldTax) - (socialAndReserve + salaryTax.getSpecialDeduction()) - 5000 + salaryTaxFirst.getFirstTax() + salaryTax.getFixedTax();
                // 空港预扣预缴个税
                double readyDeductTax = calReadyDeductTax(shouldTax, salaryTax.getAllYearTaxDeduction());
                // 空港代扣款合计 = 公积金个人+失业个人+养老个人+医保个人+工会经费+空港预扣预缴个税
                double replaceDeduct = socialAndReserve + partyPersonal + readyDeductTax;
                // 空港实发工资 = 空港应发合计-空港代扣款合计-补扣个税
                double realSalary = shouldFund - replaceDeduct - salaryTax.getDeductPersonalTax();
                // 空港加发其他（总额）
                double addtionSalary = salaryAddition.getHousingReformReward() + huanjianpaodaoDaysSubsidy + salaryDepartmentPerformance.getJianrenbujianzi() + salaryAddition.getOtherReward();
                salaryTotal.setRealSalary(realSalary);
                salaryTotal.setAdditionOtherTotal(addtionSalary);
                salaryTotal.setReplaceDeduct(replaceDeduct);
                salaryTotal.setReadyDeductTax(readyDeductTax);
                salaryTotal.setShouldTax(shouldTax);
                salaryTotal.setJobSubsidy(jobSubsidy);
                salaryTotal.setShouldFund(shouldFund);

                if ("是".equals(salaryUserBaseInfo.getBuySocialSecurity())) {
                    // 空港无企业年金，实习生无社保公积金
                    // 养老
                    salaryTotal.setEgedPersonal(salaryOutsourcingSocialFund.getAgedPersonalPament());
                    salaryTotal.setEgedCompany(salaryOutsourcingSocialFund.getAgedCompanyPament());
                    // 失业
                    salaryTotal.setLoseJobPersonal(salaryOutsourcingSocialFund.getLoseJobPersonalPayment());
                    salaryTotal.setLoseJobCompany(salaryOutsourcingSocialFund.getLoseJobCompanyPament());
                    // 医疗
                    salaryTotal.setMedicalPersonal(salaryOutsourcingSocialFund.getPersonalPament());
                    salaryTotal.setMedicalCompany(salaryOutsourcingSocialFund.getCompanyPament());
                    salaryTotal.setAdditionMedicalCompany(salaryOutsourcingSocialFund.getAdditionMedicalPament());
                    // 公积金
                    salaryTotal.setReservePersonalFund(salaryOutsourcingReserveFund.getPersonalMonthlyDeposit());
                    salaryTotal.setReserveCompanyFund(salaryOutsourcingReserveFund.getCompanyMonthlyDeposit());
                }
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
                SalaryInternSocialFund salaryInternSocialFund = salaryInternSocialFundMap.getOrDefault(salaryUserBaseInfo.getIdCardNo(), new SalaryInternSocialFund());
                // 特定人员单项工伤保险
                salaryTotal.setInjuryCompany(salaryInternSocialFund.getInjuryPament());
                salaryTotal.setRealSalary(realSalary);
                salaryTotal.setAdditionOtherTotal(addtionSalary);
                salaryTotal.setReadyDeductTax(readyDeductTax);
                salaryTotal.setShouldTax(shouldTax);
                salaryTotal.setJobSubsidy(jobSubsidy);
                salaryTotal.setInternSubsidy(internshipSubsidy);
                salaryTotal.setTotal(internshipTotal);
                salaryTotal.setJianxSubsidy(noviciateSubsidy);
            }
            salaryTotalMapper.insert(salaryTotal);
        }
    }

    /**
     * MAX(MAX(本部应纳税所得额*0.03,本部应纳税所得额*0.1-2520,本部应纳税所得额*0.2-16920,本部应纳税所得额*0.25-31920,本部应纳税所得额*0.3-52920,本部应纳税所得额*0.35-85920,本部应纳税所得额*0.45-181920,0)-1-12月扣税合计,0)
     * @param shouldTax
     * @param allYearTaxDeduction
     * @return
     */
    private double calReadyDeductTax(double shouldTax, double allYearTaxDeduction) {
        return Math.max(Stream.of(shouldTax * 0.03, shouldTax * 0.1 - 2520, shouldTax * 0.2 - 16920, shouldTax * 0.25 - 31920, shouldTax * 0.3 - 52920, shouldTax * 0.35 - 85920, shouldTax * 0.45 - 181920, 0.0)
                .max(Double::compareTo).get() - allYearTaxDeduction, 0);
    }

    private void checkPage(Page<?> page) {
        if (page == null || CollectionUtils.isEmpty(page.getRecords()) || page.getRecords().size() >= 400) {
            throw new JeecgSalaryException("某个表单数据为空或者超过" + 400 + "条数据！请检查表单");
        }
    }

    /**
     * 计算年功工资
     * @param salaryUserBaseInfo
     * @param computeTimeBase
     * @return
     */
    private double calYearMerit(SalaryUserBaseInfo salaryUserBaseInfo, Date computeTimeBase) {
        // 基本工资为0则年功工资为0
        return salaryUserBaseInfo.getBaseSalary() == 0 ? 0.0 : calJobTime(salaryUserBaseInfo, computeTimeBase);
    }

    /**
     * 计算司龄
     * @param salaryUserBaseInfo
     * @param computeTimeBase
     * @return
     */
    private double calJobTime(SalaryUserBaseInfo salaryUserBaseInfo, Date computeTimeBase) {
        if (salaryUserBaseInfo.getLeaveTime() == null) {
            return DateUtils.yearDiff(salaryUserBaseInfo.getEntryTime(), DateUtils.getLastDayOfMonth(computeTimeBase));
        } else {
            return DateUtils.yearDiff(salaryUserBaseInfo.getEntryTime(), salaryUserBaseInfo.getLeaveTime());
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
