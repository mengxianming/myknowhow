package my.study.spstudy;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import my.study.spstudy.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Override
	public Map<String, Object> getUserByName(String name) {
		Map<String, Object> user = new HashMap<>();
		user.put("name", "mengxm");
		return user;
	}

}
