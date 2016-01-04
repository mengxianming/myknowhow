package com.mogoroom.tasktracker.task;

import com.mogoroom.facade.IBookOrderFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 房东拒绝预定退款处理.
 *
 * <p>
 *     此定时器对应两种业务场景(只针对维金支付)，如下：
 *          1.租客预定房间，支付定金后，房东不同意预定
 *          2.租客通过预定单转签约单，签约时刻，房东不同意签约
 *     以上两种情况，都存在将租客支付的定金按照原路返回(原卡进原卡出)，
 *     由于快钱退款当天支付当天退款银行不支持，故需要定时器在付款的第二天进行退款处理。
 * </p>
 *
 * 定时执行周期：<b>每日09:00:00执行</b>
 *
 * Author: jilinqiang
 * Date: 2015/12/31
 * Time: 8:49
 */
public class HandleDepositForRefuseBookOrderTask implements Task {
    private static Logger log = Logger.getLogger(HandleDepositForRefuseBookOrderTask.class);

    @Autowired
    private IBookOrderFacade bookOrderFacade;

    @Override
    public void run(Map<String, String> params) throws Throwable {
        log.info("开始执行定时任务：房东拒绝预定退款处理");
        bookOrderFacade.refuseBookOrderHandleDeposit();
    }
}
