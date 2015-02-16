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
import com.study.autoprodtool.entity.CategorySmall;
import com.study.autoprodtool.form.CategorySmallForm;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.service.CategorySmallService;
import com.study.autoprodtool.service.CrudService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CategorySmallController extends AbstractEntityController<CategorySmallForm, CategorySmall>{	
//	private static final Logger log = LoggerFactory.getLogger(CategorySmallController.class);

	@Autowired
	private CategorySmallService categorySmallService;		
	
	@RequestMapping(value = Urls.CATEGORYSMALL_LIST, method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResult list(@ModelAttribute ListCriteria<CategorySmallForm> listCriteria, HttpServletRequest request) throws Exception {		
		return super.list(listCriteria, request);
	}

	@RequestMapping(value = Urls.CATEGORYSMALL_CREATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult create(CategorySmallForm form) throws Exception{
		return super.create(form);
	}
	
	@RequestMapping(value = Urls.CATEGORYSMALL_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(CategorySmallForm form) throws Exception{
		return super.update(form);
	}
	
	@RequestMapping(value = Urls.CATEGORYSMALL_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(String ids) throws Exception{
		return super.delete(ids);
	}

	@ModelAttribute
	public ListCriteria<CategorySmallForm> initListCriteria(){		
		return getDefaultListCriteria();
	}

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.web.AbstractEntityController#getCrudService()
	 */
	@Override
	CrudService<CategorySmall> getCrudService() {		
		return categorySmallService;
	}	
}
