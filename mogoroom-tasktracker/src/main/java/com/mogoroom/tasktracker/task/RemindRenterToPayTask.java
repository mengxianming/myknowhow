package com.mogoroom.tasktracker.task;

import com.mogoroom.facade.ISaleBillFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 提醒租客交租（租客账单应付日期的租客发送交租提醒）.
 *
 * <p>
 *     每期账单的应付日期的前一天，会给租客发送交租提醒短信
 * </p>
 *
 * 定时执行周期：<b>每日01:00:00执行</b>
 *
 * Author: jilinqiang
 * Date: 2015/12/31
 * Time: 8:26
 */
public class RemindRenterToPayTask implements Task {
    private static Logger log = Logger.getLogger(RemindRenterToPayTask.class);

    @Autowired
    private ISaleBillFacade saleBillFacade;

    @Override
    public void run(Map<String, String> params) throws Throwable {
        log.info("开始执行定时任务：账单应付日期提醒租客交租.");
        saleBillFacade.remindFirstDayArrearsRenterMessage();
    }
}
