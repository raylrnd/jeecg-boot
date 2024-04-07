package org.jeecg;

import org.jeecg.common.util.DateUtils;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void testDateUtils() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 校验日期是否合法
            Date entryDate = dateFormat.parse("2018-03-01");
            Date leaveDate = dateFormat.parse("2019-03-21");
            DateUtils.yearDiff(entryDate, leaveDate);
            System.out.println();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
