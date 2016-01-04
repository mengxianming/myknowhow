package com.mogoroom.tasktracker.task;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.IUserFacade;
import com.mogoroom.facade.IUserZhimaFacade;
import com.mogoroom.service.base.enums.UserType;
import com.mogoroom.service.domain.user.Landlord;
import com.mogoroom.service.domain.user.UserZhimaScore;
import com.mogoroom.util.BaseMogoSystem;

/**
 * 参见原BS.ZhimaScoreQuartz.updateZhimaScore方法
 * 
 * @author admin
 * 
 */
@Component
public class SyncZhimaScoreTask implements Task {

	public static Log logger = LogFactory.getLog(SyncZhimaScoreTask.class);

	@Autowired
	private IUserZhimaFacade userZhimaFacade;
	@Autowired
	private IUserFacade userFacade;
	@Autowired
	private BaseMogoSystem baseMogoSystem;

	/**
	 * 同步用户的芝麻信用分数
	 */
	@Override
	public void run(Map<String, String> params) throws Throwable {
		try {
			List<UserZhimaScore> list = userZhimaFacade.selectAllZhimaScore();
			for (UserZhimaScore zhimaScore : list) {
				if (zhimaScore.getErrorCode() == null) {
					if (zhimaScore.getUserType() == UserType.房东.getCode()) {
						Landlord landlord = userFacade.findLandlordByID(zhimaScore.getUserId());
						try {
							userZhimaFacade.loadZhimaScore(landlord.getId(), UserType.房东.getCode(), baseMogoSystem, null);
						} catch (Exception e) {
							logger.error("批量更新房东芝麻信用时异常：" + e);
							continue;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

}
