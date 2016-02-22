package my.study.spstudy.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.type.TypeFactory;

import my.study.spstudy.domain.Student;
import my.study.spstudy.service.IUserService;
import my.study.spstudy.util.JsonUtil;
import my.study.spstudy.vo.Page;

@Controller
public class UserController{
	@Autowired
	IUserService userService;
	@Autowired
	HttpServletRequest request;

	@RequestMapping("/")
	@ResponseBody
	public String index(){
		return "Hello";				
	}
	
	@RequestMapping(value="/user/create", method=RequestMethod.GET)
	public String createUserPage(Model model) throws Exception{
		Map<String, Object> md = toEntityModel(request.getContextPath() + "/user/create", Student.class, null, true);
		
		model.addAttribute("model", md);
		return "/pages/common/newentity";
	}

	private Map<String, Object> toEntityModel(String url, Class<?> entityClass, Object entity, boolean isNew)
			throws Exception {
		Map<String, Object> md = new LinkedHashMap<String, Object>();
		md.put("url", url);
		md.put("name", entityClass.getSimpleName());
		if(entity == null){
			entity = entityClass.newInstance();
		}		
		
		String json = JsonUtil.toJson(entity);		
		Map<String, String> dataMap = JsonUtil.fromJson(json,
				TypeFactory.defaultInstance().constructMapType(LinkedHashMap.class, String.class, String.class));
		if(isNew){
			dataMap.remove("id");
		}
		md.put("dataMap", dataMap);
		return md;
	}
	
	@RequestMapping(value="/user/create", method=RequestMethod.POST)
	public String createUser(Student user) throws Exception{	
		userService.create(user);
		
		return "redirect:/user/" + user.getId();
	}
	
	@RequestMapping(value="/user/update/{id}", method=RequestMethod.GET)
	public String updateUserPage(@PathVariable("id")Integer id, Model model) throws Exception{
		Student user = userService.findById(id);		
		Map<String, Object> md = toEntityModel(request.getContextPath() + "/user/update/" + id, Student.class, user, false);
		model.addAttribute("model", md);
		
		return "/pages/common/newentity";
	}
	
	@RequestMapping(value="/user/update/{id}", method=RequestMethod.POST)
	public String updateUser(@PathVariable("id")Integer id, Student user) throws Exception{	
		userService.update(user);
		
		return "redirect:/user/" + id;
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public String findUserById(@PathVariable("id")Integer id, ModelMap model) throws Exception{	
		Student user = userService.findById(id);		
		Map<String, Object> md = toEntityModel(request.getContextPath() + "/user/create", Student.class, user, false);
		model.addAttribute("model", md);
		return "/pages/common/entitydetail";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/user")	
	public String findUserList(Page page, @ModelAttribute("listDetail") LinkedHashMap<String, Object> listDetail) throws Exception{
		List<Student> list = userService.findList(page);	
		
		listDetail.put("name", Student.class.getSimpleName());
				
		String json = JsonUtil.toJson(new Student());		
		LinkedHashMap<String, Object> headers = JsonUtil.fromJson(json, LinkedHashMap.class);
		listDetail.put("headers", headers.keySet());
		
		listDetail.put("datas", list);
		
		
		return "/pages/common/entitylist";
		
	}
	
	@RequestMapping("/user/ex")
	public void testException(Integer type) throws Exception{
		userService.testException(type);				
	}
		

}
