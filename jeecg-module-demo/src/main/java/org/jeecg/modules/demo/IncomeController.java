package org.jeecg.modules.demo;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.SymbolConstant;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "用户管理")
@RequestMapping("/salary")
public class IncomeController {

    @RequestMapping(value = "/getCalculate", method = RequestMethod.GET)
    public Result<String> getSalary(@RequestParam(name = "yearmonth", required = true) String yearmonth) throws ParseException {
        //处理日期格式以便使用
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = sdf.parse(yearmonth);

        return Result.OK(yearmonth+"你好,这是返回的年月数据");
    }
}
