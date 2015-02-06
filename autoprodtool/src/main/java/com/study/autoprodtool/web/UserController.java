package com.study.autoprodtool.web;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.autoprodtool.common.CheckUtil;
import com.study.autoprodtool.common.ComUtils;
import com.study.autoprodtool.common.JsonResult;
import com.study.autoprodtool.common.ListJsonResult;
import com.study.autoprodtool.common.Pager;
import com.study.autoprodtool.entity.User;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.form.UserForm;
import com.study.autoprodtool.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Value("${pageLimit}")
	private Integer pageLimit;

	
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String listPage() {
		
		return "/user/list";
	}
	
	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public String createPage() {
		
		return "/user/create";
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public String updatePage() {
		
		return "/user/update";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String detailPage(@PathVariable String id) {
		log.info("show user detail fo id: "+ id);

		return "/user/detail";
	}
	
	

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResult list(@ModelAttribute ListCriteria<UserForm> listCriteria) throws Exception {
		List<User> list = userService.selectList(listCriteria);				

		return ListJsonResult.success(list, listCriteria.getPager());
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public JsonResult create(UserForm userForm) throws Exception{
		User user = userForm.toEntity(User.class);
		userService.insert(user);
		return JsonResult.success(user.getId());
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public JsonResult update(UserForm userForm) throws Exception{
		User user = userForm.toEntity(User.class);
		userService.update(user);
		return JsonResult.success(null);
	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public JsonResult delete(String ids) throws Exception{
		if(CheckUtil.isNull(ids)){
			throw new Exception("empty parameter: ids");
		}
		List<Long> idList = ComUtils.toNumbers(ids.split(","), Long.class);
		
		
		Map<Long, User> users = userService.selectListByIds(idList.toArray(new Long[0]));
		//check parameter
		for(Long id : idList){
			if(!users.containsKey(id)){
				throw new Exception(String.format("invalid parameter: %s... the entity for id %s does not exist.", ids, id));
			}
		}
		
		//do delete
		for(Long id : idList){
			userService.delete(users.get(id));
		}
		
		return JsonResult.success(null);
	}

	@ModelAttribute
	public ListCriteria<UserForm> initListCriteria(){
		ListCriteria<UserForm> listCriteria = new ListCriteria<UserForm>(){

			@Override
			protected void addCriterions(Criteria criteria) {


			}

		};
		listCriteria.setPager(new Pager().setLimit(pageLimit));
		return listCriteria;
	}

}
