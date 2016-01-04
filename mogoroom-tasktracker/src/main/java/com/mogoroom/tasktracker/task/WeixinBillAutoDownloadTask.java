package com.mogoroom.tasktracker.task;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.IAccountFacade;
import com.mogoroom.pay.weixin.WeiXinPay;
import com.mogoroom.service.comm.ICommonService;
import com.mogoroom.service.domain.bill.CheckBill;
import com.mogoroom.service.domain.bill.WeiXinCheckBill;
import com.mogoroom.util.MogoCalendar;
import com.mogoroom.util.enums.APPTypeEnum;

@Component
public class WeixinBillAutoDownloadTask implements Task{

	public static Log logger = LogFactory.getLog(WeixinBillAutoDownloadTask.class);

	@Autowired
	private IAccountFacade accountFacadeImpl;

	@Autowired
	private ICommonService commonServiceImpl;

	@Override
	public void run(Map<String, String> params) throws Throwable {
		try {

			//获取当前的日期的前一天
			Date now = commonServiceImpl.now();
			Date ye = MogoCalendar.getTimeDiff(now, 1);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String yeStr =	simpleDateFormat.format(ye);

			WeiXinPay weiXinPay = new WeiXinPay(APPTypeEnum.renter);
			InputStream inputStream = weiXinPay.downloadBill(yeStr);
			//保存对账单
			logger.info("--》系统开始保存对账单");
			List<WeiXinCheckBill> bils  = WeiXinCheckBill.csvToWeiXinCheckBill(inputStream);
			accountFacadeImpl.batchAddWeixinPayCheckBill(bils);

			//生成对账数据
			logger.info("--》正在生成对账数据");
			List<CheckBill> checkBills =  WeiXinCheckBill.weiXinCheckBillToCheckBill(bils,MogoCalendar.getTimeHMSZero(ye));
			accountFacadeImpl.batchAddCheckBill(checkBills);

			String platAccount = "";

			if(checkBills!=null && checkBills.size()>0){
				platAccount = checkBills.get(0).getPlatAccount();
			}


			//开始对账
			logger.info("--》正在进行对账");
			accountFacadeImpl.checkWeixinBill(MogoCalendar.getTimeHMSZero(ye),platAccount);
		} catch (Exception e) {
			logger.error("微信每日自动对账出现异常：", e);
		}
	}
}
