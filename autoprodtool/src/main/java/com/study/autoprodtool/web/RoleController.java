package com.study.autoprodtool.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.autoprodtool.common.JsonResult;
import com.study.autoprodtool.common.ListJsonResult;
import com.study.autoprodtool.common.Urls;
import com.study.autoprodtool.entity.Role;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.form.RoleForm;
import com.study.autoprodtool.service.CrudService;
import com.study.autoprodtool.service.RoleService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RoleController extends AbstractEntityController<RoleForm, Role>{	
//	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;		
	
	@RequestMapping(value = Urls.ROLE_LIST, method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResult list(@ModelAttribute ListCriteria<RoleForm> listCriteria, HttpServletRequest request) throws Exception {		
		return super.list(listCriteria, request);
	}

	@RequestMapping(value = Urls.ROLE_CREATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult create(RoleForm form) throws Exception{
		return super.create(form);
	}
	
	@RequestMapping(value = Urls.ROLE_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(RoleForm form) throws Exception{
		return super.update(form);
	}
	
	@RequestMapping(value = Urls.ROLE_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(String ids) throws Exception{
		return super.delete(ids);
	}

	@ModelAttribute
	public ListCriteria<RoleForm> initListCriteria() throws Exception{
		return getDefaultListCriteria();
	}

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.web.AbstractEntityController#getCrudService()
	 */
	@Override
	CrudService<Role> getCrudService() {		
		return roleService;
	}	
}
