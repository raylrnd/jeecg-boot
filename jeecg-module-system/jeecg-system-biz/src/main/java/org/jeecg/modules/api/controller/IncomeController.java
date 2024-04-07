package org.jeecg.modules.api.controller;

import io.swagger.annotations.Api;
import org.jeecg.biz.salary.service.SalaryService;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
@Api(tags = "用户管理")
@RequestMapping("/salary")
public class IncomeController {

    @Resource
    private SalaryService salaryService;

    @RequestMapping(value = "/getCalculate", method = RequestMethod.GET)
    public Result<String> getSalary(@RequestParam(name = "yearmonth", required = true) String yearmonth) throws ParseException {
        //处理日期格式以便使用
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date computeTimeBase = sdf.parse(yearmonth);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, 1, 1);
        try {
            salaryService.handleSalaryCompute(calendar.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.OK(yearmonth+"你好,这是返回的年月数据");
    }
}
