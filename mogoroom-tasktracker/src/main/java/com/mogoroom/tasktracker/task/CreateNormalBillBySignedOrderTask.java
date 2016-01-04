package com.mogoroom.tasktracker.task;

import com.mogoroom.facade.ICommonFacade;
import com.mogoroom.facade.ISaleBillFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * 由签约成功的签约单自动生成销售账单任务.
 *
 * <p>
 *  签约成功后，租客的各期的应收账务会生成，但是对应每期的账单是在每期开始的前15天生成的，此定时器负责生成这些账单
 * </p>
 *
 * 定时执行周期：<b>每日00:05:00执行</b>
 *
 * Author: jilinqiang
 * Date: 2015/12/31
 * Time: 7:51
 */
public class CreateNormalBillBySignedOrderTask implements Task {
    private static Logger log = Logger.getLogger(CreateNormalBillBySignedOrderTask.class);

    @Autowired
    private ISaleBillFacade saleBillFacade;
    @Autowired
    private ICommonFacade commonFacade;

    @Override
    public void run(Map<String, String> params) throws Throwable {
        log.info("开始由租客签约单生成销售账单定时任务");
        Date now = commonFacade.now();
        saleBillFacade.toCreateSaleBillByBillDate(now);
    }
}
