package com.study.autoprodtool.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.autoprodtool.common.JsonResult;
import com.study.autoprodtool.common.ListJsonResult;
import com.study.autoprodtool.common.Urls;
import com.study.autoprodtool.entity.User;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.form.UserForm;
import com.study.autoprodtool.service.CrudService;
import com.study.autoprodtool.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController extends AbstractEntityController<UserForm, User>{	
//	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = Urls.USER_LIST, method = RequestMethod.GET)
	public String listPage() {
		
		return "/user/list";
	}
	
	@RequestMapping(value = Urls.USER_CREATE, method = RequestMethod.GET)
	public String createPage() {
		
		return "/user/create";
	}
	
	@RequestMapping(value = Urls.USER_UPDATE, method = RequestMethod.GET)
	public String updatePage(@RequestParam Long id, Model model) throws Exception {
		User user = userService.selectOne(id);
		model.addAttribute("user", new UserForm().initFromEntity(user));
		
		return "/user/update";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = Urls.USER_DETAIL , method = RequestMethod.GET)
	public String detailPage(@RequestParam Long id, Model model) throws Exception {
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
	public ListJsonResult list(@ModelAttribute("listCriteria") ListCriteria<UserForm> listCriteria, HttpServletRequest request) throws Exception {
		return super.list(listCriteria, request);
	}

	@RequestMapping(value = Urls.USER_CREATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult create(UserForm userForm) throws Exception{
		return super.create(userForm);
	}
	
	@RequestMapping(value = Urls.USER_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(UserForm userForm) throws Exception{
		return super.update(userForm);
	}
	
	@RequestMapping(value = Urls.USER_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@RequestParam String ids) throws Exception{
		return super.delete(ids);
	}

	@ModelAttribute
	public ListCriteria<UserForm> initListCriteria(){		
		return getDefaultListCriteria();
	}

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.web.AbstractEntityController#getCrudService()
	 */
	@Override
	CrudService<User> getCrudService() {		
		return userService;
	}

}
