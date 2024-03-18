package org.jeecg.biz.salary.job;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 发送消息任务
 * @author: jeecg-boot
 */

@Slf4j
public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info(String.format(" Test-Boot 发送消息任务 SendMsgJob !  时间:" + DateUtils.getTimestamp()));
    }

}
