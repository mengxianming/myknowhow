package com.study.autoprodtool.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.autoprodtool.common.CheckUtil;
import com.study.autoprodtool.common.ComUtils;
import com.study.autoprodtool.common.JsonResult;
import com.study.autoprodtool.common.ListJsonResult;
import com.study.autoprodtool.common.Urls;
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
	
	@RequestMapping(value = Urls.USER_LIST, method = RequestMethod.GET)
	public String listPage() {
		
		return "/user/list";
	}
	
	@RequestMapping(value = Urls.USER_CREATE, method = RequestMethod.GET)
	public String createPage() {
		
		return "/user/create";
	}
	
	@RequestMapping(value = Urls.USER_UPDATE + "/{id}", method = RequestMethod.GET)
	public String updatePage(@PathVariable Long id, Model model) throws Exception {
		User user = userService.selectOne(id);
		model.addAttribute("user", new UserForm().initFromEntity(user));
		
		return "/user/update";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = Urls.USER_DETAIL + "/{id}", method = RequestMethod.GET)
	public String detailPage(@PathVariable Long id, Model model) throws Exception {
		User user = userService.selectOne(id);
		model.addAttribute("user", new UserForm().initFromEntity(user));

		return "/user/detail";
	}
	
	

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = Urls.USER_LIST, method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResult list(@ModelAttribute("listCriteria") ListCriteria<UserForm> listCriteria) throws Exception {
		List<User> list = userService.selectList(listCriteria);			
		List<UserForm> ret = new ArrayList<UserForm>();
		for(User user : list){
			ret.add(new UserForm().initFromEntity(user));
		}

		return ListJsonResult.success(ret, listCriteria.getPager());
	}

	@RequestMapping(value = Urls.USER_CREATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult create(UserForm userForm) throws Exception{
		User user = userForm.toEntity(User.class);
		userService.insert(user);
		return JsonResult.success(user.getId());
	}
	
	@RequestMapping(value = Urls.USER_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(UserForm userForm) throws Exception{
		User user = userForm.toEntity(User.class);
		userService.update(user);
		return JsonResult.success(null);
	}
	
	@RequestMapping(value = Urls.USER_DELETE, method = RequestMethod.POST)
	@ResponseBody
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

	@ModelAttribute("listCriteria")
	public ListCriteria<UserForm> initListCriteria(HttpServletRequest request){
		ListCriteria<UserForm> listCriteria = new ListCriteria<UserForm>(){

			@Override
			protected void addCriterions(Criteria criteria) {


			}

		};
		ComUtils.populateJqGridPagerSorterInfo(request, pageLimit, listCriteria);
		return listCriteria;
	}

}
