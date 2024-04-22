package org.jeecg.modules.demo.online.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.demo.online.vo.ImportCheckInfo;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.enums.EnhanceDataEnum;
import org.jeecg.modules.online.cgform.mapper.OnlCgformFieldMapper;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgform.service.a.f;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.transaction.interceptor.TransactionAspectSupport.currentTransactionStatus;

@Primary
@Service("extendedOnlCgformSqlServiceImpl")
public class ExtendedOnlCgformSqlServiceImpl extends f {
    private static final Logger a = LoggerFactory.getLogger(f.class);

    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> saveOnlineImportDataWithValidate(OnlCgformHead head, List<OnlCgformField> fieldList, List<Map<String, Object>> dataList) {
        HashMap var17 = new HashMap(5);
        Boolean isFalse = false;
        try {
        StringBuffer var4 = new StringBuffer();
        ImportCheckInfo var5 = new ImportCheckInfo(fieldList);
        OnlCgformFieldMapper var6 = (OnlCgformFieldMapper) SpringContextUtils.getBean(OnlCgformFieldMapper.class);
        int var7 = 0;
        int var8 = 0;
        int var9 = dataList.size();

        for (int var10 = 0; var10 < var9; ++var10) {
            String var11 = JSON.toJSONString(dataList.get(var10));
            ++var7;
            String var12 = var5.a(var11, var7);
            if (var12 == null) {
                try {
                    this.a(var11, head, fieldList, var6);
                } catch (Exception var16) {
                    ++var8;
                    String var14 = this.a(var16.getCause().getMessage());
                    String var15 = ImportCheckInfo.b(var14, var7);
                    var4.append(var15);
                    isFalse = true;
                }
            } else {
                ++var8;
                var4.append(var12);
            }
        }

        var17.put("error", var4.toString());
        String errorContent = ImportCheckInfo.a(var9, var8) + "<br>" + var4;
        var17.put("tip", errorContent);

        }catch (Exception ignored){
        }finally {
            if (isFalse){
                currentTransactionStatus().setRollbackOnly();
            }
        }
        return var17;
    }

    private void a(String var1, OnlCgformHead var2, List<OnlCgformField> var3, OnlCgformFieldMapper var4) throws BusinessException {
        JSONObject var5 = JSONObject.parseObject(var1);
        EnhanceDataEnum var6 = this.onlCgformHeadService.executeEnhanceImport(var2, var5);
        String var7 = var2.getTableName();
        Map var8;
        if (EnhanceDataEnum.INSERT == var6) {
            var8 = org.jeecg.modules.online.cgform.d.b.a(var7, var3, var5);
            a.info("executeInsertSQL params: " + JSON.toJSONString(var8));
            var4.executeInsertSQL(var8);
        } else if (EnhanceDataEnum.UPDATE == var6) {
            var8 = org.jeecg.modules.online.cgform.d.b.b(var7, var3, var5);
            a.info("executeUpdatetSQL params: " + JSON.toJSONString(var8));
            var4.executeUpdatetSQL(var8);
        } else if (EnhanceDataEnum.ABANDON == var6) {
        }

    }

    private String a(String var1) {
        String var2 = "^Duplicate entry \\'(.*)\\' for key .*$";
        Pattern var3 = Pattern.compile(var2);
        Matcher var4 = var3.matcher(var1);
        return var4.find() ? "重复数据" + var4.group(1) : var1;
    }
}
