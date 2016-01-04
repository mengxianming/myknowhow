package com.mogoroom.tasktracker.task;

import com.mogoroom.facade.IReservationOrderFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 处理超时预约单.
 *
 * <p>
 *     此定时器负责处理时效过期的预约单，失效分两种情况：
 *          1.指定时间内，未转签约的预约单
 *          2.指定时间内，未转预定的预约单
 * </p>
 *
 * 定时执行周期：<b>每小时执行一次</b>
 *
 * Author: jilinqiang
 * Date: 2015/12/31
 * Time: 8:32
 */
public class HandleOverTimeReservationTask implements Task {
    private static Logger log = Logger.getLogger(HandleOverTimeReservationTask.class);

    @Autowired
    private IReservationOrderFacade reservationOrderFacade;

    @Override
    public void run(Map<String, String> params) throws Throwable {
        log.info("开始执行定时任务：处理超时预约单.");
        reservationOrderFacade.reservationTurnSignedOverTime();
    }
}
