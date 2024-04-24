package org.jeecg.modules.demo.online.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.enums.CgformValidPatternEnum;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportCheckInfo extends org.jeecg.modules.online.cgform.d.h{
    private Map<String, OnlCgformField> d;
    private Map<String, OnlCgformField> e;
    private static final String f = ",";
    private static final String g = "第%s行校验信息:";
    private static final String h = "总上传行数:%s,错误行数:%s";
    public static final String a = "error";
    public static final String b = "tip";
    public static final String c = "filePath";

    public ImportCheckInfo() {
    }

    public ImportCheckInfo(List<OnlCgformField> var1) {
        this.d = new HashMap(5);
        this.e = new HashMap(5);
        Iterator var2 = var1.iterator();

        while(true) {
            OnlCgformField var3;
            do {
                if (!var2.hasNext()) {
                    return;
                }

                var3 = (OnlCgformField)var2.next();
                String var4 = var3.getFieldValidType();
                if (var4 != null && !"".equals(var4) && !CgformValidPatternEnum.ONLY.getType().equals(var4)) {
                    if (CgformValidPatternEnum.NOTNULL.getType().equals(var4)) {
                        this.e.put(var3.getDbFieldName(), var3);
                    } else {
                        this.d.put(var3.getDbFieldName(), var3);
                    }
                }
            } while(var3.getDbIsNull() != 0 && !"1".equals(var3.getFieldMustInput()));

            if (oConvertUtils.isEmpty(var3.getDbDefaultVal())) {
                this.e.put(var3.getDbFieldName(), var3);
            }
        }
    }

    public String a(String var1, int var2) {
        StringBuffer var3 = new StringBuffer();
        JSONObject var4 = JSON.parseObject(var1);
        Iterator var5 = this.e.keySet().iterator();

        while(true) {
            String var7;
            OnlCgformField var8;
            do {
                String var6;
                if (!var5.hasNext()) {
                    var5 = this.d.keySet().iterator();

                    while(var5.hasNext()) {
                        var6 = (String)var5.next();
                        var7 = var4.getString(var6);
                        var8 = (OnlCgformField)this.d.get(var6);
                        String var9 = var8.getFieldValidType();
                        if (var7 != null && !"".equals(var7)) {
                            String var10 = null;
                            String var11 = null;
                            if (CgformValidPatternEnum.INTEGER.getType().equals(var9)) {
                                var10 = "^-?[1-9]\\d*$";
                                var11 = "请输入整数";
                            } else {
                                CgformValidPatternEnum var12 = CgformValidPatternEnum.getPatternInfoByType(var9);
                                if (var12 == null) {
                                    var10 = var9;
                                    var11 = "校验【" + var9 + "】未通过";
                                } else {
                                    var10 = var12.getPattern();
                                    var11 = var12.getMsg();
                                }
                            }

                            Pattern var14 = Pattern.compile(var10);
                            Matcher var13 = var14.matcher(var7);
                            if (!var13.find()) {
                                var3.append(var8.getDbFieldTxt() + var11 + ",");
                            }
                        }
                    }

                    if (var3.length() > 0) {
                        return b(var3.toString(), var2);
                    }

                    return null;
                }

                var6 = (String)var5.next();
                var7 = var4.getString(var6);
                var8 = (OnlCgformField)this.e.get(var6);
            } while(var7 != null && !"".equals(var7));

            var3.append(var8.getDbFieldTxt() + CgformValidPatternEnum.NOTNULL.getMsg() + ",");
        }
    }

    public static String b(String var0, int var1) {
        return String.format("<br>第%s行校验信息:", var1) + var0 + "\r\n";
    }

    public static String a(int var0, int var1) {
        int var2 = var0 - var1;
        return String.format("总上传行数:%s,错误行数:%s", var0, var1);
    }
}
