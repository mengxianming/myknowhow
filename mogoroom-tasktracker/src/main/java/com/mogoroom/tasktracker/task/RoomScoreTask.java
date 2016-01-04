package com.mogoroom.tasktracker.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mogoroom.facade.IAsyncOperationFacade;
import com.mogoroom.facade.IFlatsRoomFacade;
import com.mogoroom.service.base.enums.ScoreEnum;
import com.mogoroom.service.domain.flat.Room;
import com.mogoroom.service.vo.RoomScoreVO;

public class RoomScoreTask implements Task {

	Logger log = Logger.getLogger(RoomScoreTask.class);
	
	@Autowired
	private IAsyncOperationFacade asyncOperationFacadeImpl;
	
	@Autowired
	private IFlatsRoomFacade flatsRoomFacadeImpl;
	
	@Override
	public void run(Map<String, String> params) throws Throwable {
		long start = System.currentTimeMillis();
		int userId = 1;
		int userType = 5;
		int keyType = 1; // 房源
		List<Room> rooms = flatsRoomFacadeImpl.findAllRooms();
		ScoreEnum[] scoreEnums = ScoreEnum.values();
		asyncOperationFacadeImpl.initCounter(rooms.size());
		for(Room room : rooms) {
			int roomId = room.getId();
			List<RoomScoreVO> list = new ArrayList<RoomScoreVO>();
			for(ScoreEnum scorenEnum : scoreEnums) {
				list.add(new RoomScoreVO(roomId, keyType, scorenEnum, userId, userType));
			}
			try {
				asyncOperationFacadeImpl.addScoreTask(list);
			} catch (Exception e) {
				log.error("Error when excute add score task", e);
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
		System.out.println("It spends " + (end-start));
	}

}
