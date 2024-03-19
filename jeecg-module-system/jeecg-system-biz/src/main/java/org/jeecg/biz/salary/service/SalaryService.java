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

        List<SalaryUserBaseInfo> userBaseInfoRecords = salaryUserBaseInfoPage.getRecords();
        for (SalaryUserBaseInfo salaryUserBaseInfo : userBaseInfoRecords) {
            salaryCentralReport.setName(salaryUserBaseInfo.getName());
            salaryCentralReport.setYearMerit(calYearMerit(salaryUserBaseInfo));

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

}
