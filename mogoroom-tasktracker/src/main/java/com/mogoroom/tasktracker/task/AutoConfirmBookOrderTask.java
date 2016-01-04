package com.mogoroom.tasktracker.task;

import com.mogoroom.facade.IBookOrderFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 预定单自动确认通过.
 *
 * <p>
 *     白天工作时间段，当租客成功付款预定成功后，如果职业房东在一个小时内未审核该预订单，系统会自动确认通过审核。
 * </p>
 *
 * 定时执行周期：<b>每5分钟执行一次</b>
 *
 * Author: jilinqiang
 * Date: 2015/12/31
 * Time: 8:42
 */
public class AutoConfirmBookOrderTask implements Task {
    private static Logger log = Logger.getLogger(AutoConfirmBookOrderTask.class);

    @Autowired
    private IBookOrderFacade bookOrderFacade;

    @Override
    public void run(Map<String, String> params) throws Throwable {
        log.info("开始执行定时任务：预定单自动确认通过");
        bookOrderFacade.bookOrderTurntoConfirmed();
    }
}
