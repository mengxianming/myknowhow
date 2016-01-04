package com.mogoroom.tasktracker.task;

import com.mogoroom.facade.ISaleContractFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 自动处理过期无效的定单、合同数据.
 *
 * <p>
 *    此定时器负责处理签约时效过期的签约单和对应的合同，失效分两种情况:
 *      1.租客通过预约转签约，在指定时间内未支付首期款，则该签约单失效
 *      2.租客通过预定转签约，在指定时间内未支付首期款，则该签约单失效
 * </p>
 *
 * 定时执行周期：<b>每5分钟执行一次</b>
 *
 * Author: jilinqiang
 * Date: 2015/12/31
 * Time: 8:17
 */
public class HandleInvalidOrderAndContractTask implements Task {
    private static Logger log = Logger.getLogger(HandleInvalidOrderAndContractTask.class);

    @Autowired
    private ISaleContractFacade saleContractFacade;

    @Override
    public void run(Map<String, String> params) throws Throwable {
        log.info("开始处理过期无效的定单、合同的定时任务");
        saleContractFacade.handleInvalidOrderAndContract();
    }
}
