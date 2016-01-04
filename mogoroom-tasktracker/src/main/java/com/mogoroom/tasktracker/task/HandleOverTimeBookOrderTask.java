package com.mogoroom.tasktracker.task;

import com.mogoroom.facade.IBookOrderFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 处理超时的预定单.
 *
 * <p>
 *     租客在规定的3天期限内未完成签约，预定单签约失败，定金房东不退回。
 * </p>
 *
 * 定时执行周期：<b>每5分钟执行一次</b>
 *
 * Author: jilinqiang
 * Date: 2015/12/31
 * Time: 8:35
 */
public class HandleOverTimeBookOrderTask implements Task {
    private static Logger log = Logger.getLogger(HandleOverTimeBookOrderTask.class);

    @Autowired
    private IBookOrderFacade bookOrderFacade;

    @Override
    public void run(Map<String, String> params) throws Throwable {
        log.info("开始执行定时任务：处理超时的预定单");
        bookOrderFacade.bookOrderTurntosignOrderFail();
    }
}
