package org.jeecg;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void testDateUtils() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 校验日期是否合法
            dateFormat.setLenient(false);
            Date entryDate = dateFormat.parse("2018-03-01");
            Date leaveDate = dateFormat.parse("2019-03-12");
            Calendar entryDateC = Calendar.getInstance();
            entryDateC.setTime(entryDate);
            Calendar leaveDateC = Calendar.getInstance();
            leaveDateC.setTime(leaveDate);
            int entryYear = entryDateC.get(Calendar.YEAR);
            int leaveYear = leaveDateC.get(Calendar.YEAR);
            int entryMonth = entryDateC.get(Calendar.MONTH) + 1;
            int leaveMonth = leaveDateC.get(Calendar.MONTH) + 1;
            int entryDay = entryDateC.get(Calendar.DAY_OF_MONTH);
            int leaveDay = leaveDateC.get(Calendar.DAY_OF_MONTH);
            int yDiff = 0;
            if (leaveMonth < entryMonth || leaveMonth == entryMonth && leaveDay < entryDay) {
                yDiff = leaveYear - entryYear - 1;
            } else {
                yDiff = leaveYear - entryYear;
            }
            System.out.println();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
