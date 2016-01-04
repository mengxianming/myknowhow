package com.mogoroom.tasktracker.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mogoroom.facade.IAsyncOperationFacade;
import com.mogoroom.facade.IKeywordFacade;
import com.mogoroom.facade.IRoomSearchFacade;
import com.mogoroom.service.base.enums.KeywordEnum;
import com.mogoroom.service.domain.city.Business;
import com.mogoroom.service.domain.city.City;
import com.mogoroom.service.domain.city.Community;
import com.mogoroom.service.domain.city.District;
import com.mogoroom.service.domain.city.Station;
import com.mogoroom.service.domain.city.Subway;
import com.mogoroom.service.domain.comm.Dictionary;
import com.mogoroom.service.vo.RoomDetail;
import com.mogoroom.util.BaseMogoSystem;
import com.mysql.jdbc.StringUtils;

public class KeywordTask implements Task {

	private Logger log = Logger.getLogger(KeywordTask.class);
	
	@Autowired
	private IAsyncOperationFacade asyncOperationFacadeImpl;
	
	@Autowired
	private IKeywordFacade keywordFacadeImpl;
	
	@Autowired
	private IRoomSearchFacade roomSearchFacadeImpl;
	
	@Override
	public void run(Map<String, String> params) throws Throwable {
		
		long start = System.currentTimeMillis();
		
		Map<String, Integer> Ids = new HashMap<String, Integer>();
		
		/**
		 * 录入区域信息
		 */
		List<District> districts = keywordFacadeImpl.findOnlineDistricts();
		asyncOperationFacadeImpl.addCounter(districts.size());
		for(District district : districts) {
			int type = KeywordEnum.district.getIndex();
			int id = district.getId();
			String name = district.getName();
			double xCoordinate = null==district.getxCoordinate()?0:district.getxCoordinate();
			double yCoordinate = null==district.getyCoordinate()?0:district.getyCoordinate();
			int cityId = district.getCityId();
			try {
				asyncOperationFacadeImpl.addKeywordTask(type, id, name, xCoordinate, yCoordinate, cityId);
			} catch (InterruptedException e) {
				log.error("Error when addKeywordTask for type["+type+"], id["+id+"], name["+name+"], xCoordinate["+xCoordinate+"], yCoordinate["+yCoordinate+"], cityId["+cityId+"]",  e);
				continue;
			}
			
			Ids.put(KeywordEnum.district.getDesc()+id, 1);
		}
		
		/**
		 * 录入商圈信息
		 */
		List<Business> businesses = keywordFacadeImpl.findOnlineBusinesses();
		asyncOperationFacadeImpl.addCounter(businesses.size());
		for(Business business : businesses) {
			int type = KeywordEnum.busiArea.getIndex();
			int id = business.getId();
			String name = business.getName();
			double xCoordinate = null==business.getLng()?0:business.getLng();
			double yCoordinate = null==business.getLat()?0:business.getLat();
			int cityId = business.getCityId();
			try {
				asyncOperationFacadeImpl.addKeywordTask(type, id, name, xCoordinate, yCoordinate, cityId);
			} catch (InterruptedException e) {
				log.error("Error when addKeywordTask for type["+type+"], id["+id+"], name["+name+"], xCoordinate["+xCoordinate+"], yCoordinate["+yCoordinate+"], cityId["+cityId+"]",  e);
				continue;
			}
			
			Ids.put(KeywordEnum.busiArea.getDesc()+id, 1);
		}
		
		/**
		 * 录入地铁线信息
		 */
		List<Subway> subways = keywordFacadeImpl.findSubways();
		asyncOperationFacadeImpl.addCounter(subways.size());
		for(Subway subway : subways) {
			int type = KeywordEnum.subway.getIndex();
			int id = subway.getId();
			String name = subway.getName();
			double xCoordinate = 0;
			double yCoordinate = 0;
			int cityId = subway.getCityId();
			
			try {
				asyncOperationFacadeImpl.addKeywordTask(type, id, name, xCoordinate, yCoordinate, cityId);
			} catch (InterruptedException e) {
				log.error("Error when addKeywordTask for type["+type+"], id["+id+"], name["+name+"], xCoordinate["+xCoordinate+"], yCoordinate["+yCoordinate+"], cityId["+cityId+"]",  e);
				continue;
			}
			Ids.put(KeywordEnum.subway.getDesc()+id, 1);
		}
		
		/**
		 * 录入地铁站信息
		 */
		List<Station> stations = keywordFacadeImpl.findDistinctStations();
		asyncOperationFacadeImpl.addCounter(stations.size());
		for(Station station : stations) {
			int type = KeywordEnum.station.getIndex();
			int id = station.getId();
			String name = station.getName();
			double xCoordinate = null==station.getxCoordinate()?0:station.getxCoordinate();
			double yCoordinate = null==station.getyCoordinate()?0:station.getyCoordinate();
			int cityId = station.getCityId();
			try {
				asyncOperationFacadeImpl.addKeywordTask(type, id, name, xCoordinate, yCoordinate, cityId);
			} catch (InterruptedException e) {
				log.error("Error when addKeywordTask for type["+type+"], id["+id+"], name["+name+"], xCoordinate["+xCoordinate+"], yCoordinate["+yCoordinate+"], cityId["+cityId+"]",  e);
				continue;
			}
			Ids.put(KeywordEnum.station.getDesc()+id, 1);
		}
		
		/**
		 * 录入小区信息
		 */
		List<Community> communities = keywordFacadeImpl.findvalidCommunities();
		asyncOperationFacadeImpl.addCounter(communities.size());
		for(Community community : communities) {
			int type = KeywordEnum.community.getIndex();
			int id = community.getId();
			String name = community.getName();
			double xCoordinate = StringUtils.isNullOrEmpty(community.getxCoordinate())?0:Double.parseDouble(community.getxCoordinate());
			double yCoordinate = StringUtils.isNullOrEmpty(community.getyCoordinate())?0:Double.parseDouble(community.getyCoordinate());
			int cityId = community.getCityId();
			try {
				asyncOperationFacadeImpl.addKeywordTask(type, id, name, xCoordinate, yCoordinate, cityId);
			} catch (InterruptedException e) {
				log.error("Error when addKeywordTask for type["+type+"], id["+id+"], name["+name+"], xCoordinate["+xCoordinate+"], yCoordinate["+yCoordinate+"], cityId["+cityId+"]",  e);
				continue;
			}
			
			Ids.put(KeywordEnum.community.getDesc()+id, 1);
		}
		
		/**
		 * 录入房源信息
		 */
		List<RoomDetail> roomdeaDetails = roomSearchFacadeImpl.findRoomDetails();
		if(null!=roomdeaDetails && 0<roomdeaDetails.size()) {
			asyncOperationFacadeImpl.addCounter(roomdeaDetails.size());
			for(RoomDetail rd : roomdeaDetails) {
				int type = KeywordEnum.room.getIndex();
				int id = rd.getRoomId();
				String name = rd.getRoom().getRoomNum();
				double xCoordinate = null==rd.getLng()?0:rd.getLng();
				double yCoordinate = null==rd.getLat()?0:rd.getLat();
				int cityId = rd.getCityId();
				try {
					asyncOperationFacadeImpl.addKeywordTask(type, id, name, xCoordinate, yCoordinate, cityId);
				} catch (Exception e) {
					log.error("Error when addKeywordTask for type["+type+"], id["+id+"], name["+name+"], xCoordinate["+xCoordinate+"], yCoordinate["+yCoordinate+"], cityId["+cityId+"]",  e);
					continue;
				}
				
				Ids.put(KeywordEnum.room.getDesc()+id, 1);
			}
		}
		
		/**
		 * 录入星座信息
		 */
		List<Dictionary> dictionaries = keywordFacadeImpl.findConstellations();
		List<City> cities = roomSearchFacadeImpl.findOnlineCities();
		if(null!=cities && 0<cities.size()) {
			for(City city : cities) {
				asyncOperationFacadeImpl.addCounter(dictionaries.size());
				for(Dictionary dictionary : dictionaries) {
					int type = KeywordEnum.constellation.getIndex();
					int id = Integer.parseInt(city.getId() + "" + dictionary.getKeyPro());
					String name = dictionary.getValue();
					double xCoordinate = 0;
					double yCoordinate = 0;
					int cityId = city.getId();
					
					try {
						asyncOperationFacadeImpl.addKeywordTask(type, id, name, xCoordinate, yCoordinate, cityId);
					} catch (Exception e) {
						log.error("Error when addKeywordTask for type["+type+"], id["+id+"], name["+name+"], xCoordinate["+xCoordinate+"], yCoordinate["+yCoordinate+"], cityId["+cityId+"]",  e);
						continue;
					}
					
					Ids.put(KeywordEnum.constellation.getDesc() + id, 1);
				}
			}
		}
		
		/**
		 * 录入职业信息
		 */
		List<String> careers = keywordFacadeImpl.findAllCareers();
		if(null!=careers && 0<careers.size()) {
			for(City city : cities) {
				asyncOperationFacadeImpl.addCounter(careers.size());
				for(int i=0;i<careers.size();i++) {
					int type = KeywordEnum.career.getIndex();
					int id = Integer.parseInt(city.getId() + "" + i);
					String name = careers.get(i);
					double xCoordinate = 0;
					double yCoordinate = 0;
					int cityId = city.getId();
					
					try {
						asyncOperationFacadeImpl.addKeywordTask(type, id, name, xCoordinate, yCoordinate, cityId);
					} catch (Exception e) {
						log.error("Error when addKeywordTask for type["+type+"], id["+id+"], name["+name+"], xCoordinate["+xCoordinate+"], yCoordinate["+yCoordinate+"], cityId["+cityId+"]",  e);
						continue;
					}
					
					Ids.put(KeywordEnum.career.getDesc() + id, 1);
				}
			}
		}
		
		/**
		 * 录入爱好信息
		 */
		List<String> hobbies = keywordFacadeImpl.findAllHobbies();
		if(null!=hobbies && 0<hobbies.size()) {
			for(City city : cities) {
				asyncOperationFacadeImpl.addCounter(hobbies.size());
				for(int i=0;i<hobbies.size();i++) {
					int type = KeywordEnum.hobby.getIndex();
					int id = Integer.parseInt(city.getId() + "" + i);
					String name = hobbies.get(i);
					double xCoordinate = 0;
					double yCoordinate = 0;
					int cityId = city.getId();
					
					try {
						asyncOperationFacadeImpl.addKeywordTask(type, id, name, xCoordinate, yCoordinate, cityId);
					} catch (Exception e) {
						log.error("Error when addKeywordTask for type["+type+"], id["+id+"], name["+name+"], xCoordinate["+xCoordinate+"], yCoordinate["+yCoordinate+"], cityId["+cityId+"]",  e);
						continue;
					}
					
					Ids.put(KeywordEnum.hobby.getDesc() + id, 1);
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
		
		/**
		 * 删除冗余Doc
		 */
		BaseMogoSystem bms = BaseMogoSystem.getInstance();
		String elasticKeywordIndex = bms.getELASTIC_KEYWORD_INDEX();
		String elasticKeywordType = bms.getELASTIC_KEYWORD_TYPE();
		List<String> elasticIds = null;
		try {
			elasticIds = keywordFacadeImpl.findAllDocIds(elasticKeywordIndex, elasticKeywordType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(null!=elasticIds && 0<elasticIds.size()) {
			for(String id : elasticIds) {
				if(Ids.containsKey(id)) {
					continue;
				}
				try {
					keywordFacadeImpl.deleteDocById(elasticKeywordIndex, elasticKeywordType, id);
					log.info("Doc deleted, elasticKeywordIndex["+elasticKeywordIndex+"], elasticKeywordType["+elasticKeywordType+"], id["+id+"]");
				} catch (Exception e) {
					log.error("Error when deleteDocById", e);
					continue;
				}
			}
		}
		System.out.println("Completed!");
	}

}
