package com.mogoroom.tasktracker.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mogoroom.facade.IAsyncOperationFacade;
import com.mogoroom.facade.IKeywordFacade;
import com.mogoroom.facade.IRoomSearchFacade;
import com.mogoroom.service.domain.city.Business;
import com.mogoroom.service.domain.city.City;
import com.mogoroom.service.domain.city.Community;
import com.mogoroom.service.domain.city.District;
import com.mogoroom.service.domain.city.SubwayStation;
import com.mogoroom.service.vo.RoomDetail;
import com.mogoroom.util.BaseMogoSystem;

public class RoomSearchTask implements Task {

	Logger log = Logger.getLogger(RoomSearchTask.class);
	
	@Autowired
	private IAsyncOperationFacade asyncOperationFacadeImpl;
	
	@Autowired
	private IRoomSearchFacade roomSearchFacadeImpl;
	
	@Autowired
	private IKeywordFacade keywordFacadeImpl;
	
	@Override
	public void run(Map<String, String> params) throws Throwable {
		long start = System.currentTimeMillis();
	
		/**
		 * 录入城市信息
		 */
		Map<Integer, Integer> cityIds = new HashMap<Integer, Integer>();
		List<City> cities = roomSearchFacadeImpl.findOnlineCities();
		if(null!=cities && 0<cities.size()) {
			asyncOperationFacadeImpl.addCounter(cities.size());
			for(City city : cities) {
				try {
					asyncOperationFacadeImpl.addCityTask(city);
					cityIds.put(city.getId(), 1);
				} catch (InterruptedException e) {
					log.error("Error when addCityTask for cityId["+city.getId()+"]",  e);
					continue;
				}
			}
		}
		
		/**
		 * 录入区域信息
		 */
		Map<Integer, Integer> districtIds = new HashMap<Integer, Integer>();
		List<District> districts = roomSearchFacadeImpl.findOnlineDistricts();
		if(null!=districts && 0<districts.size()) {
			asyncOperationFacadeImpl.addCounter(districts.size());
			for(District district : districts) {
				try {
					asyncOperationFacadeImpl.addDistrictTask(district);
					districtIds.put(district.getId(), 1);
				} catch (InterruptedException e) {
					log.error("Error when addCityTask for districtId["+district.getId()+"]",  e);
					continue;
				}
			}
		}
		
		/**
		 * 录入商圈信息
		 */
		Map<Integer, Integer> businessIds = new HashMap<Integer, Integer>();
		List<Business> businesses = roomSearchFacadeImpl.findOnlineBusinesses();
		if(null!=businesses && 0<businesses.size()) {
			asyncOperationFacadeImpl.addCounter(businesses.size());
			for(Business business : businesses) {
				try {
					asyncOperationFacadeImpl.addBusinessTask(business);
					businessIds.put(business.getId(), 1);
				} catch (InterruptedException e) {
					log.error("Error when addBusinessTask for businessId["+business.getId()+"]",  e);
					continue;
				}
			}
		}
		
		/**
		 * 录入地铁站信息
		 */
		Map<Integer, Integer> subwayStationIds = new HashMap<>();
		if(null!=cities && 0<cities.size()) {
			for(City city : cities) {
				Integer cityId = city.getId();
				List<SubwayStation> subwayStations = roomSearchFacadeImpl.findSubwayStationsByCityId(cityId);
				if(null!=subwayStations && 0<subwayStations.size()) {
					asyncOperationFacadeImpl.addCounter(subwayStations.size());
					for(SubwayStation subwayStation : subwayStations) {
						try {
							asyncOperationFacadeImpl.addSubwayStationTask(subwayStation);
							subwayStationIds.put(subwayStation.getId(), 1);
						} catch (Exception e) {
							log.error("Error when addSubwayStationTask for cityId["+cityId+"], subwayId["+subwayStation.getSubwayId()+"], stationId["+subwayStation.getStationId()+"]",  e);
							continue;
						}
					}
				}
			}
		}
		
		/**
		 * 录入小区汇总信息
		 */
		Map<Integer, Integer> communityIds = new HashMap<Integer, Integer>();
		List<Community> communities = roomSearchFacadeImpl.findSumByCommunities();
		if(null!=communities && 0<communities.size()) {
			asyncOperationFacadeImpl.addCounter(communities.size());
			for(Community community : communities) {
				try {
					asyncOperationFacadeImpl.addCommunityTask(community);
					communityIds.put(community.getId(), 1);
				} catch (Exception e) {
					log.error("Error when addCommunityTask for communityId["+community.getId()+"]",  e);
					continue;
				}
			}
		}
		
		/**
		 * 录入房源信息
		 */
		Map<Integer, Integer> roomIds = new HashMap<Integer, Integer>();
		List<RoomDetail> roomDetails = roomSearchFacadeImpl.findRoomDetails();
		if(null!=roomDetails && 0<roomDetails.size()) {
			asyncOperationFacadeImpl.addCounter(roomDetails.size());
			for(RoomDetail roomDetail : roomDetails) {
				try {
					asyncOperationFacadeImpl.addRoomDetailTask(roomDetail);
					roomIds.put(roomDetail.getRoomId(), 1);
				} catch (Exception e) {
					log.error("Error when addRoomDetailTask for roomId["+roomDetail.getRoomId()+"]",  e);
					continue;
				}
			}
		}
		
		int counter = 0;
		while(0 < (counter=asyncOperationFacadeImpl.currentCounter())) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Left " + counter + " rows to operate.");
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Completed! It spends " + (end-start));
		
		BaseMogoSystem bms = BaseMogoSystem.getInstance();
		String elasticSearchIndex = bms.getELASTIC_SEARCHROOM_INDEX();
		
		log.info("Starting to remove redundant data...");
		
		/**
		 * 删除冗余Doc之城市
		 */
		String cityType = bms.getELASTIC_SEARCHROOM_CITY_TYPE();
		keywordFacadeImpl.removeRedundantDataForIntKey(cityIds, elasticSearchIndex, cityType);
		log.info("Redundant city data has removed.");
		
		
		/**
		 * 删除冗余Doc之区域
		 */
		String districtType = bms.getELASTIC_SEARCHROOM_DISTRICT_TYPE();
		keywordFacadeImpl.removeRedundantDataForIntKey(districtIds, elasticSearchIndex, districtType);
		log.info("Redundant district data has removed.");
		
		/**
		 * 删除冗余Doc之商圈
		 */
		String businessType = bms.getELASTIC_SEARCHROOM_BUSINESS_TYPE();
		keywordFacadeImpl.removeRedundantDataForIntKey(businessIds, elasticSearchIndex, businessType);
		log.info("Redundant business data has removed.");
		
		/**
		 * 删除冗余Doc之地铁
		 */
		String subwayStationType = bms.getELASTIC_SEARCHROOM_STATION_TYPE();
		keywordFacadeImpl.removeRedundantDataForIntKey(subwayStationIds, elasticSearchIndex, subwayStationType);
		log.info("Redundant subwayStation data has removed.");
		
		/**
		 * 删除冗余Doc之小区
		 */
		String communityType = bms.getELASTIC_SEARCHROOM_COMMUNITY_TYPE();
		keywordFacadeImpl.removeRedundantDataForIntKey(communityIds, elasticSearchIndex, communityType);
		log.info("Redundant community data has removed.");
		
		/**
		 * 删除冗余Doc之房源
		 */
		String roomType = bms.getELASTIC_SEARCHROOM_ROOM_TYPE();
		keywordFacadeImpl.removeRedundantDataForIntKey(roomIds, elasticSearchIndex, roomType);
		log.info("Redundant room data has removed.");
	}

}
