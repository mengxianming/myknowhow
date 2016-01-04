package com.mogoroom.tasktracker.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.ICommonFacade;
import com.mogoroom.facade.IFlatsFacade;
import com.mogoroom.facade.IMessageFacade;
import com.mogoroom.facade.ISaleBillFacade;
import com.mogoroom.facade.IUserFacade;
import com.mogoroom.service.base.enums.PartnerMessageTypeEnum;
import com.mogoroom.service.domain.mesg.PartnerMessage;
import com.mogoroom.service.domain.user.Landlord;
import com.mogoroom.service.domain.user.LandlordReg;
import com.mogoroom.service.domain.user.Renter;
import com.mogoroom.service.user.IUserService;
import com.mogoroom.service.vo.RoomDetail;
import com.mogoroom.service.vo.criteria.RoomQueryCriteria;

/**
 * 参见原BS.LandlordQuartz.receiveNeedPayRentersInfo方法
 * 
 * @author admin
 * 
 */
@Component
public class ReceiveNeedPayRentersInfoTask implements Task {

	public static Log logger = LogFactory.getLog(ReceiveNeedPayRentersInfoTask.class);

	@Autowired
	private IUserFacade userFacade;

	@Autowired
	private ISaleBillFacade saleBillFacade;

	@Autowired
	private IFlatsFacade flatsFacade;

	@Autowired
	private IMessageFacade messageFacade;

	@Autowired
	private IUserService userServiceImpl;

	@Autowired
	private ICommonFacade commonFacadeImpl;

	/**
	 * 将当天为应付款日但仍未付款的租客清单发至房东
	 * 
	 * @author lxk 2015年7月4日-上午10:53:20
	 */
	@Override
	public void run(Map<String, String> params) throws Throwable {
		try {
			List<LandlordReg> landlordRegIds = userFacade.findLandlordRegId();
			for (LandlordReg reg : landlordRegIds) {
				// 未交租金租客清单
				List<Renter> renters = saleBillFacade.findArrearsRenterList(reg.getId(), 0);
				for (Renter renter : renters) {
					RoomQueryCriteria criteria = new RoomQueryCriteria();
					criteria.setRenterId(renter.getId());
					try {
						List<RoomDetail> rooms = flatsFacade.findRoomDetailsByCriteria(null, criteria);
						RoomDetail roomDetail = rooms.get(0);
						String content = "应付款日在今天，但还未支付下期租金。请及时催收。姓名：" + renter.getRealName() + "，手机：" + renter.getCellphone() + "，地址：" + roomDetail.getCityName() + roomDetail.getDistrictName() + roomDetail.getCommunityName() + roomDetail.getBuildNum() + "栋" + roomDetail.getFlatRoomNum() + "Room" + roomDetail.getRoom().getRoomName();
						PartnerMessage partnerMessage = new PartnerMessage();
						partnerMessage.setSendfrom("mogo");
						partnerMessage.setSendTo(reg.getId());
						partnerMessage.setTilte("【未支付下期租金租客】");
						partnerMessage.setContent(content);
						partnerMessage.setMsgType(PartnerMessageTypeEnum.资金.getIndex());
						// partnerMessage.setSendTime(new Date());
						// 统一更换时间源
						Date now = commonFacadeImpl.now();
						partnerMessage.setSendTime(now);
						Landlord landlord = userServiceImpl.findLandlordByID(reg.getId());
						partnerMessage.setLandlord(landlord);
						messageFacade.sendAPartnerMessage(partnerMessage, false, content);
					} catch (NullPointerException e) {
						logger.error("Error occurred when getting renter's room!", e);
					} catch (Exception e) {
						logger.error("Sending ArrearsRenter APPmessage error! type = 0", e);
					}

				}

				// 未交租金租客清单
				renters = saleBillFacade.findArrearsRenterList(reg.getId(), 1);
				for (Renter renter : renters) {
					RoomQueryCriteria criteria = new RoomQueryCriteria();
					criteria.setRenterId(renter.getId());
					try {
						List<RoomDetail> rooms = flatsFacade.findRoomDetailsByCriteria(null, criteria);
						RoomDetail roomDetail = rooms.get(0);
						String content = "应付款日但仍未支付租金，请及时催收。姓名：" + renter.getRealName() + "，手机：" + renter.getCellphone() + "，地址：" + roomDetail.getCityName() + roomDetail.getDistrictName() + roomDetail.getCommunityName() + roomDetail.getBuildNum() + "栋" + roomDetail.getFlatRoomNum() + "Room" + roomDetail.getRoom().getRoomName() + "。注：若该房源已安装智能门锁，则门锁密码会被锁定。";
						PartnerMessage partnerMessage = new PartnerMessage();
						partnerMessage.setSendfrom("mogo");
						partnerMessage.setSendTo(reg.getId());
						partnerMessage.setTilte("【已过应付款日租客】");
						partnerMessage.setContent(content);
						partnerMessage.setMsgType(PartnerMessageTypeEnum.资金.getIndex());
						// partnerMessage.setSendTime(new Date());
						// 统一更换时间源
						Date now = commonFacadeImpl.now();
						partnerMessage.setSendTime(now);
						Landlord landlord = userServiceImpl.findLandlordByID(reg.getId());
						partnerMessage.setLandlord(landlord);
						messageFacade.sendAPartnerMessage(partnerMessage, false, content);
					} catch (NullPointerException e) {
						logger.error("Error occurred when getting renter's room!", e);
					} catch (Exception e) {
						logger.error("Sending APPmessage error! type = 1", e);
					}

				}

				// renters =
				// saleBillFacade.findDelayPaymentSaleBillList(reg.getId());
				// for (Renter renter:renters) {
				// try {
				// String content =
				// "【租客补缴租金】"+renter.getRealName()+"已向你补缴下期租金。";
				// messageService.sendAPPmessageById(reg.getRegId(), content,
				// mogoSystem);
				// PartnerMessage partnerMessage = new PartnerMessage();
				// partnerMessage.setSendfrom("mogo");
				// partnerMessage.setSendTo(reg.getId());
				// partnerMessage.setTilte("【租客补缴租金】");
				// partnerMessage.setContent(content);
				// partnerMessage.setMsgType(PartnerMessageTypeEnum.资金.getIndex());
				// partnerMessage.setSendTime(new Date());
				// messageService.sendAPartnerMessage(partnerMessage, true,
				// content);
				// } catch (Exception e) {
				// LOGGER.error("Sending DelayPayment renters APPmessage error!",e);//
				// TODO: handle exception
				// }
				//
				// }
			}
			// 已过应付款日租客清单
		} catch (Exception e) {
			logger.error("Error occurred when getting renters!", e);
			throw e;
		}
	}

}
